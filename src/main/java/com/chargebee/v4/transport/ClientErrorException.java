package com.chargebee.v4.transport;

/**
 * Exception for 4xx HTTP status codes (client errors).
 * Indicates issues with the request (bad request, unauthorized, not found, etc.).
 */
public class ClientErrorException extends HttpException {
    
    public ClientErrorException(int statusCode, String message, Request request, Response response) {
        super(statusCode, message, request, response);
    }
    
    public ClientErrorException(int statusCode, String message, Request request, Response response, Throwable cause) {
        super(statusCode, message, request, response, cause);
    }
    
    /**
     * Check if this is a specific client error type.
     */
    public boolean isBadRequest() {
        return getStatusCode() == 400;
    }
    
    public boolean isUnauthorized() {
        return getStatusCode() == 401;
    }
    
    public boolean isForbidden() {
        return getStatusCode() == 403;
    }
    
    public boolean isNotFound() {
        return getStatusCode() == 404;
    }
    
    public boolean isMethodNotAllowed() {
        return getStatusCode() == 405;
    }
    
    public boolean isConflict() {
        return getStatusCode() == 409;
    }
    
    public boolean isUnprocessableEntity() {
        return getStatusCode() == 422;
    }
    
    public boolean isTooManyRequests() {
        return getStatusCode() == 429;
    }
}