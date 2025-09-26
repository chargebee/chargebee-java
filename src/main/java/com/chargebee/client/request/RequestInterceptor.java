package com.chargebee.client.request;

import com.chargebee.transport.Request;
import com.chargebee.transport.Response;
import java.util.concurrent.CompletableFuture;

/**
 * Functional interface for intercepting and customizing API requests.
 * 
 * <p>Interceptors can modify requests (e.g., add headers, logging, metrics) 
 * or completely override request execution. They are invoked before sending 
 * requests to the Chargebee API.</p>
 * 
 * <pre>{@code
 * RequestInterceptor loggingInterceptor = requestWrap -> {
 *     System.out.println("Request: " + requestWrap.getRequest().getUrl());
 *     return requestWrap.proceed(); // Continue with normal execution
 * };
 * 
 * ChargebeeClient client = ChargebeeClient.builder()
 *     .apiKey("cb_test_...")
 *     .siteName("acme")
 *     .requestInterceptor(loggingInterceptor)
 *     .build();
 * }</pre>
 * 
 * <p><strong>Thread Safety:</strong> Implementations should be thread-safe if shared across multiple clients 
 * or requests, as the same interceptor instance may be called concurrently.</p>
 * 
 * <p><strong>Performance:</strong> Avoid blocking operations in interceptors to maintain request performance. 
 * Consider async logging or metrics collection for minimal overhead.</p>
 * 
 * @see RequestWrap
 * @see Request
 * @see Response
 */
@FunctionalInterface
public interface RequestInterceptor {
    
    /**
     * Handle the intercepted request.
     * 
     * <p>The interceptor can:</p>
     * <ul>
     *   <li>Examine the request (URL, headers, method, body)</li>
     *   <li>Modify request properties before proceeding</li>
     *   <li>Log or collect metrics</li>
     *   <li>Call {@code requestWrap.proceed()} to continue normal execution</li>
     *   <li>Return a custom {@code Response} to bypass normal execution</li>
     * </ul>
     * 
     * @param requestWrap wrapper containing the transport request and execution context
     * @return the transport response, either from proceeding or custom implementation
     * @throws Exception if request processing fails
     */
    Response handleRequest(RequestWrap requestWrap) throws Exception;
    
    /**
     * Handle the intercepted request asynchronously.
     * 
     * <p>The default implementation calls the synchronous {@code handleRequest} method
     * and wraps the result in a CompletableFuture. Interceptors that need true async
     * behavior should override this method.</p>
     * 
     * <p>The interceptor can:</p>
     * <ul>
     *   <li>Examine the request (URL, headers, method, body)</li>
     *   <li>Modify request properties before proceeding</li>
     *   <li>Log or collect metrics asynchronously</li>
     *   <li>Call {@code requestWrap.proceedAsync()} to continue normal execution</li>
     *   <li>Return a custom {@code Response} to bypass normal execution</li>
     * </ul>
     * 
     * @param requestWrap wrapper containing the transport request and execution context
     * @return a CompletableFuture that will complete with the transport response
     */
    default CompletableFuture<Response> handleRequestAsync(RequestWrap requestWrap) {
        try {
            Response response = handleRequest(requestWrap);
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            CompletableFuture<Response> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(e);
            return failedFuture;
        }
    }
}