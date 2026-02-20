package com.chargebee.v4.client;

import com.chargebee.v4.exceptions.NetworkException;
import com.chargebee.v4.internal.RetryConfig;
import com.chargebee.v4.transport.*;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("ChargebeeClient Async Executor Tests")
class ChargebeeClientAsyncExecutorTest {

    private static final String TEST_API_KEY = "test_api_key";
    private static final String TEST_SITE = "test-site";

    @Nested
    @DisplayName("delayAndRetry - ScheduledExecutorService Tests")
    class DelayAndRetryTests {

        @Test
        @DisplayName("should use ScheduledExecutorService for retry delays instead of Thread.sleep")
        @Timeout(10)
        void shouldUseScheduledExecutorForDelays() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response retryableResponse = createResponse(429);
            Response successResponse = createSuccessResponse();

            when(mockTransport.sendAsync(any(Request.class)))
                .thenReturn(CompletableFuture.completedFuture(retryableResponse))
                .thenReturn(CompletableFuture.completedFuture(successResponse));

            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(50)
                .retryOnStatus(new HashSet<>(Arrays.asList(429)))
                .build();

            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .transport(mockTransport)
                .retry(retryConfig)
                .build();

            Request request = Request.builder()
                .method("GET")
                .url("http://test.com")
                .build();

            long start = System.currentTimeMillis();
            CompletableFuture<Response> future = client.sendWithRetryAsync(request);
            Response response = future.get(5, TimeUnit.SECONDS);
            long duration = System.currentTimeMillis() - start;

            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).sendAsync(any(Request.class));
            // Verify delay was actually applied
            assertTrue(duration >= 30, "Retry delay should have been applied, but took only " + duration + "ms");

            client.close();
        }

        @Test
        @DisplayName("should not block ForkJoinPool.commonPool threads during retry delay")
        @Timeout(15)
        void shouldNotBlockCommonPoolDuringDelay() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response retryableResponse = createResponse(429);
            Response successResponse = createSuccessResponse();

            AtomicInteger callCount = new AtomicInteger(0);
            when(mockTransport.sendAsync(any(Request.class))).thenAnswer(invocation -> {
                int count = callCount.incrementAndGet();
                if (count <= 2) {
                    return CompletableFuture.completedFuture(retryableResponse);
                }
                return CompletableFuture.completedFuture(successResponse);
            });

            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(3)
                .baseDelayMs(100)
                .retryOnStatus(new HashSet<>(Arrays.asList(429)))
                .build();

            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .transport(mockTransport)
                .retry(retryConfig)
                .build();

            // Submit work on ForkJoinPool.commonPool to verify it's not starved
            CompletableFuture<String> commonPoolTask = CompletableFuture.supplyAsync(() -> {
                // This should complete quickly if common pool is not blocked
                return "common-pool-not-blocked";
            });

            Request request = Request.builder()
                .method("GET")
                .url("http://test.com")
                .build();

            CompletableFuture<Response> retryFuture = client.sendWithRetryAsync(request);

            // Common pool task should complete even while retries are in progress
            String result = commonPoolTask.get(2, TimeUnit.SECONDS);
            assertEquals("common-pool-not-blocked", result);

            // Wait for retry to complete
            Response response = retryFuture.get(10, TimeUnit.SECONDS);
            assertEquals(200, response.getStatusCode());

            client.close();
        }

        @Test
        @DisplayName("should handle multiple concurrent async retries without exhausting threads")
        @Timeout(30)
        void shouldHandleConcurrentRetriesWithoutExhaustingThreads() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response retryableResponse = createResponse(429);
            Response successResponse = createSuccessResponse();

            AtomicInteger totalCalls = new AtomicInteger(0);
            when(mockTransport.sendAsync(any(Request.class))).thenAnswer(invocation -> {
                int count = totalCalls.incrementAndGet();
                // First call for each request returns 429, second returns 200
                // With 10 requests, calls 1-10 return 429, calls 11-20 return 200
                if (count <= 10) {
                    return CompletableFuture.completedFuture(retryableResponse);
                }
                return CompletableFuture.completedFuture(successResponse);
            });

            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(50)
                .retryOnStatus(new HashSet<>(Arrays.asList(429)))
                .build();

            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .transport(mockTransport)
                .retry(retryConfig)
                .build();

            int concurrentRequests = 10;
            List<CompletableFuture<Response>> futures = new ArrayList<>();

            for (int i = 0; i < concurrentRequests; i++) {
                Request request = Request.builder()
                    .method("GET")
                    .url("http://test.com")
                    .build();
                futures.add(client.sendWithRetryAsync(request));
            }

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .get(20, TimeUnit.SECONDS);

            for (CompletableFuture<Response> future : futures) {
                assertEquals(200, future.get().getStatusCode());
            }

            client.close();
        }

        @Test
        @DisplayName("should apply correct backoff delay in async retries")
        @Timeout(10)
        void shouldApplyCorrectBackoffDelayInAsyncRetries() throws Exception {
            Transport mockTransport = mock(Transport.class);
            NetworkException networkException = new NetworkException("Network error", new Exception());
            Response successResponse = createSuccessResponse();

            List<Long> callTimestamps = Collections.synchronizedList(new ArrayList<>());

            CompletableFuture<Response> failedFuture1 = new CompletableFuture<>();
            failedFuture1.completeExceptionally(networkException);
            CompletableFuture<Response> failedFuture2 = new CompletableFuture<>();
            failedFuture2.completeExceptionally(networkException);

            when(mockTransport.sendAsync(any(Request.class))).thenAnswer(invocation -> {
                callTimestamps.add(System.currentTimeMillis());
                if (callTimestamps.size() <= 2) {
                    CompletableFuture<Response> failed = new CompletableFuture<>();
                    failed.completeExceptionally(networkException);
                    return failed;
                }
                return CompletableFuture.completedFuture(successResponse);
            });

            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(3)
                .baseDelayMs(100)
                .build();

            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .transport(mockTransport)
                .retry(retryConfig)
                .build();

            Request request = Request.builder()
                .method("GET")
                .url("http://test.com")
                .build();

            Response response = client.sendWithRetryAsync(request).get(10, TimeUnit.SECONDS);
            assertEquals(200, response.getStatusCode());

            // Verify exponential backoff: delays should increase
            assertEquals(3, callTimestamps.size());
            long delay1 = callTimestamps.get(1) - callTimestamps.get(0);
            long delay2 = callTimestamps.get(2) - callTimestamps.get(1);

            // First retry delay ~100ms, second ~200ms (with jitter)
            assertTrue(delay1 >= 50, "First retry delay too short: " + delay1 + "ms");
            assertTrue(delay2 >= 100, "Second retry delay too short: " + delay2 + "ms");

            client.close();
        }
    }

    @Nested
    @DisplayName("ChargebeeClient AutoCloseable Tests")
    class ClientAutoCloseableTests {

        @Test
        @DisplayName("should implement AutoCloseable")
        void shouldImplementAutoCloseable() {
            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .build();

            assertTrue(client instanceof AutoCloseable);
            client.close();
        }

        @Test
        @DisplayName("should work with try-with-resources")
        void shouldWorkWithTryWithResources() throws Exception {
            Transport mockTransport = mock(Transport.class);
            when(mockTransport.sendAsync(any(Request.class)))
                .thenReturn(CompletableFuture.completedFuture(createSuccessResponse()));

            try (ChargebeeClient client = ChargebeeClient.builder()
                    .apiKey(TEST_API_KEY)
                    .siteName(TEST_SITE)
                    .transport(mockTransport)
                    .build()) {

                Request request = Request.builder()
                    .method("GET")
                    .url("http://test.com")
                    .build();

                Response response = client.sendWithRetryAsync(request).get(5, TimeUnit.SECONDS);
                assertEquals(200, response.getStatusCode());
            }
            // No exception should be thrown after close
        }

        @Test
        @DisplayName("should close transport when transport is AutoCloseable")
        void shouldCloseTransportWhenAutoCloseable() throws Exception {
            DefaultTransport transport = new DefaultTransport(
                TransportConfig.builder().apiKey(TEST_API_KEY).build());

            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .transport(transport)
                .build();

            ExecutorService transportExecutor = transport.getAsyncExecutor();
            assertFalse(transportExecutor.isShutdown());

            client.close();

            assertTrue(transportExecutor.isShutdown(),
                "Transport executor should be shut down when client is closed");
        }

        @Test
        @DisplayName("should handle close gracefully when transport is not AutoCloseable")
        void shouldHandleCloseWhenTransportNotAutoCloseable() {
            Transport mockTransport = mock(Transport.class);

            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .transport(mockTransport)
                .build();

            // Should not throw even with non-closeable transport
            assertDoesNotThrow(() -> client.close());
        }

        @Test
        @DisplayName("should handle multiple close calls gracefully")
        void shouldHandleMultipleCloseCalls() {
            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .build();

            assertDoesNotThrow(() -> {
                client.close();
                client.close();
            });
        }
    }

    // Helper methods

    private Response createSuccessResponse() {
        return new Response(200, new HashMap<>(), "OK".getBytes());
    }

    private Response createResponse(int statusCode) {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Content-Type", Arrays.asList("application/json"));
        String body = String.format("{\"status\":%d}", statusCode);
        return new Response(statusCode, headers, body.getBytes());
    }
}
