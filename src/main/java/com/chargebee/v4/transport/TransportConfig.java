package com.chargebee.v4.transport;

import java.util.*;

/**
 * Immutable configuration for HTTP transport.
 */
public final class TransportConfig {
    private final String apiKey;
    private final int connectTimeoutMs;
    private final int readTimeoutMs;
    private final Map<String, String> defaultHeaders;
    private final boolean followRedirects;
    private final boolean enableGzipCompression;
    private final int maxConnections;
    private final long keepAliveDurationMs;
    private final RequestLogger requestLogger;
    
    private TransportConfig(Builder builder) {
        this.apiKey = builder.apiKey;
        this.connectTimeoutMs = builder.connectTimeoutMs;
        this.readTimeoutMs = builder.readTimeoutMs;
        this.defaultHeaders = Collections.unmodifiableMap(new HashMap<>(builder.defaultHeaders));
        this.followRedirects = builder.followRedirects;
        this.enableGzipCompression = builder.enableGzipCompression;
        this.maxConnections = builder.maxConnections;
        this.keepAliveDurationMs = builder.keepAliveDurationMs;
        this.requestLogger = builder.requestLogger;
    }
    
    public String getApiKey() { 
        return apiKey; 
    }
    
    public int getConnectTimeoutMs() { 
        return connectTimeoutMs; 
    }
    
    public int getReadTimeoutMs() { 
        return readTimeoutMs; 
    }
    
    public Map<String, String> getDefaultHeaders() { 
        return defaultHeaders; 
    }
    
    public boolean isFollowRedirects() { 
        return followRedirects; 
    }
    
    public boolean isEnableGzipCompression() { 
        return enableGzipCompression; 
    }
    
    public int getMaxConnections() { 
        return maxConnections; 
    }
    
    public long getKeepAliveDurationMs() { 
        return keepAliveDurationMs; 
    }
    
    public RequestLogger getRequestLogger() {
        return requestLogger;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private String apiKey;
        private int connectTimeoutMs = 30000; // 30 seconds
        private int readTimeoutMs = 80000; // 80 seconds
        private Map<String, String> defaultHeaders = new HashMap<>();
        private boolean followRedirects = true;
        private boolean enableGzipCompression = true;
        private int maxConnections = 20;
        private long keepAliveDurationMs = 300000; // 5 minutes
        private RequestLogger requestLogger;
        
        public Builder() {
            // Standard headers are set by DefaultTransport
            // This is for additional custom headers only
        }
        
        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }
        
        public Builder connectTimeout(int timeoutMs) {
            if (timeoutMs < 0) {
                throw new IllegalArgumentException("Connect timeout must be non-negative");
            }
            this.connectTimeoutMs = timeoutMs;
            return this;
        }
        
        public Builder readTimeout(int timeoutMs) {
            if (timeoutMs < 0) {
                throw new IllegalArgumentException("Read timeout must be non-negative");
            }
            this.readTimeoutMs = timeoutMs;
            return this;
        }
        
        public Builder defaultHeader(String key, String value) {
            if (key == null) {
                throw new IllegalArgumentException("Header key cannot be null");
            }
            defaultHeaders.put(key, value);
            return this;
        }
        
        public Builder defaultHeaders(Map<String, String> headers) {
            if (headers != null) {
                this.defaultHeaders.putAll(headers);
            }
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
            if (max <= 0) {
                throw new IllegalArgumentException("Max connections must be positive");
            }
            this.maxConnections = max;
            return this;
        }
        
        public Builder keepAliveDuration(long durationMs) {
            if (durationMs < 0) {
                throw new IllegalArgumentException("Keep-alive duration must be non-negative");
            }
            this.keepAliveDurationMs = durationMs;
            return this;
        }
        
        public Builder requestLogger(RequestLogger requestLogger) {
            this.requestLogger = requestLogger;
            return this;
        }
        
        public TransportConfig build() {
            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new IllegalArgumentException("API key is required");
            }
            return new TransportConfig(this);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransportConfig)) return false;
        TransportConfig that = (TransportConfig) o;
        return connectTimeoutMs == that.connectTimeoutMs &&
               readTimeoutMs == that.readTimeoutMs &&
               followRedirects == that.followRedirects &&
               enableGzipCompression == that.enableGzipCompression &&
               maxConnections == that.maxConnections &&
               keepAliveDurationMs == that.keepAliveDurationMs &&
               Objects.equals(apiKey, that.apiKey) &&
               Objects.equals(defaultHeaders, that.defaultHeaders);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(apiKey, connectTimeoutMs, readTimeoutMs, defaultHeaders,
                           followRedirects, enableGzipCompression, maxConnections, keepAliveDurationMs);
    }
    
    @Override
    public String toString() {
        return "TransportConfig{" +
                "connectTimeoutMs=" + connectTimeoutMs +
                ", readTimeoutMs=" + readTimeoutMs +
                ", defaultHeaders=" + defaultHeaders +
                ", followRedirects=" + followRedirects +
                ", enableGzipCompression=" + enableGzipCompression +
                ", maxConnections=" + maxConnections +
                ", keepAliveDurationMs=" + keepAliveDurationMs +
                '}';
    }
}