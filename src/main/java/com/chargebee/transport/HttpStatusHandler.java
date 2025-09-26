package com.chargebee.transport;

/**
 * Utility class for handling HTTP status codes and creating appropriate exceptions.
 */
public final class HttpStatusHandler {
    
    private HttpStatusHandler() {
        // Utility class
    }
    
    /**
     * Validate HTTP response and throw appropriate exception if not successful.
     * 
     * @param response the HTTP response to validate
     * @throws HttpException if the response indicates an error (4xx or 5xx)
     */
    public static void validateResponse(Response response) throws HttpException {
        int statusCode = response.getStatusCode();
        
        if (response.isSuccessful()) {
            return; // 2xx status codes are successful
        }
        
        String message = createErrorMessage(statusCode, response);
        
        if (statusCode >= 400 && statusCode < 500) {
            throw new ClientErrorException(statusCode, message, response);
        } else if (statusCode >= 500) {
            throw new ServerErrorException(statusCode, message, response);
        }
        
        // For other non-2xx codes (1xx, 3xx), we don't throw exceptions
        // 1xx are informational, 3xx are redirects (should be handled by HTTP client)
    }
    
    /**
     * Create a descriptive error message based on status code and response.
     */
    private static String createErrorMessage(int statusCode, Response response) {
        String statusMessage = getStatusMessage(statusCode);
        String body = response.getBodyAsString();
        
        if (body != null && !body.trim().isEmpty()) {
            // Try to extract error message from response body
            String extractedMessage = extractErrorMessage(body);
            if (extractedMessage != null && !extractedMessage.isEmpty()) {
                return String.format("HTTP %d %s: %s", statusCode, statusMessage, extractedMessage);
            }
        }
        
        return String.format("HTTP %d %s", statusCode, statusMessage);
    }
    
    /**
     * Get standard HTTP status message for a status code.
     */
    private static String getStatusMessage(int statusCode) {
        switch (statusCode) {
            // 4xx Client Errors
            case 400: return "Bad Request";
            case 401: return "Unauthorized";
            case 403: return "Forbidden";
            case 404: return "Not Found";
            case 405: return "Method Not Allowed";
            case 409: return "Conflict";
            case 422: return "Unprocessable Entity";
            case 429: return "Too Many Requests";
            
            // 5xx Server Errors
            case 500: return "Internal Server Error";
            case 502: return "Bad Gateway";
            case 503: return "Service Unavailable";
            case 504: return "Gateway Timeout";
            
            // Generic messages
            default:
                if (statusCode >= 400 && statusCode < 500) {
                    return "Client Error";
                } else if (statusCode >= 500) {
                    return "Server Error";
                } else {
                    return "HTTP Error";
                }
        }
    }
    
    /**
     * Try to extract error message from JSON response body.
     * This is a simple implementation - could be enhanced for specific API error formats.
     */
    private static String extractErrorMessage(String body) {
        if (body == null || body.trim().isEmpty()) {
            return null;
        }
        
        // Simple JSON parsing to extract common error message fields
        // This could be enhanced with a proper JSON parser if needed
        String[] possibleFields = {"message", "error", "error_description", "detail"};
        
        for (String field : possibleFields) {
            String pattern = "\"" + field + "\"\\s*:\\s*\"([^\"]+)\"";
            java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE);
            java.util.regex.Matcher matcher = regex.matcher(body);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        
        // If no specific error message found, return first 200 characters of body
        return body.length() > 200 ? body.substring(0, 200) + "..." : body;
    }
}