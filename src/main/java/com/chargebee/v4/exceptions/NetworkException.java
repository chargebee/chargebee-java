package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;

/**
 * Exception for network connectivity issues such as DNS failures, connection refused,
 * or general I/O errors during HTTP communication.
 * 
 * <p>This exception inherits request context from {@link TransportException}:
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
 * } catch (NetworkException e) {
 *     System.err.println("Failed to connect to: " + e.getUrl());
 *     System.err.println("Method: " + e.getHttpMethod());
 *     System.err.println("Cause: " + e.getCause().getMessage());
 *     
 *     if (e.isRetryable()) {
 *         // Implement retry logic
 *     }
 * }
 * }</pre>
 */
public class NetworkException extends TransportException {
    
    /**
     * Creates a NetworkException with request context.
     *
     * @param message descriptive error message
     * @param cause the underlying cause (e.g., IOException)
     * @param request the HTTP request that failed
     */
    public NetworkException(String message, Throwable cause, Request request) {
        super(message, cause, request);
    }
    
    /**
     * Creates a NetworkException without request context.
     * Prefer {@link #NetworkException(String, Throwable, Request)} when request is available.
     *
     * @param message descriptive error message
     * @param cause the underlying cause
     */
    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Network errors are generally retryable as they may be transient
     * (e.g., temporary DNS issues, network hiccups).
     *
     * @return true, indicating this error is typically retryable
     */
    @Override
    public boolean isRetryable() {
        return true;
    }
}
