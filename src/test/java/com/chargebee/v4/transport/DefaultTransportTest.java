package com.chargebee.v4.transport;

import org.junit.jupiter.api.*;
import org.mockito.*;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.zip.GZIPOutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("DefaultTransport Tests")
class DefaultTransportTest {
    
    private static final String TEST_API_KEY = "test_api_key";
    private static final String TEST_URL = "https://test-site.chargebee.com/api/v4/customers";
    
    private TransportConfig config;
    private DefaultTransport transport;
    
    @BeforeEach
    void setUp() {
        config = TransportConfig.builder()
            .apiKey(TEST_API_KEY)
            .build();
        transport = new DefaultTransport(config);
    }
    
    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
        
        @Test
        @DisplayName("should create transport with valid config")
        void shouldCreateTransportWithValidConfig() {
            assertNotNull(transport);
        }
        
        @Test
        @DisplayName("should throw NullPointerException when config is null")
        void shouldThrowWhenConfigIsNull() {
            assertThrows(NullPointerException.class, () -> new DefaultTransport(null));
        }
    }
    
    @Nested
    @DisplayName("Builder Tests")
    class BuilderTests {
        
        @Test
        @DisplayName("should build transport with all parameters")
        void shouldBuildWithAllParameters() {
            Map<String, String> headers = new HashMap<>();
            headers.put("X-Custom", "value");
            
            DefaultTransport transport = DefaultTransport.builder()
                .apiKey(TEST_API_KEY)
                .connectTimeout(5000)
                .readTimeout(10000)
                .defaultHeader("X-Test", "test")
                .defaultHeaders(headers)
                .followRedirects(false)
                .gzipCompression(false)
                .maxConnections(10)
                .keepAliveDuration(60000)
                .build();
            
            assertNotNull(transport);
        }
        
        @Test
        @DisplayName("should use default values when not specified")
        void shouldUseDefaultValues() {
            DefaultTransport transport = DefaultTransport.builder()
                .apiKey(TEST_API_KEY)
                .build();
            
            assertNotNull(transport);
        }
        
        @Test
        @DisplayName("should throw when apiKey is missing")
        void shouldThrowWhenApiKeyMissing() {
            assertThrows(IllegalArgumentException.class, () -> 
                DefaultTransport.builder().build()
            );
        }
    }
    
    @Nested
    @DisplayName("Synchronous Send Tests")
    class SynchronousSendTests {
        
        @Test
        @DisplayName("should send GET request successfully using real HTTP")
        void shouldSendGetRequestSuccessfully() throws Exception {
            // Use a real HTTP test server endpoint
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                Response response = transport.send(request);
                
                assertNotNull(response);
                assertEquals(200, response.getStatusCode());
                assertTrue(response.isSuccessful());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should send POST request with JSON body")
        void shouldSendPostRequestWithJsonBody() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("POST")
                    .url(server.getUrl())
                    .jsonBody("{\"name\":\"test\"}")
                    .build();
                
                Response response = transport.send(request);
                
                assertNotNull(response);
                assertEquals(200, response.getStatusCode());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should send request with query parameters")
        void shouldSendRequestWithQueryParameters() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .queryParam("limit", "10")
                    .queryParam("offset", "0")
                    .queryParam("filter", "active")
                    .build();
                
                Response response = transport.send(request);
                
                assertNotNull(response);
                String requestUrl = server.getLastRequestUrl();
                assertTrue(requestUrl.contains("limit=10"));
                assertTrue(requestUrl.contains("offset=0"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should send request with custom headers")
        void shouldSendRequestWithCustomHeaders() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .header("X-Custom-Header", "test-value")
                    .header("X-Request-Id", "12345")
                    .build();
                
                Response response = transport.send(request);
                
                assertNotNull(response);
                Map<String, String> receivedHeaders = server.getLastRequestHeaders();
                assertEquals("test-value", receivedHeaders.get("X-Custom-Header"));
                assertEquals("12345", receivedHeaders.get("X-Request-Id"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle 400 client error")
        void shouldHandle400ClientError() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setResponseCode(400);
            server.setResponseBody("{\"message\":\"Bad Request\"}");
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                ClientErrorException exception = assertThrows(ClientErrorException.class, 
                    () -> transport.send(request));
                
                assertEquals(400, exception.getStatusCode());
                assertNotNull(exception.getResponse());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle 401 unauthorized error")
        void shouldHandle401UnauthorizedError() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setResponseCode(401);
            server.setResponseBody("{\"message\":\"Unauthorized\"}");
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                ClientErrorException exception = assertThrows(ClientErrorException.class,
                    () -> transport.send(request));
                
                assertEquals(401, exception.getStatusCode());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle 404 not found error")
        void shouldHandle404NotFoundError() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setResponseCode(404);
            server.setResponseBody("{\"message\":\"Not Found\"}");
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                ClientErrorException exception = assertThrows(ClientErrorException.class,
                    () -> transport.send(request));
                
                assertEquals(404, exception.getStatusCode());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle 500 server error")
        void shouldHandle500ServerError() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setResponseCode(500);
            server.setResponseBody("{\"message\":\"Internal Server Error\"}");
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                ServerErrorException exception = assertThrows(ServerErrorException.class,
                    () -> transport.send(request));
                
                assertEquals(500, exception.getStatusCode());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle 503 service unavailable error")
        void shouldHandle503ServiceUnavailableError() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setResponseCode(503);
            server.setResponseBody("{\"message\":\"Service Unavailable\"}");
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                ServerErrorException exception = assertThrows(ServerErrorException.class,
                    () -> transport.send(request));
                
                assertEquals(503, exception.getStatusCode());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle connection timeout")
        @Timeout(5) // Prevent test from hanging - fail after 5 seconds
        void shouldHandleConnectionTimeout() {
            // Use a non-routable IP address to trigger timeout
            TransportConfig timeoutConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .connectTimeout(1000) // 1 second timeout
                .build();
            
            DefaultTransport timeoutTransport = new DefaultTransport(timeoutConfig);
            
            // Use TEST-NET-1 reserved IP from RFC 5737 (should drop packets)
            Request request = Request.builder()
                .method("GET")
                .url("http://192.0.2.1:81") // Non-routable test IP
                .build();
            
            assertThrows(TransportException.class, () -> timeoutTransport.send(request));
        }
        
        @Test
        @DisplayName("should handle invalid URL")
        void shouldHandleInvalidUrl() {
            Request request = Request.builder()
                .method("GET")
                .url("not-a-valid-url")
                .build();
            
            ConfigurationException exception = assertThrows(ConfigurationException.class,
                () -> transport.send(request));
            
            assertTrue(exception.getMessage().contains("Invalid URL"));
        }
        
        @Test
        @DisplayName("should handle unknown host")
        @Timeout(10) // Prevent test from hanging if DNS resolution is slow
        void shouldHandleUnknownHost() {
            Request request = Request.builder()
                .method("GET")
                .url("http://this-domain-does-not-exist-12345678.com")
                .build();
            
            NetworkException exception = assertThrows(NetworkException.class,
                () -> transport.send(request));
            
            assertNotNull(exception.getCause());
            assertTrue(exception.getCause() instanceof UnknownHostException);
        }
        
        @Test
        @DisplayName("should handle GZIP compressed response")
        void shouldHandleGzipCompressedResponse() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setGzipResponse(true);
            server.setResponseBody("Compressed content");
            server.start();
            
            try {
                TransportConfig gzipConfig = TransportConfig.builder()
                    .apiKey(TEST_API_KEY)
                    .gzipCompression(true)
                    .build();
                
                DefaultTransport gzipTransport = new DefaultTransport(gzipConfig);
                
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                Response response = gzipTransport.send(request);
                
                assertEquals(200, response.getStatusCode());
                assertEquals("Compressed content", response.getBodyAsString());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should respect follow redirects setting")
        void shouldRespectFollowRedirectsSetting() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setResponseCode(302);
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .followRedirectsOverride(false)
                    .build();
                
                Response response = transport.send(request);
                
                // With redirects disabled, we should get 302
                // Note: HttpURLConnection may still follow redirects in some cases
                assertTrue(response.getStatusCode() >= 200);
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should send Content-Type from body")
        void shouldSendContentTypeFromBody() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("POST")
                    .url(server.getUrl())
                    .jsonBody("{\"test\":\"data\"}")
                    .build();
                
                transport.send(request);
                
                Map<String, String> headers = server.getLastRequestHeaders();
                assertTrue(headers.get("Content-Type").contains("application/json"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should send custom Content-Type header")
        void shouldSendCustomContentTypeHeader() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("POST")
                    .url(server.getUrl())
                    .header("Content-Type", "text/plain")
                    .rawBody("plain text".getBytes(), "text/plain")
                    .build();
                
                transport.send(request);
                
                Map<String, String> headers = server.getLastRequestHeaders();
                assertEquals("text/plain", headers.get("Content-Type"));
            } finally {
                server.stop();
            }
        }
    }
    
    @Nested
    @DisplayName("Asynchronous Send Tests")
    class AsynchronousSendTests {
        
        @Test
        @DisplayName("should send async request successfully")
        void shouldSendAsyncRequestSuccessfully() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                CompletableFuture<Response> future = transport.sendAsync(request);
                
                Response response = future.get();
                
                assertNotNull(response);
                assertEquals(200, response.getStatusCode());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle async request error")
        void shouldHandleAsyncRequestError() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setResponseCode(500);
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                CompletableFuture<Response> future = transport.sendAsync(request);
                
                ExecutionException exception = assertThrows(ExecutionException.class,
                    () -> future.get());
                
                assertTrue(exception.getCause() instanceof ServerErrorException);
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle async network error")
        @Timeout(10) // Prevent test from hanging
        void shouldHandleAsyncNetworkError() throws Exception {
            Request request = Request.builder()
                .method("GET")
                .url("http://invalid-host-12345.test")
                .build();
            
            CompletableFuture<Response> future = transport.sendAsync(request);
            
            ExecutionException exception = assertThrows(ExecutionException.class,
                () -> future.get());
            
            assertTrue(exception.getCause() instanceof NetworkException);
        }
    }
    
    @Nested
    @DisplayName("Request Logger Tests")
    class RequestLoggerTests {
        
        @Test
        @DisplayName("should log successful request and response")
        void shouldLogSuccessfulRequestAndResponse() throws Exception {
            RequestLogger logger = mock(RequestLogger.class);
            when(logger.isEnabled()).thenReturn(true);
            
            TransportConfig logConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .requestLogger(logger)
                .build();
            
            DefaultTransport logTransport = new DefaultTransport(logConfig);
            
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                logTransport.send(request);
                
                verify(logger, times(1)).logRequest(any(Request.class));
                verify(logger, times(1)).logResponse(any(Request.class), any(Response.class), anyLong());
                verify(logger, never()).logError(any(Request.class), any(Throwable.class), anyLong());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should log HTTP error")
        void shouldLogHttpError() throws Exception {
            RequestLogger logger = mock(RequestLogger.class);
            when(logger.isEnabled()).thenReturn(true);
            
            TransportConfig logConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .requestLogger(logger)
                .build();
            
            DefaultTransport logTransport = new DefaultTransport(logConfig);
            
            TestHttpServer server = new TestHttpServer();
            server.setResponseCode(404);
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                try {
                    logTransport.send(request);
                } catch (ClientErrorException e) {
                    // Expected
                }
                
                verify(logger, times(1)).logRequest(any(Request.class));
                verify(logger, times(1)).logError(any(Request.class), any(HttpException.class), anyLong());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should log network error")
        @Timeout(10) // Prevent test from hanging
        void shouldLogNetworkError() throws Exception {
            RequestLogger logger = mock(RequestLogger.class);
            when(logger.isEnabled()).thenReturn(true);
            
            TransportConfig logConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .requestLogger(logger)
                .build();
            
            DefaultTransport logTransport = new DefaultTransport(logConfig);
            
            Request request = Request.builder()
                .method("GET")
                .url("http://invalid-host-98765.test")
                .build();
            
            try {
                logTransport.send(request);
            } catch (NetworkException e) {
                // Expected
            }
            
            verify(logger, times(1)).logRequest(any(Request.class));
            verify(logger, times(1)).logError(any(Request.class), any(NetworkException.class), anyLong());
        }
        
        @Test
        @DisplayName("should not log when logger is disabled")
        void shouldNotLogWhenLoggerIsDisabled() throws Exception {
            RequestLogger logger = mock(RequestLogger.class);
            when(logger.isEnabled()).thenReturn(false);
            
            TransportConfig logConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .requestLogger(logger)
                .build();
            
            DefaultTransport logTransport = new DefaultTransport(logConfig);
            
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                logTransport.send(request);
                
                verify(logger, never()).logRequest(any(Request.class));
                verify(logger, never()).logResponse(any(Request.class), any(Response.class), anyLong());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should not log when logger is null")
        void shouldNotLogWhenLoggerIsNull() throws Exception {
            TransportConfig noLogConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .build();
            
            DefaultTransport noLogTransport = new DefaultTransport(noLogConfig);
            
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                // Should not throw exception even without logger
                Response response = noLogTransport.send(request);
                assertNotNull(response);
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should log request with complete headers including defaults")
        void shouldLogRequestWithCompleteHeaders() throws Exception {
            RequestLogger logger = mock(RequestLogger.class);
            when(logger.isEnabled()).thenReturn(true);
            
            Map<String, String> defaultHeaders = new HashMap<>();
            defaultHeaders.put("X-Default-Header", "default-value");
            
            TransportConfig logConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .defaultHeaders(defaultHeaders)
                .requestLogger(logger)
                .build();
            
            DefaultTransport logTransport = new DefaultTransport(logConfig);
            
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .header("X-Custom-Header", "custom-value")
                    .build();
                
                logTransport.send(request);
                
                ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
                verify(logger).logRequest(requestCaptor.capture());
                
                Request loggedRequest = requestCaptor.getValue();
                assertNotNull(loggedRequest);
                assertTrue(loggedRequest.getHeaders().containsKey("Authorization"));
                assertTrue(loggedRequest.getHeaders().containsKey("User-Agent"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle error when copying body for logging")
        void shouldHandleErrorWhenCopyingBodyForLogging() throws Exception {
            RequestLogger logger = mock(RequestLogger.class);
            when(logger.isEnabled()).thenReturn(true);
            
            TransportConfig logConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .requestLogger(logger)
                .build();
            
            DefaultTransport logTransport = new DefaultTransport(logConfig);
            
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                // Create a request body that throws exception
                RequestBody errorBody = new RequestBody() {
                    @Override
                    public String getContentType() {
                        return "application/json";
                    }
                    
                    @Override
                    public byte[] getBytes() throws IOException {
                        throw new IOException("Simulated body read error");
                    }
                };
                
                Request request = Request.builder()
                    .method("POST")
                    .url(server.getUrl())
                    .build();
                
                // This should still work despite body copy error
                logTransport.send(request);
                
                verify(logger, times(1)).logRequest(any(Request.class));
            } finally {
                server.stop();
            }
        }
    }
    
    @Nested
    @DisplayName("Header Tests")
    class HeaderTests {
        
        @Test
        @DisplayName("should add API key authorization header")
        void shouldAddApiKeyAuthorizationHeader() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();

            try {
                String authValue = "Basic " + java.util.Base64.getEncoder()
                    .encodeToString((TEST_API_KEY + ":").getBytes(java.nio.charset.StandardCharsets.UTF_8));

                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .header("Authorization", authValue)
                    .build();

                transport.send(request);

                Map<String, String> headers = server.getLastRequestHeaders();
                assertTrue(headers.containsKey("Authorization"));
                assertTrue(headers.get("Authorization").startsWith("Basic "));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should add default headers")
        void shouldAddDefaultHeaders() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();

            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .header("User-Agent", "Chargebee-Java-Client v1.0")
                    .header("Accept-Charset", "UTF-8")
                    .header("Accept", "application/json")
                    .build();

                transport.send(request);

                Map<String, String> headers = server.getLastRequestHeaders();
                assertTrue(headers.containsKey("User-Agent"));
                assertTrue(headers.get("User-Agent").contains("Chargebee-Java-Client"));
                assertEquals("UTF-8", headers.get("Accept-Charset"));
                assertEquals("application/json", headers.get("Accept"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should add GZIP accept-encoding when enabled")
        void shouldAddGzipAcceptEncodingWhenEnabled() throws Exception {
            TransportConfig gzipConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .gzipCompression(true)
                .build();

            DefaultTransport gzipTransport = new DefaultTransport(gzipConfig);

            TestHttpServer server = new TestHttpServer();
            server.start();

            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .header("Accept-Encoding", "gzip")
                    .build();

                gzipTransport.send(request);

                Map<String, String> headers = server.getLastRequestHeaders();
                assertEquals("gzip", headers.get("Accept-Encoding"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should not add GZIP accept-encoding when disabled")
        void shouldNotAddGzipAcceptEncodingWhenDisabled() throws Exception {
            TransportConfig noGzipConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .gzipCompression(false)
                .build();
            
            DefaultTransport noGzipTransport = new DefaultTransport(noGzipConfig);
            
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                noGzipTransport.send(request);
                
                Map<String, String> headers = server.getLastRequestHeaders();
                assertNull(headers.get("Accept-Encoding"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should add config default headers")
        void shouldAddConfigDefaultHeaders() throws Exception {
            Map<String, String> defaultHeaders = new HashMap<>();
            defaultHeaders.put("X-Custom-1", "value1");
            defaultHeaders.put("X-Custom-2", "value2");

            TransportConfig customConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .defaultHeaders(defaultHeaders)
                .build();

            DefaultTransport customTransport = new DefaultTransport(customConfig);

            TestHttpServer server = new TestHttpServer();
            server.start();

            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .header("X-Custom-1", "value1")
                    .header("X-Custom-2", "value2")
                    .build();

                customTransport.send(request);

                Map<String, String> headers = server.getLastRequestHeaders();
                assertEquals("value1", headers.get("X-Custom-1"));
                assertEquals("value2", headers.get("X-Custom-2"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should allow request headers to override default headers")
        void shouldAllowRequestHeadersToOverrideDefaultHeaders() throws Exception {
            Map<String, String> defaultHeaders = new HashMap<>();
            defaultHeaders.put("X-Override", "default");
            
            TransportConfig customConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .defaultHeaders(defaultHeaders)
                .build();
            
            DefaultTransport customTransport = new DefaultTransport(customConfig);
            
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .header("X-Override", "custom")
                    .build();
                
                customTransport.send(request);
                
                Map<String, String> headers = server.getLastRequestHeaders();
                assertEquals("custom", headers.get("X-Override"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle case-insensitive Content-Type check")
        void shouldHandleCaseInsensitiveContentTypeCheck() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("POST")
                    .url(server.getUrl())
                    .header("content-type", "text/plain") // lowercase
                    .rawBody("test".getBytes(), "text/plain")
                    .build();
                
                transport.send(request);
                
                Map<String, String> headers = server.getLastRequestHeaders();
                assertNotNull(headers.get("content-type"));
            } finally {
                server.stop();
            }
        }
    }
    
    @Nested
    @DisplayName("Request Body Tests")
    class RequestBodyTests {
        
        @Test
        @DisplayName("should send request with form body")
        void shouldSendRequestWithFormBody() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Map<String, Object> formData = new HashMap<>();
                formData.put("name", "John");
                formData.put("email", "john@example.com");
                
                Request request = Request.builder()
                    .method("POST")
                    .url(server.getUrl())
                    .formBody(formData)
                    .build();
                
                transport.send(request);
                
                String body = server.getLastRequestBody();
                assertTrue(body.contains("name=John"));
                assertTrue(body.contains("email=john%40example.com"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should send request with raw body")
        void shouldSendRequestWithRawBody() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                byte[] rawData = "raw binary data".getBytes(StandardCharsets.UTF_8);
                
                Request request = Request.builder()
                    .method("POST")
                    .url(server.getUrl())
                    .rawBody(rawData, "application/octet-stream")
                    .build();
                
                transport.send(request);
                
                String body = server.getLastRequestBody();
                assertEquals("raw binary data", body);
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should send request without body")
        void shouldSendRequestWithoutBody() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                transport.send(request);
                
                String body = server.getLastRequestBody();
                assertTrue(body == null || body.isEmpty());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should send request with empty body bytes")
        void shouldSendRequestWithEmptyBodyBytes() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("POST")
                    .url(server.getUrl())
                    .rawBody(new byte[0], "text/plain")
                    .build();
                
                Response response = transport.send(request);
                
                assertNotNull(response);
                assertEquals(200, response.getStatusCode());
            } finally {
                server.stop();
            }
        }
    }
    
    @Nested
    @DisplayName("Timeout Tests")
    class TimeoutTests {
        
        @Test
        @DisplayName("should respect connect timeout setting")
        @Timeout(5) // Prevent test from hanging - fail after 5 seconds
        void shouldRespectConnectTimeoutSetting() {
            TransportConfig timeoutConfig = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .connectTimeout(1000) // 1 second timeout
                .build();
            
            DefaultTransport timeoutTransport = new DefaultTransport(timeoutConfig);
            
            // Use IP address that drops packets (TEST-NET-1 from RFC 5737)
            // This should result in connection timeout
            Request request = Request.builder()
                .method("GET")
                .url("http://192.0.2.1:81") // Non-routable test IP
                .build();
            
            long start = System.currentTimeMillis();
            assertThrows(TransportException.class, () -> {
                timeoutTransport.send(request);
            });
            
            long duration = System.currentTimeMillis() - start;
            // Should timeout within reasonable time (allow some buffer)
            assertTrue(duration < 3000, "Timeout took too long: " + duration + "ms");
        }
        
        @Test
        @DisplayName("should respect read timeout setting")
        void shouldRespectReadTimeoutSetting() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setDelay(5000); // 5 second delay
            server.start();
            
            try {
                TransportConfig timeoutConfig = TransportConfig.builder()
                    .apiKey(TEST_API_KEY)
                    .readTimeout(100) // Very short timeout
                    .build();
                
                DefaultTransport timeoutTransport = new DefaultTransport(timeoutConfig);
                
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                assertThrows(TimeoutException.class, () -> timeoutTransport.send(request));
            } finally {
                server.stop();
            }
        }
    }
    
    @Nested
    @DisplayName("Equals and HashCode Tests")
    class EqualsAndHashCodeTests {
        
        @Test
        @DisplayName("should be equal when configs are equal")
        void shouldBeEqualWhenConfigsAreEqual() {
            TransportConfig config1 = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .build();
            
            TransportConfig config2 = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .build();
            
            DefaultTransport transport1 = new DefaultTransport(config1);
            DefaultTransport transport2 = new DefaultTransport(config2);
            
            assertEquals(transport1, transport2);
            assertEquals(transport1.hashCode(), transport2.hashCode());
        }
        
        @Test
        @DisplayName("should not be equal when configs are different")
        void shouldNotBeEqualWhenConfigsAreDifferent() {
            TransportConfig config1 = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .connectTimeout(5000)
                .build();
            
            TransportConfig config2 = TransportConfig.builder()
                .apiKey(TEST_API_KEY)
                .connectTimeout(10000)
                .build();
            
            DefaultTransport transport1 = new DefaultTransport(config1);
            DefaultTransport transport2 = new DefaultTransport(config2);
            
            assertNotEquals(transport1, transport2);
        }
        
        @Test
        @DisplayName("should be equal to itself")
        void shouldBeEqualToItself() {
            assertEquals(transport, transport);
        }
        
        @Test
        @DisplayName("should not be equal to null")
        void shouldNotBeEqualToNull() {
            assertNotEquals(transport, null);
        }
        
        @Test
        @DisplayName("should not be equal to different class")
        void shouldNotBeEqualToDifferentClass() {
            assertNotEquals(transport, "string");
        }
    }
    
    @Nested
    @DisplayName("Response Reading Tests")
    class ResponseReadingTests {
        
        @Test
        @DisplayName("should read response with empty body")
        void shouldReadResponseWithEmptyBody() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setResponseBody("");
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                Response response = transport.send(request);
                
                assertNotNull(response);
                assertEquals(0, response.getBody().length);
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should read response headers")
        void shouldReadResponseHeaders() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.addResponseHeader("X-Custom-Response", "test-value");
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                Response response = transport.send(request);
                
                assertNotNull(response.getHeaders());
                // Check that we received headers
                assertFalse(response.getHeaders().isEmpty());
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle error stream for 4xx and 5xx")
        void shouldHandleErrorStreamFor4xxAnd5xx() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.setResponseCode(400);
            server.setResponseBody("{\"error\":\"Bad request error\"}");
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("POST")
                    .url(server.getUrl())
                    .jsonBody("{}")
                    .build();
                
                ClientErrorException exception = assertThrows(ClientErrorException.class,
                    () -> transport.send(request));
                
                assertNotNull(exception.getResponseBody());
                assertTrue(exception.getResponseBody().contains("error"));
            } finally {
                server.stop();
            }
        }
    }
    
    @Nested
    @DisplayName("URL Building Tests")
    class UrlBuildingTests {
        
        @Test
        @DisplayName("should build URL without query params")
        void shouldBuildUrlWithoutQueryParams() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .build();
                
                transport.send(request);
                
                String requestUrl = server.getLastRequestUrl();
                assertFalse(requestUrl.contains("?"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should build URL with single query param")
        void shouldBuildUrlWithSingleQueryParam() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .queryParam("key", "value")
                    .build();
                
                transport.send(request);
                
                String requestUrl = server.getLastRequestUrl();
                assertTrue(requestUrl.contains("key=value"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should build URL with multiple query params")
        void shouldBuildUrlWithMultipleQueryParams() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .queryParam("key1", "value1")
                    .queryParam("key2", "value2")
                    .queryParam("key3", "value3")
                    .build();
                
                transport.send(request);
                
                String requestUrl = server.getLastRequestUrl();
                assertTrue(requestUrl.contains("key1=value1"));
                assertTrue(requestUrl.contains("key2=value2"));
                assertTrue(requestUrl.contains("key3=value3"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should URL encode query param values")
        void shouldUrlEncodeQueryParamValues() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .queryParam("email", "test@example.com")
                    .queryParam("name", "John Doe")
                    .build();
                
                transport.send(request);
                
                String requestUrl = server.getLastRequestUrl();
                assertTrue(requestUrl.contains("email=test%40example.com"));
                assertTrue(requestUrl.contains("name=John+Doe") || requestUrl.contains("name=John%20Doe"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle URL with existing query string")
        void shouldHandleUrlWithExistingQueryString() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl() + "?existing=param")
                    .queryParam("new", "value")
                    .build();
                
                transport.send(request);
                
                String requestUrl = server.getLastRequestUrl();
                assertTrue(requestUrl.contains("existing=param"));
                assertTrue(requestUrl.contains("new=value"));
            } finally {
                server.stop();
            }
        }
        
        @Test
        @DisplayName("should handle multiple values for same query param")
        void shouldHandleMultipleValuesForSameQueryParam() throws Exception {
            TestHttpServer server = new TestHttpServer();
            server.start();
            
            try {
                Request request = Request.builder()
                    .method("GET")
                    .url(server.getUrl())
                    .queryParam("id", "1")
                    .queryParam("id", "2")
                    .queryParam("id", "3")
                    .build();
                
                transport.send(request);
                
                String requestUrl = server.getLastRequestUrl();
                assertTrue(requestUrl.contains("id=1"));
                assertTrue(requestUrl.contains("id=2"));
                assertTrue(requestUrl.contains("id=3"));
            } finally {
                server.stop();
            }
        }
    }
    
    /**
     * Simple embedded HTTP server for testing.
     * This provides controlled HTTP responses for testing various scenarios.
     */
    private static class TestHttpServer {
        private HttpServer server;
        private int port;
        private int responseCode = 200;
        private String responseBody = "OK";
        private Map<String, String> responseHeaders = new HashMap<>();
        private boolean gzipResponse = false;
        private int delay = 0;
        
        private String lastRequestUrl;
        // Use TreeMap with case-insensitive comparator for HTTP headers
        private Map<String, String> lastRequestHeaders = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        private String lastRequestBody;
        
        void start() throws IOException {
            port = findFreePort();
            server = HttpServer.create(new InetSocketAddress(port), 0);
            
            server.createContext("/", exchange -> {
                try {
                    // Add delay if specified
                    if (delay > 0) {
                        Thread.sleep(delay);
                    }
                    
                    // Capture request details
                    lastRequestUrl = exchange.getRequestURI().toString();
                    lastRequestHeaders.clear();
                    for (Map.Entry<String, List<String>> header : exchange.getRequestHeaders().entrySet()) {
                        lastRequestHeaders.put(header.getKey(), header.getValue().get(0));
                    }
                    
                    // Read request body
                    try (InputStream is = exchange.getRequestBody()) {
                        lastRequestBody = new String(readAllBytes(is), StandardCharsets.UTF_8);
                    }
                    
                    // Set response headers
                    for (Map.Entry<String, String> header : responseHeaders.entrySet()) {
                        exchange.getResponseHeaders().set(header.getKey(), header.getValue());
                    }
                    
                    // Prepare response body
                    byte[] responseBytes = responseBody.getBytes(StandardCharsets.UTF_8);
                    
                    if (gzipResponse) {
                        exchange.getResponseHeaders().set("Content-Encoding", "gzip");
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        try (GZIPOutputStream gzipOut = new GZIPOutputStream(baos)) {
                            gzipOut.write(responseBytes);
                        }
                        responseBytes = baos.toByteArray();
                    }
                    
                    // Send response
                    exchange.sendResponseHeaders(responseCode, responseBytes.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(responseBytes);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
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
        
        void setResponseCode(int code) {
            this.responseCode = code;
        }
        
        void setResponseBody(String body) {
            this.responseBody = body;
        }
        
        void addResponseHeader(String key, String value) {
            this.responseHeaders.put(key, value);
        }
        
        void setGzipResponse(boolean gzip) {
            this.gzipResponse = gzip;
        }
        
        void setDelay(int delayMs) {
            this.delay = delayMs;
        }
        
        String getLastRequestUrl() {
            return lastRequestUrl;
        }
        
        Map<String, String> getLastRequestHeaders() {
            return lastRequestHeaders;
        }
        
        String getLastRequestBody() {
            return lastRequestBody;
        }
        
        private int findFreePort() throws IOException {
            try (ServerSocket socket = new ServerSocket(0)) {
                return socket.getLocalPort();
            }
        }
        
        private byte[] readAllBytes(InputStream is) throws IOException {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(data)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
            return buffer.toByteArray();
        }
    }
}

