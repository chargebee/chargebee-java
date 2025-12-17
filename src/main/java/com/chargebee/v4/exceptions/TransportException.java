package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;

/**
 * Base exception for all transport-layer failures in the Chargebee SDK.
 * 
 * <p>Transport exceptions represent infrastructure-level issues that occur before
 * or during HTTP communication, such as network errors, timeouts, and HTTP status
 * code errors.
 * 
 * <p>This base class provides common functionality for all transport exceptions:
 * <ul>
 *   <li>{@link #getRequest()} - Access to the HTTP request that failed</li>
 *   <li>{@link #getUrl()} - Convenience method for the request URL</li>
 *   <li>{@link #getHttpMethod()} - Convenience method for the HTTP method</li>
 *   <li>{@link #isRetryable()} - Whether this error is typically retryable</li>
 * </ul>
 * 
 * <p>Exception hierarchy:
 * <pre>
 * TransportException
 * ├── NetworkException      (DNS failures, connection refused, I/O errors)
 * ├── TimeoutException      (connect/read timeouts)
 * └── HttpException         (HTTP status code errors: 4xx, 5xx)
 *     ├── ClientErrorException (4xx)
 *     ├── ServerErrorException (5xx)
 *     └── APIException         (Chargebee API errors with structured response)
 * </pre>
 */
public class TransportException extends Exception {
    
    private final Request request;
    
    /**
     * Creates a TransportException with a message only.
     *
     * @param message descriptive error message
     */
    public TransportException(String message) {
        super(message);
        this.request = null;
    }
    
    /**
     * Creates a TransportException with a message and cause.
     *
     * @param message descriptive error message
     * @param cause the underlying cause
     */
    public TransportException(String message, Throwable cause) {
        super(message, cause);
        this.request = null;
    }
    
    /**
     * Creates a TransportException with full context.
     *
     * @param message descriptive error message
     * @param cause the underlying cause (may be null)
     * @param request the HTTP request that failed (may be null)
     */
    public TransportException(String message, Throwable cause, Request request) {
        super(message, cause);
        this.request = request;
    }
    
    /**
     * Creates a TransportException with message and request context.
     *
     * @param message descriptive error message
     * @param request the HTTP request that failed
     */
    public TransportException(String message, Request request) {
        super(message);
        this.request = request;
    }
    
    /**
     * Get the HTTP request that failed.
     * Useful for detailed debugging and logging.
     *
     * @return the request object, or null if not available
     */
    public Request getRequest() {
        return request;
    }
    
    /**
     * Get the URL that was being accessed when the error occurred.
     *
     * @return the request URL, or null if request is not available
     */
    public String getUrl() {
        return request != null ? request.getUrl() : null;
    }
    
    /**
     * Get the HTTP method used for the failed request.
     *
     * @return the HTTP method (GET, POST, etc.), or null if request is not available
     */
    public String getHttpMethod() {
        return request != null ? request.getMethod() : null;
    }
    
    /**
     * Check if this error is typically retryable.
     * 
     * <p>Subclasses override this to provide appropriate retry guidance:
     * <ul>
     *   <li>{@link NetworkException} - returns true (transient network issues)</li>
     *   <li>{@link TimeoutException} - returns true (temporary overload)</li>
     *   <li>{@link ServerErrorException} - returns true for most 5xx (except 501)</li>
     *   <li>{@link ClientErrorException} - returns true only for 429 (rate limiting)</li>
     * </ul>
     *
     * @return true if retrying the request might succeed, false otherwise
     */
    public boolean isRetryable() {
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{message='").append(getMessage()).append("'");
        
        if (request != null) {
            sb.append(", method='").append(request.getMethod()).append("'");
            sb.append(", url='").append(request.getUrl()).append("'");
        }
        
        Throwable cause = getCause();
        if (cause != null) {
            sb.append(", cause=").append(cause.getClass().getSimpleName());
            if (cause.getMessage() != null) {
                sb.append("('").append(cause.getMessage()).append("')");
            }
        }
        
        sb.append("}");
        return sb.toString();
    }
}
