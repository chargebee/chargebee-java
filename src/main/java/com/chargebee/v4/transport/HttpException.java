package com.chargebee.v4.transport;

/**
 * Base exception for HTTP status code errors (4xx, 5xx).
 * Contains the HTTP request and response information for error handling.
 */
public class HttpException extends TransportException {
    private final int statusCode;
    private final Request request;
    private final Response response;
    
    public HttpException(int statusCode, String message, Request request, Response response) {
        super(message);
        this.statusCode = statusCode;
        this.request = request;
        this.response = response;
    }
    
    public HttpException(int statusCode, String message, Request request, Response response, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.request = request;
        this.response = response;
    }
    
    /**
     * Get the HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
    }
    
    /**
     * Get the original HTTP request that caused this error.
     * Useful for debugging and logging.
     */
    public Request getRequest() {
        return request;
    }
    
    /**
     * Get the full HTTP response.
     */
    public Response getResponse() {
        return response;
    }
    
    /**
     * Get the response body as string for error details.
     */
    public String getResponseBody() {
        return response != null ? response.getBodyAsString() : null;
    }
    
    @Override
    public String toString() {
        return String.format("%s{statusCode=%d, message='%s', url='%s'}", 
            getClass().getSimpleName(), statusCode, getMessage(),
            request != null ? request.getUrl() : "unknown");
    }
}