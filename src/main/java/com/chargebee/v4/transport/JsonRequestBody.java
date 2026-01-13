package com.chargebee.v4.transport;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * JSON request body for specific endpoints.
 */
class JsonRequestBody extends RequestBody {
    private final String json;
    
    JsonRequestBody(String json) {
        this.json = json;
    }
    
    @Override
    public String getContentType() {
        return "application/json; charset=UTF-8";
    }
    
    @Override
    public byte[] getBytes() {
        return json.getBytes(StandardCharsets.UTF_8);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JsonRequestBody)) return false;
        JsonRequestBody that = (JsonRequestBody) o;
        return Objects.equals(json, that.json);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(json);
    }
    
    @Override
    public String toString() {
        return "JsonRequestBody{json='" + json + "'}";
    }
}