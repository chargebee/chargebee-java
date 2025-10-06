package com.chargebee.core.http;

import java.util.*;

/**
 * Immutable wrapper for HTTP response headers.
 */
public final class Headers {
    private final Map<String, List<String>> headers;
    
    public Headers(Map<String, List<String>> headers) {
        this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
    }
    
    /**
     * Get header value by name (case-insensitive).
     */
    public String get(String name) {
        List<String> values = getAll(name);
        return values != null && !values.isEmpty() ? values.get(0) : null;
    }
    
    /**
     * Get all header values by name (case-insensitive).
     */
    public List<String> getAll(String name) {
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (name.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    /**
     * Check if header exists (case-insensitive).
     */
    public boolean contains(String name) {
        return getAll(name) != null;
    }
    
    /**
     * Get all header names.
     */
    public Set<String> names() {
        return headers.keySet();
    }
    
    /**
     * Get the raw headers map.
     */
    public Map<String, List<String>> toMap() {
        return headers;
    }
    
    @Override
    public String toString() {
        return "Headers{" + headers + "}";
    }
}