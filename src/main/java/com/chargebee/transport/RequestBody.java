package com.chargebee.transport;

import java.io.IOException;
import java.util.Map;

/**
 * Abstract request body representation.
 */
public abstract class RequestBody {
    
    public abstract String getContentType();
    
    public abstract byte[] getBytes() throws IOException;
    
    public static RequestBody form(Map<String, Object> data) {
        return new FormRequestBody(data);
    }
    
    public static RequestBody json(String json) {
        return new JsonRequestBody(json);
    }
    
    public static RequestBody json(Object object) {
        // Simple JSON serialization - in real implementation use Jackson/Gson
        return new JsonRequestBody(object.toString());
    }
    
    public static RequestBody raw(byte[] data, String contentType) {
        return new RawRequestBody(data, contentType);
    }
}