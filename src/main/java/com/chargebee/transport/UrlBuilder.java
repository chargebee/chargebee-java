package com.chargebee.transport;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Utility for building URLs from components (client layer utility).
 * Handles proper encoding, path joining, and query parameter construction.
 */
public final class UrlBuilder {
    
    private UrlBuilder() {
        // Utility class
    }
    
    
    /**
     * Build URL with query parameters.
     * Supports both single values and List&lt;String&gt; values.
     */
    public static String buildUrl(String baseUrl, String path, Map<String, Object> queryParams) {
        if (baseUrl == null) {
            throw new IllegalArgumentException("Base URL cannot be null");
        }
        
        StringBuilder url = new StringBuilder(baseUrl);
        
        // Add path
        if (path != null && !path.isEmpty()) {
            // Ensure proper slash handling
            if (!baseUrl.endsWith("/") && !path.startsWith("/")) {
                url.append('/');
            } else if (baseUrl.endsWith("/") && path.startsWith("/")) {
                url.setLength(url.length() - 1); // Remove trailing slash from base
            }
            url.append(path);
        }
        
        // Add query parameters
        if (queryParams != null && !queryParams.isEmpty()) {
            String queryString = encodeQueryParams(queryParams);
            if (!queryString.isEmpty()) {
                url.append('?').append(queryString);
            }
        }
        
        return url.toString();
    }
    
    /**
     * Encode query parameters into a query string.
     * Handles both single values and List&lt;String&gt; values.
     */
    @SuppressWarnings("unchecked")
    public static String encodeQueryParams(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        
        StringBuilder query = new StringBuilder();
        boolean first = true;
        
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            
            if (key == null || value == null) {
                continue;
            }
            
            if (value instanceof List) {
                // Handle List<String> values
                List<String> values = (List<String>) value;
                for (String listValue : values) {
                    if (listValue == null) {
                        continue;
                    }
                    
                    if (!first) {
                        query.append('&');
                    }
                    first = false;
                    
                    query.append(urlEncode(key));
                    query.append('=');
                    query.append(urlEncode(listValue));
                }
            } else {
                // Handle single values
                if (!first) {
                    query.append('&');
                }
                first = false;
                
                query.append(urlEncode(key));
                query.append('=');
                query.append(urlEncode(value.toString()));
            }
        }
        
        return query.toString();
    }
    
    
    /**
     * URL-encode a string value.
     * 
     * @param value the value to encode
     * @return URL-encoded value
     */
    public static String urlEncode(String value) {
        if (value == null) {
            return "";
        }
        
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding not supported", e);
        }
    }
    
    /**
     * Validate that a base URL is properly formatted.
     * 
     * @param baseUrl the base URL to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidBaseUrl(String baseUrl) {
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            return false;
        }
        
        try {
            new java.net.URL(baseUrl);
            return baseUrl.startsWith("http://") || baseUrl.startsWith("https://");
        } catch (java.net.MalformedURLException e) {
            return false;
        }
    }
    
    /**
     * Join path segments with proper slash handling.
     * 
     * @param segments path segments to join
     * @return joined path
     */
    public static String joinPaths(String... segments) {
        if (segments == null || segments.length == 0) {
            return "";
        }
        
        StringBuilder path = new StringBuilder();
        
        for (int i = 0; i < segments.length; i++) {
            String segment = segments[i];
            if (segment == null || segment.isEmpty()) {
                continue;
            }
            
            // Remove leading slash from non-first segments
            if (i > 0 && segment.startsWith("/")) {
                segment = segment.substring(1);
            }
            
            // Remove trailing slash from non-last segments
            if (i < segments.length - 1 && segment.endsWith("/")) {
                segment = segment.substring(0, segment.length() - 1);
            }
            
            if (path.length() > 0 && !path.toString().endsWith("/")) {
                path.append('/');
            }
            
            path.append(segment);
        }
        
        return path.toString();
    }
}