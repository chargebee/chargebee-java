package com.chargebee.v4.services;

import com.chargebee.v4.client.request.RequestContext;
import com.chargebee.v4.client.ChargebeeClient;
import com.chargebee.v4.client.request.RequestOptions;
import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.transport.Request;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.transport.UrlBuilder;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * Base class for all Chargebee resource services.
 * Provides common functionality and per-request options support.
 */
@SuppressWarnings("unchecked")
public abstract class BaseService<T extends BaseService<T>> {
    protected final ChargebeeClient client;
    protected final RequestOptions options;
    
    protected BaseService(ChargebeeClient client) {
        this(client, RequestOptions.empty());
    }
    
    protected BaseService(ChargebeeClient client, RequestOptions options) {
        this.client = client;
        this.options = options;
    }
    
    /**
     * Create a new service instance with additional request options.
     * Internal method - not part of the public API.
     * Subclasses must implement this to return their specific type.
     */
    abstract T with(RequestOptions newOptions);

    /**
     * Apply explicit RequestOptions for subsequent calls on this service instance.
     */
    public T withOptions(RequestOptions explicitOptions) {
        if (explicitOptions == null) {
            return (T) this;
        }
        RequestOptions merged = mergeOptions(this.options, explicitOptions);
        return with(merged);
    }

    /**
     * Alias for withOptions for natural readability.
     */
    public T options(RequestOptions explicitOptions) {
        return withOptions(explicitOptions);
    }

    public T header(String key, String value) {
        RequestOptions newOptions = (this.options == null ? RequestOptions.empty() : this.options)
            .withHeader(key, value);
        return with(newOptions);
    }

    public T headers(Map<String, String> headers) {
        RequestOptions newOptions = (this.options == null ? RequestOptions.empty() : this.options)
            .withHeaders(headers);
        return with(newOptions);
    }
    
    /**
     * Get a client instance with merged headers (client + request options).
     */
    protected ChargebeeClient clientWithOptions() {
        if (options.getHeaders().isEmpty()) {
            return client;
        }
        // client.headers() returns HeaderedChargebeeClient, but for our use case,
        // we need the original client since our methods expect ChargebeeClient.
        // Instead, we'll handle headers differently in the service methods.
        return client;
    }
    
    /**
     * Get the merged headers for this request.
     */
    protected Map<String, String> getMergedHeaders() {
        return options.getHeaders();
    }

    /**
     * Helper: Client instance appropriate for builders.
     * Returns the original client if no headers, otherwise a headered client.
     */
    protected Object clientForBuilders() {
        return this; // Builders will delegate back to the service for execution
    }

    /**
     * Helper: POST with optional headers.
     */
    protected Response post(String path, Map<String, Object> formData) throws ChargebeeException {
        String fullUrl = UrlBuilder.buildUrl(client.getBaseUrl(), path, null);
        Request.Builder builder = Request.builder()
                .method("POST")
                .url(fullUrl)
                .formBody(formData);
        applyMergedHeaders(builder);
        return client.executeWithInterceptor(builder.build());
    }

    /**
     * Helper: POST JSON with optional headers.
     */
    protected Response postJson(String path, String jsonData) throws ChargebeeException {
        String fullUrl = UrlBuilder.buildUrl(client.getBaseUrl(), path, null);
        Request.Builder builder = Request.builder()
                .method("POST")
                .url(fullUrl)
                .jsonBody(jsonData);
        applyMergedHeaders(builder);
        return client.executeWithInterceptor(builder.build());
    }

    /**
     * Helper: POST async with optional headers.
     */
    protected CompletableFuture<Response> postAsync(String path, Map<String, Object> formData) {
        String fullUrl = UrlBuilder.buildUrl(client.getBaseUrl(), path, null);
        Request.Builder builder = Request.builder()
                .method("POST")
                .url(fullUrl)
                .formBody(formData);
        applyMergedHeaders(builder);
        return client.executeWithInterceptorAsync(builder.build());
    }

    /**
     * Helper: POST JSON async with optional headers.
     */
    protected CompletableFuture<Response> postJsonAsync(String path, String jsonData) {
        String fullUrl = UrlBuilder.buildUrl(client.getBaseUrl(), path, null);
        Request.Builder builder = Request.builder()
                .method("POST")
                .url(fullUrl)
                .jsonBody(jsonData);
        applyMergedHeaders(builder);
        return client.executeWithInterceptorAsync(builder.build());
    }


    /**
     * Build path with path parameters replaced.
     */
    protected String buildPathWithParams(String pathTemplate, String paramName, String paramValue) {
        if (paramValue == null) {
            throw new IllegalArgumentException("Path parameter '" + paramName + "' cannot be null");
        }
        return pathTemplate.replace("{" + paramName + "}", paramValue);
    }
    
    /**
     * Parse response into response object using fromJson method.
     * This follows the same pattern as model classes.
     */
    protected <R> R parseResponse(Response response, Class<R> responseClass) {
        try {
            String json = response.getBodyAsString();
            return (R) responseClass.getDeclaredMethod("fromJson", String.class).invoke(null, json);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response of type " + responseClass.getSimpleName() + " from JSON", e);
        }
    }
    
    /**
     * GET with Object query parameters.
     */
    protected Response get(String path, Map<String, Object> queryParams) throws ChargebeeException {
        String fullUrl = UrlBuilder.buildUrl(client.getBaseUrl(), path, queryParams);
        Request.Builder builder = Request.builder()
                .method("GET")
                .url(fullUrl);
        applyMergedHeaders(builder);
        return client.executeWithInterceptor(builder.build());
    }
    
    /**
     * GET async with Object query parameters.
     */
    protected CompletableFuture<Response> getAsync(String path, Map<String, Object> queryParams) {
        String fullUrl = UrlBuilder.buildUrl(client.getBaseUrl(), path, queryParams);
        Request.Builder builder = Request.builder()
                .method("GET")
                .url(fullUrl);
        applyMergedHeaders(builder);
        return client.executeWithInterceptorAsync(builder.build());
    }

    private void applyMergedHeaders(Request.Builder builder) {
        RequestContext operation = new RequestContext(options.getHeaders());
        Map<String, String> merged = client.getClientHeaders().merge(operation).getHeaders();
        for (Map.Entry<String, String> h : merged.entrySet()) {
            builder.header(h.getKey(), h.getValue());
        }
        // Apply per-request options to the transport request
        if (options.getMaxNetworkRetries() != null) {
            builder.maxNetworkRetriesOverride(options.getMaxNetworkRetries());
        }
        if (options.getFollowRedirects() != null) {
            builder.followRedirectsOverride(options.getFollowRedirects());
        }
    }

    private static RequestOptions mergeOptions(RequestOptions base, RequestOptions override) {
        if (base == null) {
            return override == null ? RequestOptions.empty() : override;
        }
        if (override == null) {
            return base;
        }
        Map<String, String> headers = new HashMap<>(base.getHeaders());
        headers.putAll(override.getHeaders());
        RequestOptions.Builder builder = RequestOptions.builder().headers(headers);
        Integer overrideRetries = override.getMaxNetworkRetries();
        Integer baseRetries = base.getMaxNetworkRetries();
        if (overrideRetries != null) {
            builder.maxNetworkRetries(overrideRetries);
        } else if (baseRetries != null) {
            builder.maxNetworkRetries(baseRetries);
        }
        return builder.build();
    }
}