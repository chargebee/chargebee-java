package com.chargebee.transport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Console-based HTTP request/response logger.
 * Logs requests as curl commands and responses with status codes and timing.
 */
public class ConsoleRequestLogger implements RequestLogger {
    
    public enum LogLevel {
        NONE,     // No logging
        BASIC,    // Just URL and status
        HEADERS,  // Include headers
        FULL,     // Include headers and body
        CURL      // Include curl command
    }
    
    private final LogLevel logLevel;
    private final boolean maskSensitiveData;
    private final DateTimeFormatter timeFormatter;
    
    public ConsoleRequestLogger() {
        this(LogLevel.CURL, true);
    }
    
    public ConsoleRequestLogger(LogLevel logLevel) {
        this(logLevel, true);
    }
    
    public ConsoleRequestLogger(LogLevel logLevel, boolean maskSensitiveData) {
        this.logLevel = logLevel;
        this.maskSensitiveData = maskSensitiveData;
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    }
    
    @Override
    public void logRequest(Request request) {
        if (!isEnabled()) {
            return;
        }
        
        String timestamp = LocalDateTime.now().format(timeFormatter);
        System.out.println("\n" + repeatChar('=', 80));
        System.out.println("📤 HTTP REQUEST [" + timestamp + "]");
        System.out.println(repeatChar('=', 80));
        
        Request logRequest = maskSensitiveData ? CurlFormatter.sanitizeForLogging(request) : request;
        
        switch (logLevel) {
            case NONE:
                // No logging
                break;
            case BASIC:
                logBasicRequest(logRequest);
                break;
            case HEADERS:
                logRequestWithHeaders(logRequest);
                break;
            case FULL:
                logFullRequest(logRequest);
                break;
            case CURL:
                logCurlRequest(logRequest);
                break;
        }
    }
    
    @Override
    public void logResponse(Request request, Response response, long durationMs) {
        if (!isEnabled()) {
            return;
        }
        
        String timestamp = LocalDateTime.now().format(timeFormatter);
        String statusIcon = response.isSuccessful() ? "✅" : "❌";
        
        System.out.println("\n" + repeatChar('-', 80));
        System.out.println(statusIcon + " HTTP RESPONSE [" + timestamp + "] (" + durationMs + "ms)");
        System.out.println(repeatChar('-', 80));
        
        logBasicResponse(response);
        
        if (logLevel == LogLevel.HEADERS || logLevel == LogLevel.FULL || logLevel == LogLevel.CURL) {
            logResponseHeaders(response);
        }
        
        if (logLevel == LogLevel.FULL || logLevel == LogLevel.CURL) {
            logResponseBody(response);
        }
        
        System.out.println(repeatChar('=', 80));
    }
    
    @Override
    public void logError(Request request, Throwable error, long durationMs) {
        if (!isEnabled()) {
            return;
        }
        
        String timestamp = LocalDateTime.now().format(timeFormatter);
        System.out.println("\n" + repeatChar('-', 80));
        System.out.println("💥 HTTP ERROR [" + timestamp + "] (" + durationMs + "ms)");
        System.out.println(repeatChar('-', 80));
        System.out.println("Error: " + error.getClass().getSimpleName() + ": " + error.getMessage());
        
        if (error instanceof HttpException) {
            HttpException httpError = (HttpException) error;
            System.out.println("Status Code: " + httpError.getStatusCode());
            if (httpError.getResponse() != null) {
                logBasicResponse(httpError.getResponse());
            }
        }
        
        System.out.println(repeatChar('=', 80));
    }
    
    @Override
    public boolean isEnabled() {
        return logLevel != LogLevel.NONE;
    }
    
    private void logBasicRequest(Request request) {
        System.out.println("Method: " + request.getMethod());
        System.out.println("URL: " + request.getUrl());
    }
    
    private void logRequestWithHeaders(Request request) {
        logBasicRequest(request);
        logRequestHeaders("Request Headers:", request.getHeaders());
    }
    
    private void logFullRequest(Request request) {
        logRequestWithHeaders(request);
        logRequestBody(request);
    }
    
    private void logCurlRequest(Request request) {
        System.out.println("Curl Command:");
        System.out.println(CurlFormatter.formatAsCurl(request));
        System.out.println();
        System.out.println("Request Details:");
        logBasicRequest(request);
        logRequestBody(request);
    }
    
    private void logRequestBody(Request request) {
        RequestBody body = request.getBody();
        if (body != null) {
            System.out.println("\nRequest Body:");
            String bodyContent = getBodyPreview(body);
            System.out.println(bodyContent);
        }
    }
    
    private void logBasicResponse(Response response) {
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Content-Type: " + (response.getContentType() != null ? response.getContentType() : "unknown"));
        System.out.println("Body Length: " + response.getBody().length + " bytes");
    }
    
    private void logResponseHeaders(Response response) {
        logHeaders("Response Headers:", response.getHeaders());
    }
    
    private void logResponseBody(Response response) {
        String bodyContent = response.getBodyAsString();
        if (bodyContent != null && !bodyContent.trim().isEmpty()) {
            System.out.println("\nResponse Body:");
            // Limit body preview to avoid overwhelming output
            if (bodyContent.length() > 2000) {
                System.out.println(bodyContent.substring(0, 2000) + "\n... [truncated, " + bodyContent.length() + " total chars]");
            } else {
                System.out.println(bodyContent);
            }
        }
    }
    
    private void logHeaders(String title, Map<String, List<String>> headers) {
        if (headers != null && !headers.isEmpty()) {
            System.out.println("\n" + title);
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String headerName = entry.getKey();
                List<String> headerValues = entry.getValue();
                if (headerValues != null) {
                    for (String value : headerValues) {
                        System.out.println("  " + headerName + ": " + value);
                    }
                }
            }
        }
    }
    
    private void logRequestHeaders(String title, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            System.out.println("\n" + title);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String headerName = entry.getKey();
                String headerValue = entry.getValue();
                if (headerValue != null) {
                    System.out.println("  " + headerName + ": " + headerValue);
                }
            }
        }
    }
    
    private String getBodyPreview(RequestBody body) {
        if (body == null) {
            return "[No body]";
        }
        
        try {
            byte[] bodyBytes = body.getBytes();
            String bodyContent = new String(bodyBytes, java.nio.charset.StandardCharsets.UTF_8);
            
            // URL decode for better readability
            String decodedContent = urlDecode(bodyContent);
            
            // Limit body preview to avoid overwhelming output
            if (decodedContent.length() > 1000) {
                return decodedContent.substring(0, 1000) + "\n... [truncated, " + decodedContent.length() + " total chars]";
            } else {
                return decodedContent;
            }
            
        } catch (Exception e) {
            return "[Error reading body: " + e.getMessage() + "]";
        }
    }
    
    /**
     * URL decode a string for better readability in logs.
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
     * Helper method to repeat a character (for Java versions without String.repeat).
     */
    private static String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}