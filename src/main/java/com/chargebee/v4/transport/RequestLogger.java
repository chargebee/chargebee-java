package com.chargebee.v4.transport;

/**
 * Interface for logging HTTP requests and responses.
 * Implementations can provide different logging strategies (console, file, etc.).
 */
public interface RequestLogger {
    
    /**
     * Log an HTTP request before it's sent.
     * 
     * @param request the HTTP request to log
     */
    void logRequest(Request request);
    
    /**
     * Log an HTTP response after it's received.
     * 
     * @param request the original HTTP request
     * @param response the HTTP response received
     * @param durationMs the time taken for the request in milliseconds
     */
    void logResponse(Request request, Response response, long durationMs);
    
    /**
     * Log an HTTP request/response error.
     * 
     * @param request the original HTTP request
     * @param error the error that occurred
     * @param durationMs the time taken before the error occurred in milliseconds
     */
    void logError(Request request, Throwable error, long durationMs);
    
    /**
     * Check if request logging is enabled.
     * This can be used to avoid expensive operations when logging is disabled.
     */
    boolean isEnabled();
}