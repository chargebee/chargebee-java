package com.chargebee.v4.client;

import com.chargebee.v4.client.request.RequestContext;
import com.chargebee.v4.client.request.RequestInterceptor;
import com.chargebee.v4.client.request.RequestWrap;
import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.exceptions.ConfigurationException;
import com.chargebee.v4.exceptions.NetworkException;
import com.chargebee.v4.exceptions.TimeoutException;
import com.chargebee.v4.exceptions.TransportException;
import com.chargebee.v4.internal.RetryConfig;
import com.chargebee.v4.transport.*;
import com.chargebee.v4.transport.Transport;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Immutable, thread-safe Chargebee API client with pluggable transport.
 * Use the builder to create instances with custom configuration.
 * 
 * <pre>{@code
 * ChargebeeClient client = ChargebeeClient.builder("cb_test_...", "acme")
 *     .build();
 * }</pre>
 */
public final class ChargebeeClient extends ClientMethodsImpl {
    private final String apiKey;
    private final String siteName;
    private final String endpoint;
    private final Transport transport;
    private final RetryConfig retry;
    private final int connectTimeout;
    private final int readTimeout;
    private final String domainSuffix;
    private final String protocol;
    private final RequestInterceptor requestInterceptor;
    private final RequestContext clientHeaders;
    
    // Auto-generated service registry for lazy loading
    private final ServiceRegistry serviceRegistry;

    private ChargebeeClient(Builder builder) {
        this.apiKey = builder.apiKey;
        this.siteName = builder.siteName;
        this.endpoint = builder.endpoint;
        this.transport = builder.transport;
        this.retry = builder.retry;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.domainSuffix = builder.domainSuffix;
        this.protocol = builder.protocol;
        this.requestInterceptor = builder.requestInterceptor;
        this.clientHeaders = new RequestContext(builder.clientHeaders.getHeaders());
        this.serviceRegistry = new ServiceRegistry(this);
    }

    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Create a new builder with the specified API key and site name.
     * 
     * @param apiKey the Chargebee API key
     * @param siteName the Chargebee site name
     * @return new builder instance
     */
    public static Builder builder(String apiKey, String siteName) {
        return new Builder().apiKey(apiKey).siteName(siteName);
    }

    // Getters (immutable, so safe)
    public String getApiKey() { return apiKey; }
    public String getSiteName() { return siteName; }
    public String getEndpoint() { return endpoint; }
    public Transport getTransport() { return transport; }
    public RetryConfig getRetry() { return retry; }
    public int getConnectTimeout() { return connectTimeout; }
    public int getReadTimeout() { return readTimeout; }
    public String getDomainSuffix() { return domainSuffix; }
    public String getProtocol() { return protocol; }
    public RequestInterceptor getRequestInterceptor() { return requestInterceptor; }
    public RequestContext getClientHeaders() { return clientHeaders; }
    
    // (Header decoration removed from public API)
    
    // Resource Services - Auto-generated via ClientMethodsImpl
    
    @Override
    protected ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }
    
    /**
     * Get the base URL for API requests.
     * Either uses the configured endpoint or constructs from siteName + domainSuffix.
     */
    public String getBaseUrl() {
        if (endpoint != null && !endpoint.trim().isEmpty()) {
            return endpoint;
        }
        return String.format("%s://%s.%s/api/v2", protocol, siteName, domainSuffix);
    }
    
    /**
     * Send a GET request to the specified path.
     * 
     * @param path the API path (e.g., "/customers")
     * @param queryParams optional query parameters
     * @return the HTTP response
     * @throws ChargebeeException for network, timeout, configuration failures, or interceptor errors
     */
    public Response get(String path, Map<String, List<String>> queryParams) throws ChargebeeException {
        Map<String, Object> objectParams = new HashMap<>(queryParams);
        String fullUrl = UrlBuilder.buildUrl(getBaseUrl(), path, objectParams);
        Request.Builder builder = Request.builder()
                .method("GET")
                .url(fullUrl);

        for (Map.Entry<String, String> header : clientHeaders.getHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }

        return executeWithInterceptor(builder.build());
    }
    
    /**
     * Send a GET request to the specified path.
     * 
     * @param path the API path (e.g., "/customers")
     * @return the HTTP response
     * @throws ChargebeeException for network, timeout, configuration failures, or interceptor errors
     */
    public Response get(String path) throws ChargebeeException {
        return get(path, Collections.emptyMap());
    }
    
    /**
     * Send a GET request to the specified path asynchronously.
     * 
     * @param path the API path (e.g., "/customers")
     * @param queryParams optional query parameters
     * @return a CompletableFuture that will complete with the HTTP response
     */
    public CompletableFuture<Response> getAsync(String path, Map<String, List<String>> queryParams) {
        Map<String, Object> objectParams = new HashMap<>(queryParams);
        String fullUrl = UrlBuilder.buildUrl(getBaseUrl(), path, objectParams);
        Request.Builder builder = Request.builder()
                .method("GET")
                .url(fullUrl);

        for (Map.Entry<String, String> header : clientHeaders.getHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }

        return executeWithInterceptorAsync(builder.build());
    }
    
    /**
     * Send a GET request to the specified path asynchronously.
     * 
     * @param path the API path (e.g., "/customers")
     * @return a CompletableFuture that will complete with the HTTP response
     */
    public CompletableFuture<Response> getAsync(String path) {
        return getAsync(path, Collections.emptyMap());
    }
    
    /**
     * Send a POST request to the specified path with form data.
     * 
     * @param path the API path (e.g., "/customers")
     * @param formData the form data to send
     * @return the HTTP response
     * @throws ChargebeeException for network, timeout, configuration failures, or interceptor errors
     */
    public Response post(String path, Map<String, Object> formData) throws ChargebeeException {
        String fullUrl = UrlBuilder.buildUrl(getBaseUrl(), path, null);
        Request.Builder builder = Request.builder()
                .method("POST")
                .url(fullUrl)
                .formBody(formData);

        for (Map.Entry<String, String> header : clientHeaders.getHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }

        return executeWithInterceptor(builder.build());
    }
    
    /**
     * Send a POST request to the specified path with JSON data.
     * 
     * @param path the API path (e.g., "/customers")
     * @param jsonData the JSON data to send
     * @return the HTTP response
     * @throws ChargebeeException for network, timeout, configuration failures, or interceptor errors
     */
    public Response postJson(String path, String jsonData) throws ChargebeeException {
        String fullUrl = UrlBuilder.buildUrl(getBaseUrl(), path, null);
        Request.Builder builder = Request.builder()
                .method("POST")
                .url(fullUrl)
                .jsonBody(jsonData);

        for (Map.Entry<String, String> header : clientHeaders.getHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }

        return executeWithInterceptor(builder.build());
    }
    
    /**
     * Send a POST request to the specified path with form data asynchronously.
     * 
     * @param path the API path (e.g., "/customers")
     * @param formData the form data to send
     * @return a CompletableFuture that will complete with the HTTP response
     */
    public CompletableFuture<Response> postAsync(String path, Map<String, Object> formData) {
        String fullUrl = UrlBuilder.buildUrl(getBaseUrl(), path, null);
        Request.Builder builder = Request.builder()
                .method("POST")
                .url(fullUrl)
                .formBody(formData);

        for (Map.Entry<String, String> header : clientHeaders.getHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }

        return executeWithInterceptorAsync(builder.build());
    }
    
    /**
     * Send a POST request to the specified path with JSON data asynchronously.
     * 
     * @param path the API path (e.g., "/customers")
     * @param jsonData the JSON data to send
     * @return a CompletableFuture that will complete with the HTTP response
     */
    public CompletableFuture<Response> postJsonAsync(String path, String jsonData) {
        String fullUrl = UrlBuilder.buildUrl(getBaseUrl(), path, null);
        Request.Builder builder = Request.builder()
                .method("POST")
                .url(fullUrl)
                .jsonBody(jsonData);

        for (Map.Entry<String, String> header : clientHeaders.getHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }

        return executeWithInterceptorAsync(builder.build());
    }
    
    /**
     * Execute a request with optional interceptor.
     */
    public Response executeWithInterceptor(Request request) throws ChargebeeException {
        if (requestInterceptor != null) {
            RequestWrap requestWrap = new RequestWrap(this, request);
            return requestInterceptor.handleRequest(requestWrap);
        } else {
            return sendWithRetry(request);
        }
    }
    
    /**
     * Execute a request asynchronously with optional interceptor.
     */
    public CompletableFuture<Response> executeWithInterceptorAsync(Request request) {
        if (requestInterceptor != null) {
            RequestWrap requestWrap = new RequestWrap(this, request);
            return requestInterceptor.handleRequestAsync(requestWrap);
        } else {
            return sendWithRetryAsync(request);
        }
    }
    
    /**
     * Send a request with retry logic based on the configured RetryConfig.
     */
    public Response sendWithRetry(Request request) {
        Request enrichedRequest = addDefaultHeaders(request);

        Integer overrideRetries = enrichedRequest.getMaxNetworkRetriesOverride();
        boolean retriesEnabled = retry.isEnabled() || (overrideRetries != null && overrideRetries >= 0);
        if (!retriesEnabled) {
            return transport.send(enrichedRequest);
        }

        TransportException lastException = null;
        int attempts = 0;
        int maxRetries = (overrideRetries != null ? overrideRetries : retry.getMaxRetries());

        while (attempts <= maxRetries) {
            try {
                Request requestToSend = addRetryAttemptHeader(enrichedRequest, attempts);
                Response response = transport.send(requestToSend);

                if (attempts < maxRetries && shouldRetry(response.getStatusCode())) {
                    attempts++;
                    sleep(calculateBackoffDelay(attempts));
                    continue;
                }

                return response;
            } catch (TimeoutException | NetworkException e) {
                lastException = e;
                attempts++;
                if (attempts <= maxRetries) {
                    sleep(calculateBackoffDelay(attempts));
                } else {
                    break;
                }
            } catch (TransportException e) {
                throw e;
            }
        }

        throw lastException != null ? lastException :
            new TransportException("Request failed after " + attempts + " attempts");
    }
    
    private Request addDefaultHeaders(Request request) {
        Request.Builder builder = Request.builder()
            .method(request.getMethod())
            .url(request.getUrl());

        if (!request.getQueryParams().isEmpty()) {
            builder.queryParams(request.getQueryParams());
        }

        if (request.getBody() != null) {
            try {
                byte[] bodyBytes = request.getBody().getBytes();
                builder.rawBody(bodyBytes, request.getBody().getContentType());
            } catch (Exception e) {
                throw new RuntimeException("Failed to copy request body", e);
            }
        }

        if (request.getMaxNetworkRetriesOverride() != null) {
            builder.maxNetworkRetriesOverride(request.getMaxNetworkRetriesOverride());
        }

        if (request.getFollowRedirectsOverride() != null) {
            builder.followRedirectsOverride(request.getFollowRedirectsOverride());
        }

        addStandardHeaders(builder);

        for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }

        return builder.build();
    }

    private void addStandardHeaders(Request.Builder builder) {
        if (apiKey != null) {
            String authValue = "Basic " + java.util.Base64.getEncoder()
                .encodeToString((apiKey + ":").getBytes(java.nio.charset.StandardCharsets.UTF_8))
                .replaceAll("\r", "").replaceAll("\n", "");
            builder.header("Authorization", authValue);
        }

        builder.header("Accept-Charset", "UTF-8");
        builder.header("User-Agent", "Chargebee-Java-Client v" + getVersion());
        builder.header("Accept", "application/json");
        builder.header("OS-Version", String.format("%s %s %s",
            System.getProperty("os.name"),
            System.getProperty("os.arch"),
            System.getProperty("os.version")));
        builder.header("Lang-Version", System.getProperty("java.version"));

        if (transport instanceof DefaultTransport) {
            TransportConfig config = ((DefaultTransport) transport).getConfig();
            if (config != null && config.isEnableGzipCompression()) {
                builder.header("Accept-Encoding", "gzip");
            }
            if (config != null) {
                for (Map.Entry<String, String> header : config.getDefaultHeaders().entrySet()) {
                    builder.header(header.getKey(), header.getValue());
                }
            }
        }
    }

    private String getVersion() {
        try (java.io.InputStream is = ChargebeeClient.class.getClassLoader().getResourceAsStream("version.properties")) {
            if (is != null) {
                java.util.Properties props = new java.util.Properties();
                props.load(is);
                return props.getProperty("version", "unknown");
            }
        } catch (java.io.IOException e) {
        }
        return "unknown";
    }

    private boolean shouldRetry(int statusCode) {
        return retry.getRetryOnStatus().contains(statusCode);
    }
    
    private Request addRetryAttemptHeader(Request request, int attempts) {
        if (attempts <= 0) {
            return request;
        }
        return request.withHeader(Request.HEADER_RETRY_ATTEMPT, String.valueOf(attempts));
    }

    private void sleep(long delayMs) {
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted during retry delay", e);
        }
    }
    
    private long calculateBackoffDelay(int attempt) {
        // Exponential backoff with jitter
        long baseDelay = retry.getBaseDelayMs();
        long exponentialDelay = baseDelay * (1L << (attempt - 1));
        
        // Add jitter (+/-25% of the delay)
        long jitter = (long) (exponentialDelay * 0.25 * (ThreadLocalRandom.current().nextDouble() * 2 - 1));
        
        return Math.max(0, exponentialDelay + jitter);
    }
    
    /**
     * Send a request asynchronously with retry logic based on the configured RetryConfig.
     */
    public CompletableFuture<Response> sendWithRetryAsync(Request request) {
        Request enrichedRequest = addDefaultHeaders(request);

        Integer overrideRetries = enrichedRequest.getMaxNetworkRetriesOverride();
        boolean retriesEnabled = retry.isEnabled() || (overrideRetries != null && overrideRetries >= 0);
        if (!retriesEnabled) {
            return transport.sendAsync(enrichedRequest);
        }

        return sendWithRetryAsyncInternal(enrichedRequest, 0, (overrideRetries != null ? overrideRetries : retry.getMaxRetries()));
    }
    
    private CompletableFuture<Response> sendWithRetryAsyncInternal(Request request, int attempts, int maxRetries) {
        Request requestToSend = addRetryAttemptHeader(request, attempts);
        return transport.sendAsync(requestToSend)
            .handle((response, throwable) -> {
                if (throwable != null) {
                    // Handle retryable exceptions
                    if ((throwable instanceof TimeoutException || throwable instanceof NetworkException) 
                        && attempts < maxRetries) {
                        long delay = calculateBackoffDelay(attempts + 1);
                        return delayAndRetry(request, attempts + 1, delay, maxRetries);
                    }
                    
                    // Non-retryable exception or max retries reached
                    CompletableFuture<Response> failed = new CompletableFuture<>();
                    failed.completeExceptionally(throwable);
                    return failed;
                }
                
                // Check if we should retry based on status code
                if (attempts < maxRetries && shouldRetry(response.getStatusCode())) {
                    long delay = calculateBackoffDelay(attempts + 1);
                    return delayAndRetry(request, attempts + 1, delay, maxRetries);
                }
                
                return CompletableFuture.completedFuture(response);
            })
            .thenCompose(future -> future);
    }
    
    private CompletableFuture<Response> delayAndRetry(Request request, int nextAttempt, long delayMs, int maxRetries) {
        CompletableFuture<Response> delayedRetry = new CompletableFuture<>();
        
        // Use a separate thread for the delay to avoid blocking
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(delayMs);
                sendWithRetryAsyncInternal(request, nextAttempt, maxRetries)
                    .whenComplete((response, throwable) -> {
                        if (throwable != null) {
                            delayedRetry.completeExceptionally(throwable);
                        } else {
                            delayedRetry.complete(response);
                        }
                    });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                delayedRetry.completeExceptionally(new RuntimeException("Interrupted during retry delay", e));
            }
        });
        
        return delayedRetry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargebeeClient that = (ChargebeeClient) o;
        return connectTimeout == that.connectTimeout &&
               readTimeout == that.readTimeout &&
               Objects.equals(apiKey, that.apiKey) &&
               Objects.equals(siteName, that.siteName) &&
               Objects.equals(endpoint, that.endpoint) &&
               Objects.equals(transport, that.transport) &&
               Objects.equals(retry, that.retry) &&
               Objects.equals(domainSuffix, that.domainSuffix) &&
               Objects.equals(protocol, that.protocol) &&
               Objects.equals(requestInterceptor, that.requestInterceptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, siteName, endpoint, transport, retry, 
                          connectTimeout, readTimeout, domainSuffix, protocol, requestInterceptor);
    }

    public static final class Builder {
        private String apiKey;
        private String siteName;
        private String endpoint;
        private Transport transport;
        private RetryConfig retry = RetryConfig.defaultConfig(); // Default no retries
        private int connectTimeout = 30000;
        private int readTimeout = 80000;
        private boolean debugLogging = false;
        private RequestLogger requestLogger;
        private String domainSuffix = "chargebee.com";
        private String protocol = "https";
        private RequestInterceptor requestInterceptor;
        private final RequestContext clientHeaders = new RequestContext();

        private Builder() {}

        public Builder apiKey(String apiKey) { this.apiKey = apiKey; return this; }
        public Builder siteName(String siteName) { this.siteName = siteName; return this; }
        public Builder endpoint(String endpoint) { this.endpoint = endpoint; return this; }
        public Builder transport(Transport transport) { this.transport = transport; return this; }
        public Builder retry(RetryConfig retry) { this.retry = retry; return this; }
        public Builder retryConfig(RetryConfig retryConfig) { this.retry = retryConfig; return this; }
        public Builder connectTimeout(int connectTimeout) { this.connectTimeout = connectTimeout; return this; }
        public Builder readTimeout(int readTimeout) { this.readTimeout = readTimeout; return this; }
        public Builder timeout(int connectTimeoutMs, int readTimeoutMs) {
            this.connectTimeout = connectTimeoutMs;
            this.readTimeout = readTimeoutMs;
            return this;
        }
        public Builder debugLogging(boolean debugLogging) { this.debugLogging = debugLogging; return this; }
        public Builder requestLogger(RequestLogger requestLogger) { this.requestLogger = requestLogger; return this; }
        public Builder domainSuffix(String domainSuffix) { this.domainSuffix = domainSuffix; return this; }
        public Builder protocol(String protocol) { this.protocol = protocol; return this; }
        public Builder requestInterceptor(RequestInterceptor requestInterceptor) { this.requestInterceptor = requestInterceptor; return this; }

        // Header helpers
        public Builder header(String name, String value) {
            clientHeaders.header(name, value);
            return this;
        }
        
        public Builder headers(Map<String, String> headers) {
            clientHeaders.headers(headers);
            return this;
        }

        public ChargebeeClient build() {
            validate();
            
            // Set up transport if not provided
            if (transport == null) {
                transport = createDefaultTransport();
            }
            
            return new ChargebeeClient(this);
        }
        
        private void validate() {
            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new ConfigurationException("API key is required");
            }
            if (siteName == null || siteName.trim().isEmpty()) {
                throw new ConfigurationException("Site name is required");
            }
            
            // Validate endpoint if provided
            if (endpoint != null && !endpoint.trim().isEmpty()) {
                if (!UrlBuilder.isValidBaseUrl(endpoint)) {
                    throw new ConfigurationException("Invalid endpoint URL: " + endpoint);
                }
            }
            
            // Validate timeouts
            if (connectTimeout < 0) {
                throw new ConfigurationException("Connect timeout must be non-negative");
            }
            if (readTimeout < 0) {
                throw new ConfigurationException("Read timeout must be non-negative");
            }
            
            // Validate protocol
            if (protocol != null && !protocol.equals("http") && !protocol.equals("https")) {
                throw new ConfigurationException("Protocol must be 'http' or 'https'");
            }
        }
        
        private Transport createDefaultTransport() {
            TransportConfig.Builder configBuilder = TransportConfig.builder()
                    .apiKey(apiKey)
                    .connectTimeout(connectTimeout)
                    .readTimeout(readTimeout);
            
            // Add request logger if provided, or create default if debugLogging is enabled
            if (requestLogger != null) {
                configBuilder.requestLogger(requestLogger);
            } else if (debugLogging) {
                configBuilder.requestLogger(new ConsoleRequestLogger(ConsoleRequestLogger.LogLevel.CURL));
            }
            
            return new DefaultTransport(configBuilder.build());
        }
    }
}

