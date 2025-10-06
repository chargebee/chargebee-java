package com.chargebee.v4.transport;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Fake transport implementation for testing.
 * Records requests and returns configurable responses.
 */
public class FakeTransport implements Transport {
    private final Queue<Request> recordedRequests = new ConcurrentLinkedQueue<>();
    private final Queue<Response> queuedResponses = new ConcurrentLinkedQueue<>();
    private Response defaultResponse;
    
    public FakeTransport() {
        // Default successful response
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Content-Type", Arrays.asList("application/json"));
        this.defaultResponse = new Response(200, headers, "{}".getBytes());
    }
    
    @Override
    public Response send(Request request) throws TransportException {
        recordedRequests.add(request);
        
        Response response = queuedResponses.poll();
        return response != null ? response : defaultResponse;
    }
    
    @Override
    public CompletableFuture<Response> sendAsync(Request request) {
        recordedRequests.add(request);
        
        Response response = queuedResponses.poll();
        Response finalResponse = response != null ? response : defaultResponse;
        
        return CompletableFuture.completedFuture(finalResponse);
    }
    
    /**
     * Queue a response to be returned for the next request.
     */
    public FakeTransport queueResponse(Response response) {
        queuedResponses.add(response);
        return this;
    }
    
    /**
     * Queue a response with status code and body.
     */
    public FakeTransport queueResponse(int statusCode, String body) {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Content-Type", Arrays.asList("application/json"));
        queuedResponses.add(new Response(statusCode, headers, body.getBytes()));
        return this;
    }
    
    /**
     * Set the default response for when no responses are queued.
     */
    public FakeTransport setDefaultResponse(Response response) {
        this.defaultResponse = response;
        return this;
    }
    
    /**
     * Set the default response with status code and body.
     */
    public FakeTransport setDefaultResponse(int statusCode, String body) {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Content-Type", Arrays.asList("application/json"));
        this.defaultResponse = new Response(statusCode, headers, body.getBytes());
        return this;
    }
    
    /**
     * Get all recorded requests in order.
     */
    public List<Request> getRecordedRequests() {
        return new ArrayList<>(recordedRequests);
    }
    
    /**
     * Get the last recorded request.
     */
    public Request getLastRequest() {
        return recordedRequests.isEmpty() ? null : 
               recordedRequests.toArray(new Request[0])[recordedRequests.size() - 1];
    }
    
    /**
     * Get the number of requests made.
     */
    public int getRequestCount() {
        return recordedRequests.size();
    }
    
    /**
     * Clear all recorded requests.
     */
    public FakeTransport clearRequests() {
        recordedRequests.clear();
        return this;
    }
    
    /**
     * Clear all queued responses.
     */
    public FakeTransport clearResponses() {
        queuedResponses.clear();
        return this;
    }
    
    /**
     * Reset the fake transport (clear requests and responses).
     */
    public FakeTransport reset() {
        clearRequests();
        clearResponses();
        return this;
    }
    
    /**
     * Verify that a request was made with the given method and URL.
     */
    public boolean hasRequestWithMethodAndUrl(String method, String url) {
        return recordedRequests.stream()
            .anyMatch(req -> method.equals(req.getMethod()) && url.equals(req.getUrl()));
    }
    
    /**
     * Verify that a request was made with the given header.
     */
    public boolean hasRequestWithHeader(String key, String value) {
        return recordedRequests.stream()
            .anyMatch(req -> value.equals(req.getHeaders().get(key)));
    }
    
    /**
     * Verify that a request was made with any of the standard Chargebee headers.
     */
    public boolean hasStandardChargebeeHeaders() {
        if (recordedRequests.isEmpty()) return false;
        
        Request lastRequest = getLastRequest();
        Map<String, String> headers = lastRequest.getHeaders();
        
        // Check for standard headers that should be present
        return headers.containsKey("User-Agent") &&
               headers.containsKey("Accept") &&
               headers.containsKey("Accept-Charset") &&
               headers.containsKey("Authorization");
    }
}