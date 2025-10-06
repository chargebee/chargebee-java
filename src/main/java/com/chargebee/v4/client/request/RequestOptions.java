package com.chargebee.v4.client.request;

import com.chargebee.v4.transport.RequestLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Immutable request options for per-request configuration.
 * Supports headers and can be extended for timeouts, retries, API versions, etc.
 */
public final class RequestOptions {
    private final Map<String, String> headers;
    private final Integer maxNetworkRetries;
    private final Boolean retryEnabled;
    private final Integer retryBaseDelayMs;
    private final Set<Integer> retryOnStatus;
    private final Integer connectTimeoutMs;
    private final Integer readTimeoutMs;
    private final Boolean followRedirects;
    private final Boolean gzipCompression;
    private final RequestLogger requestLogger;

    private RequestOptions(
            Map<String, String> headers,
            Integer maxNetworkRetries,
            Boolean retryEnabled,
            Integer retryBaseDelayMs,
            Set<Integer> retryOnStatus,
            Integer connectTimeoutMs,
            Integer readTimeoutMs,
            Boolean followRedirects,
            Boolean gzipCompression,
            RequestLogger requestLogger
    ) {
        // Java 8 compatibility - use HashMap constructor instead of Map.copyOf
        this.headers = new HashMap<>(headers);
        this.maxNetworkRetries = maxNetworkRetries;
        this.retryEnabled = retryEnabled;
        this.retryBaseDelayMs = retryBaseDelayMs;
        this.retryOnStatus = (retryOnStatus == null ? null : new HashSet<>(retryOnStatus));
        this.connectTimeoutMs = connectTimeoutMs;
        this.readTimeoutMs = readTimeoutMs;
        this.followRedirects = followRedirects;
        this.gzipCompression = gzipCompression;
        this.requestLogger = requestLogger;
    }

    public static RequestOptions empty() {
        return new RequestOptions(new HashMap<>(), null, null, null, null, null, null, null, null, null);
    }

    public static Builder builder() {
        return new Builder();
    }

    public RequestOptions withHeader(String key, String value) {
        Map<String, String> copy = new HashMap<>(headers);
        copy.put(key, value);
        return new RequestOptions(copy, maxNetworkRetries, retryEnabled, retryBaseDelayMs, retryOnStatus, connectTimeoutMs, readTimeoutMs, followRedirects, gzipCompression, requestLogger);
    }

    public RequestOptions withHeaders(Map<String, String> newHeaders) {
        Map<String, String> copy = new HashMap<>(headers);
        copy.putAll(newHeaders);
        return new RequestOptions(copy, maxNetworkRetries, retryEnabled, retryBaseDelayMs, retryOnStatus, connectTimeoutMs, readTimeoutMs, followRedirects, gzipCompression, requestLogger);
    }

    public Map<String, String> getHeaders() {
        return new HashMap<>(headers); // Return defensive copy
    }

    public Integer getMaxNetworkRetries() {
        return maxNetworkRetries;
    }

    public Boolean getRetryEnabled() {
        return retryEnabled;
    }

    public Integer getRetryBaseDelayMs() {
        return retryBaseDelayMs;
    }

    public Set<Integer> getRetryOnStatus() {
        return retryOnStatus == null ? null : new HashSet<>(retryOnStatus);
    }

    public Integer getConnectTimeoutMs() {
        return connectTimeoutMs;
    }

    public Integer getReadTimeoutMs() {
        return readTimeoutMs;
    }

    public Boolean getFollowRedirects() {
        return followRedirects;
    }

    public Boolean getGzipCompression() {
        return gzipCompression;
    }

    public RequestLogger getRequestLogger() {
        return requestLogger;
    }

    public static final class Builder {
        private final Map<String, String> headers = new HashMap<>();
        private Integer maxNetworkRetries;
        private Boolean retryEnabled;
        private Integer retryBaseDelayMs;
        private Set<Integer> retryOnStatus;
        private Integer connectTimeoutMs;
        private Integer readTimeoutMs;
        private Boolean followRedirects;
        private Boolean gzipCompression;
        private RequestLogger requestLogger;

        public Builder header(String name, String value) {
            if (name != null && value != null) {
                headers.put(name, value);
            }
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            if (headers != null) {
                this.headers.putAll(headers);
            }
            return this;
        }

        public Builder maxNetworkRetries(int maxNetworkRetries) {
            if (maxNetworkRetries < 0) {
                throw new IllegalArgumentException("maxNetworkRetries must be >= 0");
            }
            this.maxNetworkRetries = maxNetworkRetries;
            return this;
        }

        // Alias for compatibility with prior proposal/snippets
        public Builder setMaxNetworkRetries(int maxNetworkRetries) {
            return maxNetworkRetries(maxNetworkRetries);
        }

        public Builder retryEnabled(boolean enabled) {
            this.retryEnabled = enabled;
            return this;
        }

        public Builder retryBaseDelayMs(int delayMs) {
            if (delayMs < 0) {
                throw new IllegalArgumentException("retryBaseDelayMs must be >= 0");
            }
            this.retryBaseDelayMs = delayMs;
            return this;
        }

        public Builder retryOnStatus(Set<Integer> statuses) {
            this.retryOnStatus = (statuses == null ? null : new HashSet<>(statuses));
            return this;
        }

        public Builder connectTimeoutMs(int timeoutMs) {
            if (timeoutMs < 0) {
                throw new IllegalArgumentException("connectTimeoutMs must be >= 0");
            }
            this.connectTimeoutMs = timeoutMs;
            return this;
        }

        public Builder readTimeoutMs(int timeoutMs) {
            if (timeoutMs < 0) {
                throw new IllegalArgumentException("readTimeoutMs must be >= 0");
            }
            this.readTimeoutMs = timeoutMs;
            return this;
        }

        public Builder timeout(int connectTimeoutMs, int readTimeoutMs) {
            return connectTimeoutMs(connectTimeoutMs).readTimeoutMs(readTimeoutMs);
        }

        public Builder followRedirects(boolean follow) {
            this.followRedirects = follow;
            return this;
        }

        public Builder gzipCompression(boolean enable) {
            this.gzipCompression = enable;
            return this;
        }

        public Builder requestLogger(RequestLogger requestLogger) {
            this.requestLogger = requestLogger;
            return this;
        }

        public RequestOptions build() {
            return new RequestOptions(headers, maxNetworkRetries, retryEnabled, retryBaseDelayMs, retryOnStatus, connectTimeoutMs, readTimeoutMs, followRedirects, gzipCompression, requestLogger);
        }
    }
}