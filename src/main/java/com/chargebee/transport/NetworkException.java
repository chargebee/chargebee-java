package com.chargebee.transport;

/**
 * Network connectivity issues, DNS failures.
 */
public class NetworkException extends TransportException {
    
    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}