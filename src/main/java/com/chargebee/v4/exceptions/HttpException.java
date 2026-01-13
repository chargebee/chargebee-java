package com.chargebee.v4.exceptions;

import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;

/**
 * Base exception for HTTP status code errors (4xx, 5xx).
 * 
 * <p>This exception provides access to the full HTTP context:
 * <ul>
 *   <li>{@link #getStatusCode()} - The HTTP status code</li>
 *   <li>{@link #getRequest()} - The original HTTP request</li>
 *   <li>{@link #getResponse()} - The HTTP response</li>
 *   <li>{@link #getResponseBody()} - The response body as string</li>
 *   <li>{@link #getUrl()} - Convenience method for request URL</li>
 *   <li>{@link #getHttpMethod()} - Convenience method for HTTP method</li>
 * </ul>
 * 
 * <p>Exception hierarchy:
 * <pre>
 * HttpException
 * ├── ClientErrorException (4xx errors)
 * ├── ServerErrorException (5xx errors)
 * └── APIException         (Chargebee API errors with structured response)
 * </pre>
 * 
 * <p>Example usage:
 * <pre>{@code
 * try {
 *     client.customers().retrieve("invalid_id");
 * } catch (HttpException e) {
 *     System.err.println("HTTP " + e.getStatusCode() + ": " + e.getMessage());
 *     System.err.println("URL: " + e.getUrl());
 *     System.err.println("Response: " + e.getResponseBody());
 *     
 *     if (e.isRetryable()) {
 *         // Retry logic
 *     }
 * }
 * }</pre>
 */
public class HttpException extends TransportException {
    
    private final int statusCode;
    private final Response response;
    
    /**
     * Creates an HttpException with full context.
     *
     * @param statusCode the HTTP status code
     * @param message descriptive error message
     * @param request the original HTTP request
     * @param response the HTTP response
     */
    public HttpException(int statusCode, String message, Request request, Response response) {
        super(message, request);
        this.statusCode = statusCode;
        this.response = response;
    }
    
    /**
     * Creates an HttpException with full context and cause.
     *
     * @param statusCode the HTTP status code
     * @param message descriptive error message
     * @param request the original HTTP request
     * @param response the HTTP response
     * @param cause the underlying cause
     */
    public HttpException(int statusCode, String message, Request request, Response response, Throwable cause) {
        super(message, cause, request);
        this.statusCode = statusCode;
        this.response = response;
    }
    
    /**
     * Get the HTTP status code.
     *
     * @return the HTTP status code (e.g., 400, 404, 500)
     */
    public int getStatusCode() {
        return statusCode;
    }
    
    /**
     * Get the full HTTP response.
     *
     * @return the response object
     */
    public Response getResponse() {
        return response;
    }
    
    /**
     * Get the response body as string for error details.
     *
     * @return the response body, or null if response is not available
     */
    public String getResponseBody() {
        return response != null ? response.getBodyAsString() : null;
    }
    
    /**
     * Check if this HTTP error is retryable.
     * 
     * <p>By default, HTTP errors are not retryable. Subclasses override this:
     * <ul>
     *   <li>{@link ServerErrorException} - true for most 5xx (except 501)</li>
     *   <li>{@link ClientErrorException} - true only for 429 (rate limiting)</li>
     * </ul>
     *
     * @return false by default
     */
    @Override
    public boolean isRetryable() {
        return false;
    }
    
    /**
     * Check if this is a client error (4xx status code).
     *
     * @return true if status code is 4xx
     */
    public boolean isClientError() {
        return statusCode >= 400 && statusCode < 500;
    }
    
    /**
     * Check if this is a server error (5xx status code).
     *
     * @return true if status code is 5xx
     */
    public boolean isServerError() {
        return statusCode >= 500 && statusCode < 600;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{statusCode=").append(statusCode);
        sb.append(", message='").append(getMessage()).append("'");
        
        if (getRequest() != null) {
            sb.append(", method='").append(getHttpMethod()).append("'");
            sb.append(", url='").append(getUrl()).append("'");
        }
        
        sb.append("}");
        return sb.toString();
    }
}
