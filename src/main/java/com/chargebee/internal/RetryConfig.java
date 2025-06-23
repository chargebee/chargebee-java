package com.chargebee.internal;

import java.util.*;

public class RetryConfig {
    private final boolean enabled;
    private final int maxRetries;
    private final int baseDelayMs;
    private final Set<Integer> retryOnStatus;

    public RetryConfig(boolean enabled, int maxRetries, int baseDelayMs, Set<Integer> retryOnStatus) {
        this.enabled = enabled;
        this.maxRetries = maxRetries;
        this.baseDelayMs = baseDelayMs;
        this.retryOnStatus = retryOnStatus;
    }

    public boolean isEnabled() { return enabled; }
    public int getMaxRetries() { return maxRetries; }
    public int getBaseDelayMs() { return baseDelayMs; }
    public Set<Integer> getRetryOnStatus() { return retryOnStatus; }

    public boolean shouldRetry(int statusCode, int retryCount) {
        return retryOnStatus.contains(statusCode) && retryCount < maxRetries;
    }

    public static RetryConfig defaultConfig() {
        return new RetryConfig(false, 3, 500, new HashSet<>(Arrays.asList(500)));
    }
}
