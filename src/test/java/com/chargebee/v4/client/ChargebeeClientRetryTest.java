package com.chargebee.v4.client;

import com.chargebee.v4.internal.RetryConfig;
import com.chargebee.v4.transport.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("ChargebeeClient Retry Tests")
class ChargebeeClientRetryTest {
    
    private static final String TEST_API_KEY = "test_api_key";
    private static final String TEST_SITE = "test-site";
    
    @Nested
    @DisplayName("sendWithRetry - Retry Disabled Tests")
    class RetryDisabledTests {
        
        @Test
        @DisplayName("should not retry when retry is disabled and request succeeds")
        void shouldNotRetryWhenDisabledAndSucceeds() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response mockResponse = createSuccessResponse();
            when(mockTransport.send(any(Request.class))).thenReturn(mockResponse);
            
            RetryConfig noRetry = RetryConfig.builder().enabled(false).build();
            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .transport(mockTransport)
                .retry(noRetry)
                .build();
            
            Request request = Request.builder()
                .method("GET")
                .url("http://test.com")
                .build();
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(1)).send(any(Request.class));
        }
        
        @Test
        @DisplayName("should not retry when retry is disabled and request fails")
        void shouldNotRetryWhenDisabledAndFails() throws Exception {
            Transport mockTransport = mock(Transport.class);
            when(mockTransport.send(any(Request.class)))
                .thenThrow(new NetworkException("Network error", new Exception()));
            
            RetryConfig noRetry = RetryConfig.builder().enabled(false).build();
            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .transport(mockTransport)
                .retry(noRetry)
                .build();
            
            Request request = Request.builder()
                .method("GET")
                .url("http://test.com")
                .build();
            
            assertThrows(NetworkException.class, () -> client.sendWithRetry(request));
            verify(mockTransport, times(1)).send(any(Request.class));
        }
    }
    
    @Nested
    @DisplayName("sendWithRetry - Retry on Network Errors")
    class RetryOnNetworkErrorsTests {
        
        @Test
        @DisplayName("should retry on TimeoutException and succeed")
        void shouldRetryOnTimeoutAndSucceed() throws Exception {
            Transport mockTransport = mock(Transport.class);
            TimeoutException timeoutException = new TimeoutException("read", "Timeout", new Exception());
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.send(any(Request.class)))
                .thenThrow(timeoutException)
                .thenReturn(successResponse);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
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
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).send(any(Request.class));
        }
        
        @Test
        @DisplayName("should retry on NetworkException and succeed")
        void shouldRetryOnNetworkExceptionAndSucceed() throws Exception {
            Transport mockTransport = mock(Transport.class);
            NetworkException networkException = new NetworkException("Network error", new Exception());
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.send(any(Request.class)))
                .thenThrow(networkException)
                .thenReturn(successResponse);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
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
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).send(any(Request.class));
        }
        
        @Test
        @DisplayName("should fail after max retries on TimeoutException")
        void shouldFailAfterMaxRetriesOnTimeout() throws Exception {
            Transport mockTransport = mock(Transport.class);
            TimeoutException timeoutException = new TimeoutException("read", "Timeout", new Exception());
            
            when(mockTransport.send(any(Request.class))).thenThrow(timeoutException);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
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
            
            TimeoutException thrown = assertThrows(TimeoutException.class, 
                () -> client.sendWithRetry(request));
            
            assertEquals("Timeout", thrown.getMessage());
            verify(mockTransport, times(3)).send(any(Request.class)); // Initial + 2 retries
        }
        
        @Test
        @DisplayName("should fail after max retries on NetworkException")
        void shouldFailAfterMaxRetriesOnNetworkException() throws Exception {
            Transport mockTransport = mock(Transport.class);
            NetworkException networkException = new NetworkException("Network error", new Exception());
            
            when(mockTransport.send(any(Request.class))).thenThrow(networkException);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(3)
                .baseDelayMs(10)
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
            
            NetworkException thrown = assertThrows(NetworkException.class, 
                () -> client.sendWithRetry(request));
            
            assertEquals("Network error", thrown.getMessage());
            verify(mockTransport, times(4)).send(any(Request.class)); // Initial + 3 retries
        }
    }
    
    @Nested
    @DisplayName("sendWithRetry - Retry on Status Codes")
    class RetryOnStatusCodesTests {
        
        @Test
        @DisplayName("should retry on 429 Too Many Requests")
        void shouldRetryOn429() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response tooManyRequests = createResponse(429);
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.send(any(Request.class)))
                .thenReturn(tooManyRequests)
                .thenReturn(successResponse);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
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
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).send(any(Request.class));
        }
        
        @Test
        @DisplayName("should retry on 502 Bad Gateway")
        void shouldRetryOn502() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response badGateway = createResponse(502);
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.send(any(Request.class)))
                .thenReturn(badGateway)
                .thenReturn(successResponse);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
                .retryOnStatus(new HashSet<>(Arrays.asList(502)))
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
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).send(any(Request.class));
        }
        
        @Test
        @DisplayName("should retry on 503 Service Unavailable")
        void shouldRetryOn503() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response serviceUnavailable = createResponse(503);
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.send(any(Request.class)))
                .thenReturn(serviceUnavailable)
                .thenReturn(successResponse);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
                .retryOnStatus(new HashSet<>(Arrays.asList(503)))
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
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).send(any(Request.class));
        }
        
        @Test
        @DisplayName("should retry on 504 Gateway Timeout")
        void shouldRetryOn504() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response gatewayTimeout = createResponse(504);
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.send(any(Request.class)))
                .thenReturn(gatewayTimeout)
                .thenReturn(successResponse);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
                .retryOnStatus(new HashSet<>(Arrays.asList(504)))
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
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).send(any(Request.class));
        }
        
        @Test
        @DisplayName("should not retry on 400 Bad Request")
        void shouldNotRetryOn400() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response badRequest = createResponse(400);
            
            when(mockTransport.send(any(Request.class))).thenReturn(badRequest);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
                .retryOnStatus(new HashSet<>(Arrays.asList(429, 502, 503, 504))) // Not 400
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
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(400, response.getStatusCode());
            verify(mockTransport, times(1)).send(any(Request.class)); // No retries
        }
        
        @Test
        @DisplayName("should return retryable status code after max retries")
        void shouldReturnRetryableStatusAfterMaxRetries() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response tooManyRequests = createResponse(429);
            
            when(mockTransport.send(any(Request.class))).thenReturn(tooManyRequests);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
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
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(429, response.getStatusCode());
            verify(mockTransport, times(3)).send(any(Request.class)); // Initial + 2 retries
        }
    }
    
    @Nested
    @DisplayName("sendWithRetry - Non-Retryable Exceptions")
    class NonRetryableExceptionsTests {
        
        @Test
        @DisplayName("should not retry on ConfigurationException")
        void shouldNotRetryOnConfigurationException() throws Exception {
            Transport mockTransport = mock(Transport.class);
            ConfigurationException configException = new ConfigurationException("Invalid config");
            
            when(mockTransport.send(any(Request.class))).thenThrow(configException);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(3)
                .baseDelayMs(10)
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
            
            ConfigurationException thrown = assertThrows(ConfigurationException.class,
                () -> client.sendWithRetry(request));
            
            assertEquals("Invalid config", thrown.getMessage());
            verify(mockTransport, times(1)).send(any(Request.class)); // No retries
        }
        
        @Test
        @DisplayName("should not retry on ClientErrorException")
        void shouldNotRetryOnClientErrorException() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response errorResponse = createResponse(400);
            ClientErrorException clientError = new ClientErrorException(400, "Bad Request", errorResponse);
            
            when(mockTransport.send(any(Request.class))).thenThrow(clientError);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(3)
                .baseDelayMs(10)
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
            
            ClientErrorException thrown = assertThrows(ClientErrorException.class,
                () -> client.sendWithRetry(request));
            
            assertEquals(400, thrown.getStatusCode());
            verify(mockTransport, times(1)).send(any(Request.class)); // No retries
        }
    }
    
    @Nested
    @DisplayName("sendWithRetry - Request Override Tests")
    class RequestOverrideTests {
        
        @Test
        @DisplayName("should use request override for max retries")
        void shouldUseRequestOverrideForMaxRetries() throws Exception {
            Transport mockTransport = mock(Transport.class);
            NetworkException networkException = new NetworkException("Network error", new Exception());
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.send(any(Request.class)))
                .thenThrow(networkException)
                .thenThrow(networkException)
                .thenThrow(networkException)
                .thenReturn(successResponse);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(1) // Default is 1 retry
                .baseDelayMs(10)
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
                .maxNetworkRetriesOverride(3) // Override to 3 retries
                .build();
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(4)).send(any(Request.class)); // Initial + 3 retries
        }
        
        @Test
        @DisplayName("should enable retry when override is set even if config disabled")
        void shouldEnableRetryWithOverride() throws Exception {
            Transport mockTransport = mock(Transport.class);
            NetworkException networkException = new NetworkException("Network error", new Exception());
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.send(any(Request.class)))
                .thenThrow(networkException)
                .thenReturn(successResponse);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(false) // Retry disabled in config
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
                .maxNetworkRetriesOverride(2) // Enable via override
                .build();
            
            Response response = client.sendWithRetry(request);
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).send(any(Request.class));
        }
        
        @Test
        @DisplayName("should disable retry when override is 0")
        void shouldDisableRetryWithZeroOverride() throws Exception {
            Transport mockTransport = mock(Transport.class);
            NetworkException networkException = new NetworkException("Network error", new Exception());
            
            when(mockTransport.send(any(Request.class))).thenThrow(networkException);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(3) // Enabled in config
                .baseDelayMs(10)
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
                .maxNetworkRetriesOverride(0) // Disable retries for this request
                .build();
            
            NetworkException thrown = assertThrows(NetworkException.class,
                () -> client.sendWithRetry(request));
            
            assertNotNull(thrown);
            verify(mockTransport, times(1)).send(any(Request.class)); // No retries
        }
    }
    
    @Nested
    @DisplayName("sendWithRetryAsync - Async Retry Tests")
    class AsyncRetryTests {
        
        @Test
        @DisplayName("should retry async request on TimeoutException and succeed")
        void shouldRetryAsyncOnTimeoutAndSucceed() throws Exception {
            Transport mockTransport = mock(Transport.class);
            TimeoutException timeoutException = new TimeoutException("read", "Timeout", new Exception());
            Response successResponse = createSuccessResponse();
            
            CompletableFuture<Response> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(timeoutException);
            
            when(mockTransport.sendAsync(any(Request.class)))
                .thenReturn(failedFuture)
                .thenReturn(CompletableFuture.completedFuture(successResponse));
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
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
            
            CompletableFuture<Response> future = client.sendWithRetryAsync(request);
            Response response = future.get();
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).sendAsync(any(Request.class));
        }
        
        @Test
        @DisplayName("should retry async request on NetworkException and succeed")
        void shouldRetryAsyncOnNetworkExceptionAndSucceed() throws Exception {
            Transport mockTransport = mock(Transport.class);
            NetworkException networkException = new NetworkException("Network error", new Exception());
            Response successResponse = createSuccessResponse();
            
            CompletableFuture<Response> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(networkException);
            
            when(mockTransport.sendAsync(any(Request.class)))
                .thenReturn(failedFuture)
                .thenReturn(CompletableFuture.completedFuture(successResponse));
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
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
            
            CompletableFuture<Response> future = client.sendWithRetryAsync(request);
            Response response = future.get();
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).sendAsync(any(Request.class));
        }
        
        @Test
        @DisplayName("should fail async after max retries")
        void shouldFailAsyncAfterMaxRetries() throws Exception {
            Transport mockTransport = mock(Transport.class);
            NetworkException networkException = new NetworkException("Network error", new Exception());
            
            CompletableFuture<Response> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(networkException);
            
            when(mockTransport.sendAsync(any(Request.class)))
                .thenReturn(failedFuture);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
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
            
            CompletableFuture<Response> future = client.sendWithRetryAsync(request);
            
            ExecutionException exception = assertThrows(ExecutionException.class, () -> future.get());
            assertTrue(exception.getCause() instanceof NetworkException);
            verify(mockTransport, times(3)).sendAsync(any(Request.class)); // Initial + 2 retries
        }
        
        @Test
        @DisplayName("should retry async on retryable status code")
        void shouldRetryAsyncOnRetryableStatusCode() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response tooManyRequests = createResponse(429);
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.sendAsync(any(Request.class)))
                .thenReturn(CompletableFuture.completedFuture(tooManyRequests))
                .thenReturn(CompletableFuture.completedFuture(successResponse));
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
                .baseDelayMs(10)
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
            
            CompletableFuture<Response> future = client.sendWithRetryAsync(request);
            Response response = future.get();
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(2)).sendAsync(any(Request.class));
        }
        
        @Test
        @DisplayName("should not retry async when retry disabled")
        void shouldNotRetryAsyncWhenDisabled() throws Exception {
            Transport mockTransport = mock(Transport.class);
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.sendAsync(any(Request.class)))
                .thenReturn(CompletableFuture.completedFuture(successResponse));
            
            RetryConfig noRetry = RetryConfig.builder().enabled(false).build();
            ChargebeeClient client = ChargebeeClient.builder()
                .apiKey(TEST_API_KEY)
                .siteName(TEST_SITE)
                .transport(mockTransport)
                .retry(noRetry)
                .build();
            
            Request request = Request.builder()
                .method("GET")
                .url("http://test.com")
                .build();
            
            CompletableFuture<Response> future = client.sendWithRetryAsync(request);
            Response response = future.get();
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(1)).sendAsync(any(Request.class));
        }
        
        @Test
        @DisplayName("should not retry async on non-retryable exception")
        void shouldNotRetryAsyncOnNonRetryableException() throws Exception {
            Transport mockTransport = mock(Transport.class);
            ConfigurationException configException = new ConfigurationException("Invalid config");
            
            CompletableFuture<Response> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(configException);
            
            when(mockTransport.sendAsync(any(Request.class)))
                .thenReturn(failedFuture);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(3)
                .baseDelayMs(10)
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
            
            CompletableFuture<Response> future = client.sendWithRetryAsync(request);
            
            ExecutionException exception = assertThrows(ExecutionException.class, () -> future.get());
            assertTrue(exception.getCause() instanceof ConfigurationException);
            verify(mockTransport, times(1)).sendAsync(any(Request.class)); // No retries
        }
    }
    
    @Nested
    @DisplayName("Backoff Delay Calculation Tests")
    class BackoffDelayTests {
        
        @Test
        @DisplayName("should calculate exponential backoff with increasing delays")
        void shouldCalculateExponentialBackoff() throws Exception {
            Transport mockTransport = mock(Transport.class);
            NetworkException networkException = new NetworkException("Network error", new Exception());
            Response successResponse = createSuccessResponse();
            
            AtomicInteger attemptCount = new AtomicInteger(0);
            List<Long> timestamps = new ArrayList<>();
            
            when(mockTransport.send(any(Request.class))).thenAnswer(invocation -> {
                timestamps.add(System.currentTimeMillis());
                int attempt = attemptCount.getAndIncrement();
                if (attempt < 3) {
                    throw networkException;
                }
                return successResponse;
            });
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(3)
                .baseDelayMs(50) // 50ms base delay
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
            Response response = client.sendWithRetry(request);
            long duration = System.currentTimeMillis() - start;
            
            assertEquals(200, response.getStatusCode());
            verify(mockTransport, times(4)).send(any(Request.class));
            
            // Verify that delays were applied (approximate check)
            // Expected delays: ~50ms, ~100ms, ~200ms = ~350ms total (with jitter)
            assertTrue(duration >= 200, "Duration too short: " + duration + "ms"); // Allow for jitter
            assertTrue(duration < 1000, "Duration too long: " + duration + "ms");
        }
        
        @Test
        @DisplayName("should apply backoff delays between retries")
        void shouldApplyBackoffDelays() throws Exception {
            Transport mockTransport = mock(Transport.class);
            TimeoutException timeoutException = new TimeoutException("read", "Timeout", new Exception());
            Response successResponse = createSuccessResponse();
            
            when(mockTransport.send(any(Request.class)))
                .thenThrow(timeoutException)
                .thenThrow(timeoutException)
                .thenReturn(successResponse);
            
            RetryConfig retryConfig = RetryConfig.builder()
                .enabled(true)
                .maxRetries(2)
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
            
            long start = System.currentTimeMillis();
            Response response = client.sendWithRetry(request);
            long duration = System.currentTimeMillis() - start;
            
            assertEquals(200, response.getStatusCode());
            // Should take at least 100ms (first retry) + 200ms (second retry) = 300ms
            assertTrue(duration >= 200, "Duration too short, backoff not applied: " + duration + "ms");
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

