package com.chargebee.v4.transport;

/**
 * Exception for 5xx HTTP status codes (server errors).
 * Indicates issues on the server side that may be temporary.
 */
public class ServerErrorException extends HttpException {
    
    public ServerErrorException(int statusCode, String message, Request request, Response response) {
        super(statusCode, message, request, response);
    }
    
    public ServerErrorException(int statusCode, String message, Request request, Response response, Throwable cause) {
        super(statusCode, message, request, response, cause);
    }
    
    /**
     * Check if this is a specific server error type.
     */
    public boolean isInternalServerError() {
        return getStatusCode() == 500;
    }
    
    public boolean isBadGateway() {
        return getStatusCode() == 502;
    }
    
    public boolean isServiceUnavailable() {
        return getStatusCode() == 503;
    }
    
    public boolean isGatewayTimeout() {
        return getStatusCode() == 504;
    }
    
    /**
     * Check if this error might be retryable.
     * Generally, 5xx errors except 501 (Not Implemented) are considered retryable.
     */
    public boolean isRetryable() {
        int code = getStatusCode();
        return code >= 500 && code != 501; // 501 Not Implemented is not retryable
    }
}