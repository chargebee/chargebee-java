package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;

/**
 * Exception for 4xx HTTP status codes (client errors).
 * 
 * <p>Client errors indicate issues with the request itself:
 * <ul>
 *   <li>400 Bad Request - Invalid request format or parameters</li>
 *   <li>401 Unauthorized - Missing or invalid authentication</li>
 *   <li>403 Forbidden - Valid auth but insufficient permissions</li>
 *   <li>404 Not Found - Resource doesn't exist</li>
 *   <li>409 Conflict - Resource state conflict</li>
 *   <li>422 Unprocessable Entity - Validation errors</li>
 *   <li>429 Too Many Requests - Rate limiting (retryable)</li>
 * </ul>
 * 
 * <p>Convenience methods for checking specific error types:
 * <pre>{@code
 * try {
 *     client.customers().retrieve("invalid_id");
 * } catch (ClientErrorException e) {
 *     if (e.isNotFound()) {
 *         // Customer doesn't exist
 *     } else if (e.isUnauthorized()) {
 *         // Check API key
 *     } else if (e.isTooManyRequests()) {
 *         // Back off and retry
 *         Thread.sleep(e.getRetryAfterMs());
 *     }
 * }
 * }</pre>
 */
public class ClientErrorException extends HttpException {
    
    /** HTTP status code for rate limiting. */
    public static final int TOO_MANY_REQUESTS = 429;
    
    /**
     * Creates a ClientErrorException.
     *
     * @param statusCode the HTTP status code (4xx)
     * @param message descriptive error message
     * @param request the original HTTP request
     * @param response the HTTP response
     */
    public ClientErrorException(int statusCode, String message, Request request, Response response) {
        super(statusCode, message, request, response);
    }
    
    /**
     * Creates a ClientErrorException with cause.
     *
     * @param statusCode the HTTP status code (4xx)
     * @param message descriptive error message
     * @param request the original HTTP request
     * @param response the HTTP response
     * @param cause the underlying cause
     */
    public ClientErrorException(int statusCode, String message, Request request, Response response, Throwable cause) {
        super(statusCode, message, request, response, cause);
    }
    
    /**
     * Client errors are generally not retryable, except for 429 Too Many Requests.
     *
     * @return true only if status code is 429 (rate limiting)
     */
    @Override
    public boolean isRetryable() {
        return getStatusCode() == TOO_MANY_REQUESTS;
    }
    
    /**
     * Check if this is a 400 Bad Request error.
     *
     * @return true if status code is 400
     */
    public boolean isBadRequest() {
        return getStatusCode() == 400;
    }
    
    /**
     * Check if this is a 401 Unauthorized error.
     * Usually indicates missing or invalid API key.
     *
     * @return true if status code is 401
     */
    public boolean isUnauthorized() {
        return getStatusCode() == 401;
    }
    
    /**
     * Check if this is a 403 Forbidden error.
     * Usually indicates valid auth but insufficient permissions.
     *
     * @return true if status code is 403
     */
    public boolean isForbidden() {
        return getStatusCode() == 403;
    }
    
    /**
     * Check if this is a 404 Not Found error.
     *
     * @return true if status code is 404
     */
    public boolean isNotFound() {
        return getStatusCode() == 404;
    }
    
    /**
     * Check if this is a 405 Method Not Allowed error.
     *
     * @return true if status code is 405
     */
    public boolean isMethodNotAllowed() {
        return getStatusCode() == 405;
    }
    
    /**
     * Check if this is a 409 Conflict error.
     *
     * @return true if status code is 409
     */
    public boolean isConflict() {
        return getStatusCode() == 409;
    }
    
    /**
     * Check if this is a 422 Unprocessable Entity error.
     * Usually indicates validation errors.
     *
     * @return true if status code is 422
     */
    public boolean isUnprocessableEntity() {
        return getStatusCode() == 422;
    }
    
    /**
     * Check if this is a 429 Too Many Requests error.
     * This error is retryable after waiting for the rate limit to reset.
     *
     * @return true if status code is 429
     */
    public boolean isTooManyRequests() {
        return getStatusCode() == TOO_MANY_REQUESTS;
    }
    
    /**
     * Get the retry-after delay in milliseconds from response headers.
     * This is typically provided with 429 responses.
     *
     * @return the retry delay in milliseconds, or -1 if not available
     */
    public long getRetryAfterMs() {
        Response response = getResponse();
        if (response == null) {
            return -1;
        }
        
        String retryAfter = response.getHeader("Retry-After");
        if (retryAfter == null) {
            return -1;
        }
        
        try {
            // Retry-After can be seconds
            int seconds = Integer.parseInt(retryAfter.trim());
            return seconds * 1000L;
        } catch (NumberFormatException e) {
            // Could be HTTP-date format, return -1 for simplicity
            return -1;
        }
    }
}
