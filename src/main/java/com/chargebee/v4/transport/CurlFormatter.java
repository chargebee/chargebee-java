package com.chargebee.v4.transport;

import java.util.Map;

/**
 * Utility for formatting HTTP requests as curl commands for debugging.
 */
public final class CurlFormatter {
    
    private CurlFormatter() {
        // Utility class
    }
    
    /**
     * Format an HTTP request as a curl command.
     * 
     * @param request the HTTP request to format
     * @return the curl command string
     */
    public static String formatAsCurl(Request request) {
        StringBuilder curl = new StringBuilder("curl");
        
        // Add method if not GET
        if (!"GET".equals(request.getMethod())) {
            curl.append(" -X ").append(request.getMethod());
        }
        
        // Add headers
        Map<String, String> headers = request.getHeaders();
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String headerName = entry.getKey();
                String headerValue = entry.getValue();
                
                if (headerValue != null) {
                    curl.append(" \\\n  -H '")
                        .append(escapeForShell(headerName))
                        .append(": ")
                        .append(escapeForShell(headerValue))
                        .append("'");
                }
            }
        }
        
        // Add request body if present
        RequestBody requestBody = request.getBody();
        if (requestBody != null) {
            String bodyContent = getBodyContent(requestBody);
            if (bodyContent != null && !bodyContent.isEmpty()) {
                // URL decode for better readability in curl command
                String decodedContent = urlDecode(bodyContent);
                curl.append(" \\\n  -d '").append(escapeForShell(decodedContent)).append("'");
            }
        }
        
        // Add URL (always last)
        curl.append(" \\\n  '").append(escapeForShell(request.getUrl())).append("'");
        
        return curl.toString();
    }
    
    /**
     * Format an HTTP request as a compact curl command (single line).
     * 
     * @param request the HTTP request to format
     * @return the compact curl command string
     */
    public static String formatAsCurlCompact(Request request) {
        StringBuilder curl = new StringBuilder("curl");
        
        // Add method if not GET
        if (!"GET".equals(request.getMethod())) {
            curl.append(" -X ").append(request.getMethod());
        }
        
        // Add headers
        Map<String, String> headers = request.getHeaders();
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String headerName = entry.getKey();
                String headerValue = entry.getValue();
                
                if (headerValue != null) {
                    curl.append(" -H '")
                        .append(escapeForShell(headerName))
                        .append(": ")
                        .append(escapeForShell(headerValue))
                        .append("'");
                }
            }
        }
        
        // Add request body if present
        RequestBody requestBody = request.getBody();
        if (requestBody != null) {
            String bodyContent = getBodyContent(requestBody);
            if (bodyContent != null && !bodyContent.isEmpty()) {
                // URL decode for better readability in curl command
                String decodedContent = urlDecode(bodyContent);
                curl.append(" -d '").append(escapeForShell(decodedContent)).append("'");
            }
        }
        
        // Add URL
        curl.append(" '").append(escapeForShell(request.getUrl())).append("'");
        
        return curl.toString();
    }
    
    /**
     * Get the content of a request body as string.
     */
    private static String getBodyContent(RequestBody requestBody) {
        if (requestBody == null) {
            return null;
        }
        
        try {
            byte[] bodyBytes = requestBody.getBytes();
            return new String(bodyBytes, java.nio.charset.StandardCharsets.UTF_8);
            
        } catch (Exception e) {
            return "[Error reading body: " + e.getMessage() + "]";
        }
    }
    
    /**
     * URL decode a string for better readability in curl commands.
     */
    private static String urlDecode(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        
        try {
            return java.net.URLDecoder.decode(input, "UTF-8");
        } catch (Exception e) {
            // If decoding fails, return the original string
            return input;
        }
    }
    
    /**
     * Escape a string for safe use in shell commands.
     * This is a simple implementation - for production use, consider more robust escaping.
     */
    private static String escapeForShell(String input) {
        if (input == null) {
            return "";
        }
        
        // Replace single quotes with '\'' (end quote, escaped quote, start quote)
        return input.replace("'", "'\"'\"'");
    }
    
    /**
     * Sanitize sensitive headers for logging (mask API keys, tokens, etc.).
     * 
     * @param request the request to sanitize
     * @return a new request with sensitive headers masked
     */
    public static Request sanitizeForLogging(Request request) {
        Map<String, String> headers = request.getHeaders();
        if (headers == null || headers.isEmpty()) {
            return request;
        }
        
        Request.Builder builder = Request.builder()
            .method(request.getMethod())
            .url(request.getUrl());
        
        // Copy headers, masking sensitive ones
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String headerName = entry.getKey();
            String headerValue = entry.getValue();
            
            if (isSensitiveHeader(headerName)) {
                // Mask sensitive headers
                builder.header(headerName, "[MASKED]");
            } else {
                // Copy non-sensitive headers as-is
                if (headerValue != null) {
                    builder.header(headerName, headerValue);
                }
            }
        }
        
        // Copy the request body (it doesn't contain sensitive data, just form parameters)
        if (request.getBody() != null) {
            try {
                byte[] bodyBytes = request.getBody().getBytes();
                builder.rawBody(bodyBytes, request.getBody().getContentType());
            } catch (Exception e) {
                // If we can't copy the body, skip it rather than fail
                System.err.println("Warning: Could not copy request body during sanitization: " + e.getMessage());
            }
        }
        
        return builder.build();
    }
    
    /**
     * Check if a header contains sensitive information that should be masked.
     */
    private static boolean isSensitiveHeader(String headerName) {
        if (headerName == null) {
            return false;
        }
        
        String lowerName = headerName.toLowerCase();
        return lowerName.contains("authorization") ||
               lowerName.contains("token") ||
               lowerName.contains("key") ||
               lowerName.contains("secret") ||
               lowerName.contains("password") ||
               lowerName.contains("credential");
    }
}