package com.chargebee.v4.transport;

/**
 * Base exception for HTTP status code errors (4xx, 5xx).
 * Contains the HTTP response information for error handling.
 */
public class HttpException extends TransportException {
    private final int statusCode;
    private final Response response;
    
    public HttpException(int statusCode, String message, Response response) {
        super(message);
        this.statusCode = statusCode;
        this.response = response;
    }
    
    public HttpException(int statusCode, String message, Response response, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.response = response;
    }
    
    /**
     * Get the HTTP status code.
     */
    public int getStatusCode() {
        return statusCode;
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
        return String.format("%s{statusCode=%d, message='%s'}", 
            getClass().getSimpleName(), statusCode, getMessage());
    }
}