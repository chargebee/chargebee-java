package com.chargebee.transport;

/**
 * Connection or read timeouts.
 */
public class TimeoutException extends TransportException {
    private final String timeoutType; // "connect" or "read"
    
    public TimeoutException(String timeoutType, String message, Throwable cause) {
        super(message, cause);
        this.timeoutType = timeoutType;
    }
    
    public String getTimeoutType() {
        return timeoutType;
    }
    
    public boolean isRetryable() {
        return true; // Timeouts are generally retryable
    }
}