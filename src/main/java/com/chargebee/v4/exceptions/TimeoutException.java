package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;

/**
 * Exception for connection or read timeouts during HTTP communication.
 * 
 * <p>This exception inherits request context from {@link TransportException} and adds:
 * <ul>
 *   <li>{@link #getTimeoutType()} - Whether it was a "connect" or "read" timeout</li>
 *   <li>{@link #isConnectTimeout()} / {@link #isReadTimeout()} - Convenient type checks</li>
 * </ul>
 * 
 * <p>Inherited from base class:
 * <ul>
 *   <li>{@link #getUrl()} - The URL that was being accessed</li>
 *   <li>{@link #getHttpMethod()} - The HTTP method (GET, POST, etc.)</li>
 *   <li>{@link #getRequest()} - The full request object if needed</li>
 * </ul>
 * 
 * <p>Example usage:
 * <pre>{@code
 * try {
 *     client.customers().list();
 * } catch (TimeoutException e) {
 *     System.err.println("Timeout type: " + e.getTimeoutType());
 *     System.err.println("Failed URL: " + e.getUrl());
 *     
 *     if (e.isConnectTimeout()) {
 *         // Server might be down or unreachable
 *     } else if (e.isReadTimeout()) {
 *         // Server is slow to respond, consider increasing timeout
 *     }
 *     
 *     if (e.isRetryable()) {
 *         // Implement retry logic
 *     }
 * }
 * }</pre>
 */
public class TimeoutException extends TransportException {
    
    /** Timeout type for connection timeouts. */
    public static final String CONNECT = "connect";
    
    /** Timeout type for read timeouts. */
    public static final String READ = "read";
    
    private final String timeoutType;
    
    /**
     * Creates a TimeoutException with request context.
     *
     * @param timeoutType the type of timeout ("connect" or "read")
     * @param message descriptive error message
     * @param cause the underlying cause
     * @param request the HTTP request that timed out
     */
    public TimeoutException(String timeoutType, String message, Throwable cause, Request request) {
        super(message, cause, request);
        this.timeoutType = timeoutType;
    }
    
    /**
     * Creates a TimeoutException without request context.
     * Prefer {@link #TimeoutException(String, String, Throwable, Request)} when request is available.
     *
     * @param timeoutType the type of timeout ("connect" or "read")
     * @param message descriptive error message
     * @param cause the underlying cause
     */
    public TimeoutException(String timeoutType, String message, Throwable cause) {
        super(message, cause);
        this.timeoutType = timeoutType;
    }
    
    /**
     * Get the type of timeout that occurred.
     *
     * @return "connect" for connection timeouts, "read" for read timeouts
     */
    public String getTimeoutType() {
        return timeoutType;
    }
    
    /**
     * Check if this was a connection timeout.
     * Connection timeouts occur when the server cannot be reached within the connect timeout period.
     *
     * @return true if this is a connection timeout
     */
    public boolean isConnectTimeout() {
        return CONNECT.equals(timeoutType);
    }
    
    /**
     * Check if this was a read timeout.
     * Read timeouts occur when the server doesn't respond within the read timeout period.
     *
     * @return true if this is a read timeout
     */
    public boolean isReadTimeout() {
        return READ.equals(timeoutType);
    }
    
    /**
     * Timeouts are generally retryable as they may be transient
     * (e.g., temporary server load, network congestion).
     *
     * @return true, indicating this error is typically retryable
     */
    @Override
    public boolean isRetryable() {
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{timeoutType='").append(timeoutType).append("'");
        sb.append(", message='").append(getMessage()).append("'");
        
        if (getRequest() != null) {
            sb.append(", method='").append(getHttpMethod()).append("'");
            sb.append(", url='").append(getUrl()).append("'");
        }
        
        sb.append("}");
        return sb.toString();
    }
}
