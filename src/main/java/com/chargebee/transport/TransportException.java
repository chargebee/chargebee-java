package com.chargebee.transport;

/**
 * Base exception for transport-layer failures.
 * Transport never interprets HTTP status codes - only throws for infrastructure issues.
 */
public class TransportException extends Exception {
    
    public TransportException(String message) {
        super(message);
    }
    
    public TransportException(String message, Throwable cause) {
        super(message, cause);
    }
}