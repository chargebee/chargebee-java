package com.chargebee.v4.internal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RetryConfig {
    private final boolean enabled;
    private final int maxRetries;
    private final int baseDelayMs;
    private final Set<Integer> retryOnStatus;

    private RetryConfig(Builder builder) {
        this.enabled = builder.enabled;
        this.maxRetries = builder.maxRetries;
        this.baseDelayMs = builder.baseDelayMs;
        this.retryOnStatus = builder.retryOnStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static RetryConfig defaultConfig() {
        return builder().build(); // enabled=false by default
    }

    // Getters
    public boolean isEnabled() { return enabled; }
    public int getMaxRetries() { return maxRetries; }
    public int getBaseDelayMs() { return baseDelayMs; }
    public Set<Integer> getRetryOnStatus() { return new HashSet<>(retryOnStatus); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetryConfig that = (RetryConfig) o;
        return enabled == that.enabled &&
               maxRetries == that.maxRetries &&
               baseDelayMs == that.baseDelayMs &&
               retryOnStatus.equals(that.retryOnStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled, maxRetries, baseDelayMs, retryOnStatus);
    }

    public static final class Builder {
        private boolean enabled = false;
        private int maxRetries = 3;
        private int baseDelayMs = 500;
        private Set<Integer> retryOnStatus = new HashSet<>(Arrays.asList(500, 502, 503, 504));

        public Builder enabled(boolean enabled) { this.enabled = enabled; return this; }
        public Builder maxRetries(int maxRetries) { this.maxRetries = maxRetries; return this; }
        public Builder baseDelayMs(int baseDelayMs) { this.baseDelayMs = baseDelayMs; return this; }
        public Builder retryOnStatus(Set<Integer> retryOnStatus) { this.retryOnStatus = new HashSet<>(retryOnStatus); return this; }

        public RetryConfig build() {
            return new RetryConfig(this);
        }
    }
}