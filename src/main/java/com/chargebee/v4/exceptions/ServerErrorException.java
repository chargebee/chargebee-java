package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;

/**
 * Exception for 5xx HTTP status codes (server errors).
 * 
 * <p>Server errors indicate issues on the server side that may be temporary:
 * <ul>
 *   <li>500 Internal Server Error - Unexpected server error</li>
 *   <li>501 Not Implemented - Feature not supported (not retryable)</li>
 *   <li>502 Bad Gateway - Gateway/proxy error</li>
 *   <li>503 Service Unavailable - Server overloaded or maintenance</li>
 *   <li>504 Gateway Timeout - Gateway/proxy timeout</li>
 * </ul>
 * 
 * <p>Most 5xx errors are retryable (except 501):
 * <pre>{@code
 * try {
 *     client.customers().list();
 * } catch (ServerErrorException e) {
 *     if (e.isRetryable()) {
 *         // Exponential backoff retry
 *     } else if (e.isNotImplemented()) {
 *         // Feature not available
 *     }
 * }
 * }</pre>
 */
public class ServerErrorException extends HttpException {
    
    /**
     * Creates a ServerErrorException.
     *
     * @param statusCode the HTTP status code (5xx)
     * @param message descriptive error message
     * @param request the original HTTP request
     * @param response the HTTP response
     */
    public ServerErrorException(int statusCode, String message, Request request, Response response) {
        super(statusCode, message, request, response);
    }
    
    /**
     * Creates a ServerErrorException with cause.
     *
     * @param statusCode the HTTP status code (5xx)
     * @param message descriptive error message
     * @param request the original HTTP request
     * @param response the HTTP response
     * @param cause the underlying cause
     */
    public ServerErrorException(int statusCode, String message, Request request, Response response, Throwable cause) {
        super(statusCode, message, request, response, cause);
    }
    
    /**
     * Check if this is a 500 Internal Server Error.
     *
     * @return true if status code is 500
     */
    public boolean isInternalServerError() {
        return getStatusCode() == 500;
    }
    
    /**
     * Check if this is a 501 Not Implemented error.
     * This error is NOT retryable as the feature is not supported.
     *
     * @return true if status code is 501
     */
    public boolean isNotImplemented() {
        return getStatusCode() == 501;
    }
    
    /**
     * Check if this is a 502 Bad Gateway error.
     *
     * @return true if status code is 502
     */
    public boolean isBadGateway() {
        return getStatusCode() == 502;
    }
    
    /**
     * Check if this is a 503 Service Unavailable error.
     *
     * @return true if status code is 503
     */
    public boolean isServiceUnavailable() {
        return getStatusCode() == 503;
    }
    
    /**
     * Check if this is a 504 Gateway Timeout error.
     *
     * @return true if status code is 504
     */
    public boolean isGatewayTimeout() {
        return getStatusCode() == 504;
    }
    
    /**
     * Server errors are generally retryable, except 501 (Not Implemented).
     * 
     * <p>Retryable server errors (temporary issues):
     * <ul>
     *   <li>500 Internal Server Error</li>
     *   <li>502 Bad Gateway</li>
     *   <li>503 Service Unavailable</li>
     *   <li>504 Gateway Timeout</li>
     * </ul>
     *
     * @return true for all 5xx except 501
     */
    @Override
    public boolean isRetryable() {
        int code = getStatusCode();
        return code >= 500 && code != 501;
    }
}
