package com.chargebee.v4.transport;

import com.chargebee.v4.exceptions.*;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.zip.GZIPInputStream;

/**
 * Default HTTP transport implementation using HttpURLConnection for both sync and async operations.
 * Async operations are executed on a background thread pool.
 */
public class DefaultTransport implements Transport {
    private final TransportConfig config;
    private final Executor asyncExecutor;

    private static final String VERSION;

    public TransportConfig getConfig() {
        return config;
    }
    
    static {
        String version = "unknown";
        try (InputStream is = DefaultTransport.class.getClassLoader().getResourceAsStream("version.properties")) {
            if (is != null) {
                Properties props = new Properties();
                props.load(is);
                version = props.getProperty("version", "unknown");
            }
        } catch (IOException e) {
            // Ignore and use default
        }
        VERSION = version;
    }
    
    public DefaultTransport(TransportConfig config) {
        this.config = Objects.requireNonNull(config, "TransportConfig cannot be null");
        this.asyncExecutor = ForkJoinPool.commonPool();
    }
    
    @Override
    public Response send(Request request) throws TransportException {
        RequestLogger logger = config.getRequestLogger();
        long startTime = System.currentTimeMillis();
        
        // Log request with complete headers if logging is enabled
        if (logger != null && logger.isEnabled()) {
            Request completeRequest = buildCompleteRequestForLogging(request);
            logger.logRequest(completeRequest);
        }
        
        try {
            HttpURLConnection connection = createConnection(request);
            writeRequestBody(connection, request);
            Response response = readResponse(connection);

            // Validate response and throw appropriate exceptions
            HttpStatusHandler.validateResponse(request, response);

            // Log successful response
            if (logger != null && logger.isEnabled()) {
                long duration = System.currentTimeMillis() - startTime;
                logger.logResponse(request, response, duration);
            }

            return response;
        } catch (HttpException e) {
            // Log HTTP error
            if (logger != null && logger.isEnabled()) {
                long duration = System.currentTimeMillis() - startTime;
                logger.logError(request, e, duration);
            }
            throw e; // Re-throw HTTP exceptions as-is
        } catch (TransportException e) {
            // Log transport error
            if (logger != null && logger.isEnabled()) {
                long duration = System.currentTimeMillis() - startTime;
                logger.logError(request, e, duration);
            }
            throw e;
        } catch (SocketTimeoutException e) {
            String timeoutType = e.getMessage() != null && e.getMessage().contains("connect") ? "connect" : "read";
            TimeoutException timeoutException = new TimeoutException(timeoutType, "Request timed out", e, request);
            if (logger != null && logger.isEnabled()) {
                long duration = System.currentTimeMillis() - startTime;
                logger.logError(request, timeoutException, duration);
            }
            throw timeoutException;
        } catch (UnknownHostException | NoRouteToHostException e) {
            NetworkException networkException = new NetworkException("Network connectivity issue: " + e.getMessage(), e, request);
            if (logger != null && logger.isEnabled()) {
                long duration = System.currentTimeMillis() - startTime;
                logger.logError(request, networkException, duration);
            }
            throw networkException;
        } catch (IOException e) {
            NetworkException networkException = new NetworkException("Network error: " + e.getMessage(), e, request);
            if (logger != null && logger.isEnabled()) {
                long duration = System.currentTimeMillis() - startTime;
                logger.logError(request, networkException, duration);
            }
            throw networkException;
        }
    }
    
    @Override
    public CompletableFuture<Response> sendAsync(Request request) {
        CompletableFuture<Response> future = new CompletableFuture<>();
        
        // Execute the synchronous send method in a background thread
        asyncExecutor.execute(() -> {
            try {
                Response response = send(request);
                future.complete(response);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });
        
        return future;
    }
    
    private HttpURLConnection createConnection(Request request) throws IOException, TransportException {
        String fullUrl = buildFullUrl(request);
        
        URL url;
        try {
            url = new URL(fullUrl);
        } catch (MalformedURLException e) {
            throw new ConfigurationException("Invalid URL: " + fullUrl);
        }
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        // Set timeouts
        connection.setConnectTimeout(config.getConnectTimeoutMs());
        connection.setReadTimeout(config.getReadTimeoutMs());
        
        // Set method
        connection.setRequestMethod(request.getMethod());
        
        // Set redirect behavior
        Boolean followOverride = request.getFollowRedirectsOverride();
        boolean follow = (followOverride != null ? followOverride : config.isFollowRedirects());
        connection.setInstanceFollowRedirects(follow);
        
        // Disable caching
        connection.setUseCaches(false);

        addRequestHeaders(connection, request);

        if (request.getBody() != null && !hasContentTypeHeader(request.getHeaders())) {
            connection.setRequestProperty("Content-Type", request.getBody().getContentType());
        }
        
        // Enable output for POST/PUT methods with body
        if (request.getBody() != null) {
            connection.setDoOutput(true);
        }
        
        return connection;
    }
    
    private Request buildCompleteRequestForLogging(Request request) {
        Request.Builder builder = Request.builder()
            .method(request.getMethod())
            .url(buildFullUrl(request));

        if (request.getQueryParams() != null) {
            builder.queryParams(request.getQueryParams());
        }

        addDefaultHeadersToBuilder(builder);

        for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }

        if (request.getBody() != null && !hasContentTypeHeader(request.getHeaders())) {
            builder.header("Content-Type", request.getBody().getContentType());
        }

        if (request.getBody() != null) {
            try {
                byte[] bodyBytes = request.getBody().getBytes();
                builder.rawBody(bodyBytes, request.getBody().getContentType());
            } catch (Exception e) {
                builder.jsonBody("[Error copying body for logging: " + e.getMessage() + "]");
            }
        }

        return builder.build();
    }

    private void addDefaultHeadersToBuilder(Request.Builder builder) {
        if (config.getApiKey() != null) {
            String authValue = "Basic " + Base64.getEncoder()
                .encodeToString((config.getApiKey() + ":").getBytes(StandardCharsets.UTF_8))
                .replaceAll("\r", "").replaceAll("\n", "");
            builder.header("Authorization", authValue);
        }

        builder.header("Accept-Charset", "UTF-8");
        builder.header("User-Agent", "Chargebee-Java-Client v" + VERSION);
        builder.header("Accept", "application/json");
        builder.header("OS-Version", String.format("%s %s %s",
            System.getProperty("os.name"),
            System.getProperty("os.arch"),
            System.getProperty("os.version")));
        builder.header("Lang-Version", System.getProperty("java.version"));

        if (config.isEnableGzipCompression()) {
            builder.header("Accept-Encoding", "gzip");
        }

        for (Map.Entry<String, String> header : config.getDefaultHeaders().entrySet()) {
            builder.header(header.getKey(), header.getValue());
        }
    }
    
    private String buildFullUrl(Request request) {
        String url = request.getUrl();
        Map<String, List<String>> queryParams = request.getQueryParams();
        
        if (queryParams.isEmpty()) {
            return url;
        }
        
        StringBuilder urlBuilder = new StringBuilder(url);
        boolean hasQuery = url.contains("?");
        
        for (Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
            for (String value : entry.getValue()) {
                urlBuilder.append(hasQuery ? "&" : "?");
                hasQuery = true;
                
                try {
                    urlBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                             .append("=")
                             .append(URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e); // UTF-8 should always be supported
                }
            }
        }
        
        return urlBuilder.toString();
    }

    private void addRequestHeaders(HttpURLConnection connection, Request request) {
        for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
            connection.setRequestProperty(header.getKey(), header.getValue());
        }
    }
    
    private boolean hasContentTypeHeader(Map<String, String> headers) {
        // Case-insensitive check for Content-Type header
        for (String key : headers.keySet()) {
            if ("Content-Type".equalsIgnoreCase(key)) {
                return true;
            }
        }
        return false;
    }
    
    private void writeRequestBody(HttpURLConnection connection, Request request) throws IOException {
        RequestBody body = request.getBody();
        if (body == null) {
            return;
        }
        
        byte[] bodyBytes = body.getBytes();
        if (bodyBytes.length > 0) {
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(bodyBytes);
            }
        }
    }
    
    private Response readResponse(HttpURLConnection connection) throws IOException, TransportException {
        int statusCode = connection.getResponseCode();
        Map<String, List<String>> headers = connection.getHeaderFields();

        // Read response body
        byte[] body = readResponseBody(connection, statusCode >= 400);

        Response response = new Response(statusCode, headers, body);

        return response;
    }
    
    private byte[] readResponseBody(HttpURLConnection connection, boolean isError) throws IOException {
        InputStream inputStream = isError ? connection.getErrorStream() : connection.getInputStream();
        
        if (inputStream == null) {
            return new byte[0];
        }
        
        try {
            // Handle GZIP compression
            if ("gzip".equalsIgnoreCase(connection.getContentEncoding())) {
                inputStream = new GZIPInputStream(inputStream);
            }
            
            return readInputStream(inputStream);
        } finally {
            inputStream.close();
        }
    }
    
    private byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[8192];
        int bytesRead;
        
        while ((bytesRead = inputStream.read(data)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        
        return buffer.toByteArray();
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private String apiKey;
        private int connectTimeoutMs = 30000;
        private int readTimeoutMs = 80000;
        private Map<String, String> defaultHeaders = new HashMap<>();
        private boolean followRedirects = true;
        private boolean enableGzipCompression = true;
        private int maxConnections = 20;
        private long keepAliveDurationMs = 300000;
        
        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }
        
        public Builder connectTimeout(int timeoutMs) {
            this.connectTimeoutMs = timeoutMs;
            return this;
        }
        
        public Builder readTimeout(int timeoutMs) {
            this.readTimeoutMs = timeoutMs;
            return this;
        }
        
        public Builder defaultHeader(String key, String value) {
            this.defaultHeaders.put(key, value);
            return this;
        }
        
        public Builder defaultHeaders(Map<String, String> headers) {
            this.defaultHeaders.putAll(headers);
            return this;
        }
        
        public Builder followRedirects(boolean follow) {
            this.followRedirects = follow;
            return this;
        }
        
        public Builder gzipCompression(boolean enable) {
            this.enableGzipCompression = enable;
            return this;
        }
        
        public Builder maxConnections(int max) {
            this.maxConnections = max;
            return this;
        }
        
        public Builder keepAliveDuration(long durationMs) {
            this.keepAliveDurationMs = durationMs;
            return this;
        }
        
        public DefaultTransport build() {
            TransportConfig config = TransportConfig.builder()
                .apiKey(apiKey)
                .connectTimeout(connectTimeoutMs)
                .readTimeout(readTimeoutMs)
                .defaultHeaders(defaultHeaders)
                .followRedirects(followRedirects)
                .gzipCompression(enableGzipCompression)
                .maxConnections(maxConnections)
                .keepAliveDuration(keepAliveDurationMs)
                .build();
            
            return new DefaultTransport(config);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultTransport)) return false;
        DefaultTransport that = (DefaultTransport) o;
        return Objects.equals(config, that.config);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(config);
    }
}