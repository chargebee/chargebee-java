package com.chargebee.transport;

/**
 * Exception thrown when there's an issue with transport or client configuration.
 * This is a runtime exception to allow fluent builder usage.
 */
public class ConfigurationException extends RuntimeException {
    
    public ConfigurationException(String message) {
        super(message);
    }
    
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}