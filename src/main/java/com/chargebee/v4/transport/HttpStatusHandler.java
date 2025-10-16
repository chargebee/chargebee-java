package com.chargebee.v4.transport;

import com.chargebee.v4.exceptions.*;
import com.chargebee.v4.internal.JsonUtil;

/**
 * Utility class for handling HTTP status codes and creating appropriate exceptions.
 */
public final class HttpStatusHandler {

    private static final String UBB_BATCH_INGESTION_INVALID_REQUEST = "ubb_batch_ingestion_invalid_request";

    private HttpStatusHandler() {
        // Utility class
    }

    /**
     * Validate HTTP response and throw appropriate exception if not successful.
     *
     * @param request the HTTP request that generated this response
     * @param response the HTTP response to validate
     * @throws HttpException if the response indicates an error (4xx or 5xx)
     */
    public static void validateResponse(Request request, Response response) throws HttpException {
        int statusCode = response.getStatusCode();

        if (response.isSuccessful()) {
            return; // 2xx status codes are successful
        }

        // Try to parse error response as JSON and throw appropriate API exception
        // Only treat as API exception if it has the 'type' field or has both 'message' and 'api_error_code'
        String body = response.getBodyAsString();
        if (body != null && !body.trim().isEmpty() && body.trim().startsWith("{")) {
            boolean hasType = JsonUtil.hasValue(body, "type");
            boolean hasMessage = JsonUtil.hasValue(body, "message");
            boolean hasApiErrorCode = JsonUtil.hasValue(body, "api_error_code");

            // Treat as Chargebee API error if it has type field, or both message and api_error_code
            if (hasType || (hasMessage && hasApiErrorCode)) {
                throwAPIException(statusCode, body, request, response);
                return;
            }
        }

        // Fallback to generic HTTP exceptions if response is not valid JSON
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
     * Throw appropriate API exception based on error type in JSON response.
     */
    private static void throwAPIException(int statusCode, String jsonResponse, Request request, Response response)
            throws APIException {
        String type = JsonUtil.getString(jsonResponse, "type");
        String apiErrorCode = JsonUtil.getString(jsonResponse, "api_error_code");
        String message = JsonUtil.getString(jsonResponse, "message");
        if (message == null) {
            message = "API Error";
        }

        // Check if this is a batch API error by examining the request URL
        boolean isBatchApi = isBatchApiRequest(request);

        // Throw specific exception based on error type
        if (UBB_BATCH_INGESTION_INVALID_REQUEST.equals(type)) {
            throw new UbbBatchIngestionInvalidRequestException(
                statusCode, type, apiErrorCode, message, jsonResponse, response);
        } else if (isBatchApi) {
            throw new BatchAPIException(
                statusCode, type, apiErrorCode, message, jsonResponse, response);
        } else if ("payment".equals(type)) {
            throw new PaymentException(
                statusCode, type, apiErrorCode, message, jsonResponse, response);
        } else if ("operation_failed".equals(type)) {
            throw new OperationFailedException(
                statusCode, type, apiErrorCode, message, jsonResponse, response);
        } else if ("invalid_request".equals(type)) {
            throw new InvalidRequestException(
                statusCode, type, apiErrorCode, message, jsonResponse, response);
        } else {
            // Generic API exception for unknown types
            throw new APIException(
                statusCode, type, apiErrorCode, message, jsonResponse, response);
        }
    }

    /**
     * Check if the request is a batch API request by examining the URL.
     *
     * @param request the HTTP request
     * @return true if the request URL contains "/batch/"
     */
    private static boolean isBatchApiRequest(Request request) {
        if (request == null || request.getUrl() == null) {
            return false;
        }
        return request.getUrl().contains("/batch/");
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