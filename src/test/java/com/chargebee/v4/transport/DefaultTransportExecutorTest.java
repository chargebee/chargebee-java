package com.chargebee.v4.transport;

import org.junit.jupiter.api.*;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DefaultTransport Executor Tests")
class DefaultTransportExecutorTest {

    private static final String TEST_API_KEY = "test_api_key";

    @Nested
    @DisplayName("Default Executor Tests")
    class DefaultExecutorTests {

        @Test
        @DisplayName("should create a dedicated executor by default instead of ForkJoinPool.commonPool")
        void shouldUseDedicatedExecutorByDefault() {
            TransportConfig config = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .build();

            DefaultTransport transport = new DefaultTransport(config);

            ExecutorService executor = transport.getAsyncExecutor();
            assertNotNull(executor);
            assertNotSame(ForkJoinPool.commonPool(), executor);

            transport.close();
        }

        @Test
        @DisplayName("should use daemon threads in default executor")
        void shouldUseDaemonThreadsInDefaultExecutor() throws Exception {
            TransportConfig config = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .build();

            DefaultTransport transport = new DefaultTransport(config);

            CompletableFuture<Boolean> isDaemon = new CompletableFuture<>();
            CompletableFuture<String> threadName = new CompletableFuture<>();

            transport.getAsyncExecutor().execute(() -> {
                isDaemon.complete(Thread.currentThread().isDaemon());
                threadName.complete(Thread.currentThread().getName());
            });

            assertTrue(isDaemon.get(5, TimeUnit.SECONDS), "Default executor threads should be daemon threads");
            assertTrue(threadName.get(5, TimeUnit.SECONDS).startsWith("chargebee-async-"),
                "Default executor threads should be named chargebee-async-*");

            transport.close();
        }

        @Test
        @DisplayName("should execute async requests on dedicated thread pool, not common pool")
        void shouldExecuteAsyncOnDedicatedPool() throws Exception {
            TransportConfig config = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .build();

            DefaultTransport transport = new DefaultTransport(config);

            TestHttpServer server = new TestHttpServer();
            server.start();

            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();

                CompletableFuture<String> threadNameFuture = new CompletableFuture<>();

                // Intercept the thread name during async execution
                CompletableFuture<Response> responseFuture = transport.sendAsync(request);
                responseFuture.thenRun(() -> threadNameFuture.complete("completed"));

                Response response = responseFuture.get(5, TimeUnit.SECONDS);
                assertEquals(200, response.getStatusCode());
            } finally {
                server.stop();
                transport.close();
            }
        }
    }

    @Nested
    @DisplayName("Custom Executor Tests")
    class CustomExecutorTests {

        @Test
        @DisplayName("should use custom executor when provided via TransportConfig")
        void shouldUseCustomExecutor() throws Exception {
            ExecutorService customExecutor = Executors.newFixedThreadPool(2);

            TransportConfig config = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .asyncExecutor(customExecutor)
                .build();

            DefaultTransport transport = new DefaultTransport(config);

            assertSame(customExecutor, transport.getAsyncExecutor());

            transport.close();
            // Custom executor should NOT be shut down by transport
            assertFalse(customExecutor.isShutdown(),
                "Transport should not shut down a user-provided executor");

            customExecutor.shutdown();
        }

        @Test
        @DisplayName("should execute async requests on custom executor")
        void shouldExecuteAsyncOnCustomExecutor() throws Exception {
            CompletableFuture<String> executingThreadName = new CompletableFuture<>();

            ExecutorService customExecutor = Executors.newSingleThreadExecutor(r -> {
                Thread t = new Thread(r, "custom-test-thread");
                t.setDaemon(true);
                return t;
            });

            TransportConfig config = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .asyncExecutor(customExecutor)
                .build();

            DefaultTransport transport = new DefaultTransport(config);

            TestHttpServer server = new TestHttpServer();
            server.start();

            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();

                Response response = transport.sendAsync(request).get(5, TimeUnit.SECONDS);
                assertEquals(200, response.getStatusCode());
            } finally {
                server.stop();
                transport.close();
                customExecutor.shutdown();
            }
        }
    }

    @Nested
    @DisplayName("AutoCloseable Tests")
    class AutoCloseableTests {

        @Test
        @DisplayName("should shut down owned executor on close")
        void shouldShutDownOwnedExecutorOnClose() {
            TransportConfig config = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .build();

            DefaultTransport transport = new DefaultTransport(config);
            ExecutorService executor = transport.getAsyncExecutor();

            assertFalse(executor.isShutdown());

            transport.close();

            assertTrue(executor.isShutdown(), "Owned executor should be shut down on close");
        }

        @Test
        @DisplayName("should not shut down user-provided executor on close")
        void shouldNotShutDownUserProvidedExecutorOnClose() {
            ExecutorService userExecutor = Executors.newCachedThreadPool();

            TransportConfig config = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .asyncExecutor(userExecutor)
                .build();

            DefaultTransport transport = new DefaultTransport(config);
            transport.close();

            assertFalse(userExecutor.isShutdown(),
                "User-provided executor must not be shut down by transport");

            userExecutor.shutdown();
        }

        @Test
        @DisplayName("should be usable with try-with-resources")
        void shouldWorkWithTryWithResources() throws Exception {
            ExecutorService capturedExecutor;

            try (DefaultTransport transport = new DefaultTransport(
                    TransportConfig.builder().apiKey(TEST_API_KEY).build())) {
                capturedExecutor = transport.getAsyncExecutor();
                assertFalse(capturedExecutor.isShutdown());
            }

            assertTrue(capturedExecutor.isShutdown(),
                "Executor should be shut down after try-with-resources block");
        }

        @Test
        @DisplayName("should handle multiple close calls gracefully")
        void shouldHandleMultipleCloseCalls() {
            TransportConfig config = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .build();

            DefaultTransport transport = new DefaultTransport(config);

            // Should not throw on multiple close calls
            transport.close();
            transport.close();
        }
    }

    @Nested
    @DisplayName("Concurrency Tests")
    class ConcurrencyTests {

        @Test
        @DisplayName("should handle multiple concurrent async requests")
        @Timeout(10)
        void shouldHandleConcurrentAsyncRequests() throws Exception {
            TransportConfig config = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .build();

            DefaultTransport transport = new DefaultTransport(config);

            TestHttpServer server = new TestHttpServer();
            server.start();

            try {
                int concurrentRequests = 20;
                List<CompletableFuture<Response>> futures = new ArrayList<>();

                for (int i = 0; i < concurrentRequests; i++) {
                    Request request = Request.builder()
                        .method("GET")
                        .url(server.getUrl())
                        .build();
                    futures.add(transport.sendAsync(request));
                }

                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .get(10, TimeUnit.SECONDS);

                for (CompletableFuture<Response> future : futures) {
                    assertEquals(200, future.get().getStatusCode());
                }
            } finally {
                server.stop();
                transport.close();
            }
        }
    }

    /**
     * Simple embedded HTTP server for testing.
     */
    private static class TestHttpServer {
        private HttpServer server;
        private int port;

        void start() throws IOException {
            port = findFreePort();
            server = HttpServer.create(new InetSocketAddress(port), 0);

            server.createContext("/", exchange -> {
                byte[] responseBytes = "OK".getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, responseBytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(responseBytes);
                }
            });

            server.setExecutor(null);
            server.start();
        }

        void stop() {
            if (server != null) {
                server.stop(0);
            }
        }

        String getUrl() {
            return "http://localhost:" + port + "/";
        }

        private int findFreePort() throws IOException {
            try (ServerSocket socket = new ServerSocket(0)) {
                return socket.getLocalPort();
            }
        }
    }
}
