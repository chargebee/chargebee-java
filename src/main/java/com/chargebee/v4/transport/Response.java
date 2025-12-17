package com.chargebee.v4.transport;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Immutable HTTP response representation.
 */
public final class Response {
    private final int statusCode;
    private final Map<String, List<String>> headers;
    private final byte[] body;
    private final String contentType;
    private final String charset;
    
    public Response(int statusCode, Map<String, List<String>> headers, byte[] body) {
        this.statusCode = statusCode;
        this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
        this.body = body != null ? body.clone() : new byte[0];
        this.contentType = parseContentType(headers);
        this.charset = parseCharset(headers);
    }
    
    public int getStatusCode() { 
        return statusCode; 
    }
    
    public Map<String, List<String>> getHeaders() { 
        return headers; 
    }
    
    public byte[] getBody() { 
        return body.clone(); 
    }
    
    public String getContentType() { 
        return contentType; 
    }
    
    public String getCharset() { 
        return charset; 
    }
    
    public String getBodyAsString() {
        return new String(body, charset != null ? 
            Charset.forName(charset) : StandardCharsets.UTF_8);
    }
    
    public String getBodyAsString(Charset charset) {
        return new String(body, charset);
    }
    
    public boolean isSuccessful() {
        return statusCode >= 200 && statusCode < 300;
    }
    
    /**
     * Get a single header value by name (case-insensitive).
     *
     * @param name the header name
     * @return the first header value, or null if not found
     */
    public String getHeader(String name) {
        List<String> values = getHeaderValue(headers, name);
        return (values != null && !values.isEmpty()) ? values.get(0) : null;
    }
    
    /**
     * Get all values for a header by name (case-insensitive).
     *
     * @param name the header name
     * @return list of header values, or null if not found
     */
    public List<String> getHeaderValues(String name) {
        return getHeaderValue(headers, name);
    }
    
    private static String parseContentType(Map<String, List<String>> headers) {
        List<String> values = getHeaderValue(headers, "Content-Type");
        if (values == null || values.isEmpty()) return null;
        String contentType = values.get(0);
        int semicolon = contentType.indexOf(';');
        return semicolon >= 0 ? contentType.substring(0, semicolon).trim() : contentType;
    }
    
    private static String parseCharset(Map<String, List<String>> headers) {
        List<String> values = getHeaderValue(headers, "Content-Type");
        if (values == null || values.isEmpty()) return "UTF-8";
        String contentType = values.get(0);
        int charsetIndex = contentType.toLowerCase().indexOf("charset=");
        if (charsetIndex >= 0) {
            String charset = contentType.substring(charsetIndex + 8).trim();
            int semicolon = charset.indexOf(';');
            return semicolon >= 0 ? charset.substring(0, semicolon).trim() : charset;
        }
        return "UTF-8";
    }
    
    private static List<String> getHeaderValue(Map<String, List<String>> headers, String name) {
        // Case-insensitive header lookup
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (name.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;
        Response response = (Response) o;
        return statusCode == response.statusCode &&
               Objects.equals(headers, response.headers) &&
               Arrays.equals(body, response.body);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(statusCode, headers, Arrays.hashCode(body));
    }
    
    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", headers=" + headers +
                ", contentType='" + contentType + '\'' +
                ", charset='" + charset + '\'' +
                ", bodyLength=" + body.length +
                '}';
    }
}