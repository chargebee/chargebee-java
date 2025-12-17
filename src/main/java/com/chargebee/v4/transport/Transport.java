package com.chargebee.v4.transport;

import com.chargebee.v4.exceptions.HttpException;
import com.chargebee.v4.exceptions.TransportException;

import java.util.concurrent.CompletableFuture;

/**
 * HTTP transport abstraction for sending requests and receiving responses.
 * Implementations must be thread-safe.
 */
public interface Transport {
    
    /**
     * Send an HTTP request and return the response.
     * 
     * @param request the HTTP request to send
     * @return the HTTP response
     * @throws TransportException for network, timeout, or configuration failures
     * @throws HttpException for HTTP error status codes (4xx, 5xx)
     */
    Response send(Request request) throws TransportException;
    
    /**
     * Send an HTTP request asynchronously and return a CompletableFuture with the response.
     * 
     * @param request the HTTP request to send
     * @return a CompletableFuture that will complete with the HTTP response,
     *         or complete exceptionally with TransportException or HttpException
     */
    CompletableFuture<Response> sendAsync(Request request);
}