package com.chargebee.v4.transport;

import java.util.*;

/**
 * Immutable HTTP request representation.
 */
public final class Request {
    public static final String HEADER_RETRY_ATTEMPT = "X-CB-Retry-Attempt";
    
    private final String method;
    private final String url;
    private final Map<String, List<String>> queryParams;
    private final Map<String, String> headers;
    private final RequestBody body;
    private final Integer maxNetworkRetriesOverride;
    private final Boolean followRedirectsOverride;
    
    private Request(Builder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.queryParams = Collections.unmodifiableMap(new HashMap<>(builder.queryParams));
        this.headers = Collections.unmodifiableMap(new HashMap<>(builder.headers));
        this.body = builder.body;
        this.maxNetworkRetriesOverride = builder.maxNetworkRetriesOverride;
        this.followRedirectsOverride = builder.followRedirectsOverride;
    }
    
    public String getMethod() { 
        return method; 
    }
    
    public String getUrl() { 
        return url; 
    }
    
    public Map<String, List<String>> getQueryParams() { 
        return queryParams; 
    }
    
    public Map<String, String> getHeaders() { 
        return headers; 
    }
    
    public RequestBody getBody() { 
        return body; 
    }

    public Integer getMaxNetworkRetriesOverride() {
        return maxNetworkRetriesOverride;
    }

    public Boolean getFollowRedirectsOverride() {
        return followRedirectsOverride;
    }
    
    public Request withHeader(String key, String value) {
        Map<String, String> newHeaders = new HashMap<>(this.headers);
        newHeaders.put(key, value);
        return new Request(this, newHeaders);
    }
    
    private Request(Request source, Map<String, String> newHeaders) {
        this.method = source.method;
        this.url = source.url;
        this.queryParams = source.queryParams;
        this.headers = Collections.unmodifiableMap(newHeaders);
        this.body = source.body;
        this.maxNetworkRetriesOverride = source.maxNetworkRetriesOverride;
        this.followRedirectsOverride = source.followRedirectsOverride;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private String method = "GET";
        private String url;
        private Map<String, List<String>> queryParams = new HashMap<>();
        private Map<String, String> headers = new HashMap<>();
        private RequestBody body;
        private Integer maxNetworkRetriesOverride;
        private Boolean followRedirectsOverride;
        
        public Builder method(String method) {
            this.method = method;
            return this;
        }
        
        public Builder url(String url) {
            this.url = url;
            return this;
        }
        
        public Builder queryParam(String key, String value) {
            queryParams.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
            return this;
        }
        
        public Builder queryParams(Map<String, List<String>> params) {
            this.queryParams.putAll(params);
            return this;
        }
        
        public Builder header(String key, String value) {
            headers.put(key, value);
            return this;
        }
        
        public Builder headers(Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }
        
        public Builder formBody(Map<String, Object> formData) {
            this.body = RequestBody.form(formData);
            return this;
        }
        
        public Builder jsonBody(String json) {
            this.body = RequestBody.json(json);
            return this;
        }
        
        public Builder jsonBody(Object object) {
            this.body = RequestBody.json(object);
            return this;
        }
        
        public Builder rawBody(byte[] data, String contentType) {
            this.body = RequestBody.raw(data, contentType);
            return this;
        }

        public Builder maxNetworkRetriesOverride(Integer maxRetries) {
            this.maxNetworkRetriesOverride = maxRetries;
            return this;
        }

        public Builder followRedirectsOverride(Boolean followRedirects) {
            this.followRedirectsOverride = followRedirects;
            return this;
        }
        
        public Request build() {
            if (url == null) {
                throw new IllegalStateException("URL is required");
            }
            return new Request(this);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return Objects.equals(method, request.method) &&
               Objects.equals(url, request.url) &&
               Objects.equals(queryParams, request.queryParams) &&
               Objects.equals(headers, request.headers) &&
               Objects.equals(body, request.body);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(method, url, queryParams, headers, body);
    }
    
    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", queryParams=" + queryParams +
                ", headers=" + headers +
                ", body=" + body +
                '}';
    }
}