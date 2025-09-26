package com.chargebee.client.request;

import com.chargebee.client.ChargebeeClient;
import com.chargebee.transport.Request;
import com.chargebee.transport.Response;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Wrapper for HTTP requests that provides context and execution control for interceptors.
 * 
 * <p>This class encapsulates a transport request along with its execution environment, 
 * allowing interceptors to examine the request, modify it, and control whether to proceed 
 * with normal execution or provide a custom response.</p>
 * 
 * <p>The {@code proceed()} method allows interceptors to continue with the standard 
 * request execution after any modifications.</p>
 * 
 * <pre>{@code
 * RequestInterceptor interceptor = requestWrap -> {
 *     // Log the request
 *     System.out.println("Making request to: " + requestWrap.getRequest().getUrl());
 *     
 *     // Add custom header (would need to rebuild request)
 *     Request.Builder builder = Request.builder()
 *         .method(requestWrap.getRequest().getMethod())
 *         .url(requestWrap.getRequest().getUrl())
 *         .headers(requestWrap.getRequest().getHeaders())
 *         .header("X-Custom-Header", "value");
 *     requestWrap.setRequest(builder.build());
 *     
 *     // Proceed with normal execution
 *     return requestWrap.proceed();
 * };
 * }</pre>
 * 
 * @see RequestInterceptor
 * @see Request
 * @see Response
 */
public final class RequestWrap implements Callable<Response> {
    
    /**
     * The Chargebee client instance handling this request.
     */
    public final ChargebeeClient client;
    
    /**
     * The transport request being wrapped.
     */
    private Request request;
    
    /**
     * Create a new request wrapper.
     * 
     * @param client the Chargebee client instance
     * @param request the transport request to wrap
     */
    public RequestWrap(ChargebeeClient client, Request request) {
        this.client = client;
        this.request = request;
    }
    
    /**
     * Get the wrapped transport request.
     * 
     * @return the transport request instance
     */
    public Request getRequest() {
        return request;
    }
    
    /**
     * Set a new transport request (useful for modifications).
     * 
     * @param request the new transport request
     */
    public void setRequest(Request request) {
        this.request = request;
    }
    
    /**
     * Get the client handling this request.
     * 
     * @return the Chargebee client instance
     */
    public ChargebeeClient getClient() {
        return client;
    }
    
    /**
     * Proceed with the normal request execution.
     * 
     * <p>This method should be called by interceptors to continue with the 
     * standard request processing after any modifications.</p>
     * 
     * @return the transport response from normal execution
     * @throws Exception if request execution fails
     */
    public Response proceed() throws Exception {
        return call();
    }
    
    /**
     * Proceed with the normal request execution asynchronously.
     * 
     * <p>This method should be called by async interceptors to continue with the 
     * standard request processing after any modifications.</p>
     * 
     * @return a CompletableFuture that will complete with the transport response
     */
    public CompletableFuture<Response> proceedAsync() {
        return client.sendWithRetryAsync(request);
    }
    
    /**
     * Execute the wrapped request using the transport layer.
     * 
     * <p>This method sends the request through the client's retry logic 
     * and transport layer.</p>
     * 
     * @return the transport response
     * @throws Exception if request execution fails
     */
    @Override
    public Response call() throws Exception {
        return client.sendWithRetry(request);
    }
}