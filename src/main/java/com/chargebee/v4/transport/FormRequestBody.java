package com.chargebee.v4.transport;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Form-encoded request body (Chargebee default).
 * Handles nested objects and arrays as required by Chargebee API.
 */
class FormRequestBody extends RequestBody {
    private final Map<String, Object> data;
    
    FormRequestBody(Map<String, Object> data) {
        this.data = new HashMap<>(data);
    }
    
    @Override
    public String getContentType() {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }
    
    @Override
    public byte[] getBytes() throws IOException {
        return encodeFormData(data).getBytes(StandardCharsets.UTF_8);
    }
    
    private String encodeFormData(Map<String, Object> data) {
        StringBuilder buf = new StringBuilder();
        boolean first = true;
        
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() == null) continue;
            
            if (!first) buf.append('&');
            first = false;
            
            String key = entry.getKey();
            Object value = entry.getValue();
            
            if (value instanceof List) {
                encodeList(buf, key, (List<?>) value);
            } else if (value instanceof Map) {
                encodeMap(buf, key, (Map<?, ?>) value);
            } else {
                buf.append(urlEncode(key)).append('=').append(urlEncode(value.toString()));
            }
        }
        return buf.toString();
    }
    
    private void encodeList(StringBuilder buf, String key, List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) buf.append('&');
            String indexedKey = key + "[" + i + "]";
            Object value = list.get(i);
            buf.append(urlEncode(indexedKey)).append('=')
               .append(urlEncode(value != null ? value.toString() : ""));
        }
    }
    
    private void encodeMap(StringBuilder buf, String prefix, Map<?, ?> map) {
        boolean first = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (!first) buf.append('&');
            first = false;
            
            String key = prefix + "[" + entry.getKey() + "]";
            Object value = entry.getValue();
            
            if (value instanceof Map) {
                encodeMap(buf, key, (Map<?, ?>) value);
            } else if (value instanceof List) {
                encodeList(buf, key, (List<?>) value);
            } else {
                buf.append(urlEncode(key)).append('=')
                   .append(urlEncode(value != null ? value.toString() : ""));
            }
        }
    }
    
    private String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormRequestBody)) return false;
        FormRequestBody that = (FormRequestBody) o;
        return Objects.equals(data, that.data);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
    
    @Override
    public String toString() {
        return "FormRequestBody{data=" + data + "}";
    }
}