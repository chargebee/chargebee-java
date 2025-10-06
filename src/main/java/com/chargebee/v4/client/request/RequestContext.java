package com.chargebee.v4.client.request;

import java.util.HashMap;
import java.util.Map;

/**
 * Context for request execution that holds headers and other request-specific data.
 * Used internally to pass header information between service layers.
 */
public class RequestContext {
    private final Map<String, String> headers = new HashMap<>();
    
    public RequestContext() {}
    
    public RequestContext(Map<String, String> initialHeaders) {
        if (initialHeaders != null) {
            this.headers.putAll(initialHeaders);
        }
    }
    
    public RequestContext header(String name, String value) {
        headers.put(name, value);
        return this;
    }
    
    public RequestContext headers(Map<String, String> headers) {
        this.headers.putAll(headers);
        return this;
    }
    

    
    /**
     * Get all headers as an immutable map.
     */
    public Map<String, String> getHeaders() {
        return new HashMap<>(headers);
    }
    
    /**
     * Create a new RequestContext with merged headers.
     * Operation headers override client headers.
     */
    public RequestContext merge(RequestContext other) {
        RequestContext merged = new RequestContext(this.headers);
        if (other != null) {
            merged.headers.putAll(other.headers);
        }
        return merged;
    }
    

}