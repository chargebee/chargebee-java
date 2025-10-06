package com.chargebee.core.http;

import com.chargebee.v4.transport.Response;

/**
 * Wrapper for HTTP responses that provides access to both raw response data
 * and parsed objects.
 */
public final class ChargebeeApiResponse<T> {
    private final Response rawResponse;
    private final T parsedObject;
    
    public ChargebeeApiResponse(Response rawResponse, T parsedObject) {
        this.rawResponse = rawResponse;
        this.parsedObject = parsedObject;
    }
    
    /**
     * Get the HTTP status code.
     */
    public int statusCode() {
        return rawResponse.getStatusCode();
    }
    
    /**
     * Get the response headers.
     */
    public Headers headers() {
        return new Headers(rawResponse.getHeaders());
    }
    
    /**
     * Get the raw response body as string.
     */
    public String rawBody() {
        return rawResponse.getBodyAsString();
    }
    
    /**
     * Get the raw response body as bytes.
     */
    public byte[] rawBodyBytes() {
        return rawResponse.getBody();
    }
    
    /**
     * Get the parsed object.
     */
    public T parse() {
        return parsedObject;
    }
    
    /**
     * Check if the response was successful (2xx status code).
     */
    public boolean isSuccessful() {
        return rawResponse.isSuccessful();
    }
    
    /**
     * Get the underlying raw Response object.
     */
    public Response getRawResponse() {
        return rawResponse;
    }
    
    @Override
    public String toString() {
        return "ChargebeeApiResponse{" +
                "statusCode=" + statusCode() +
                ", headers=" + headers().names().size() + " headers" +
                ", parsedObject=" + parsedObject +
                '}';
    }
}