package com.chargebee.v4.exceptions;

/**
 * Exception thrown when there's an issue with SDK configuration.
 * 
 * <p>This is a runtime exception (unchecked) to allow fluent builder usage
 * without requiring try-catch blocks during client setup.
 * 
 * <p>Common causes:
 * <ul>
 *   <li>Missing required configuration (e.g., API key)</li>
 *   <li>Invalid configuration values</li>
 *   <li>Malformed URLs</li>
 * </ul>
 * 
 * <p>Example:
 * <pre>{@code
 * try {
 *     ChargebeeClient client = ChargebeeClient.builder()
 *         .siteName("my-site")
 *         // Missing apiKey!
 *         .build();
 * } catch (ConfigurationException e) {
 *     System.err.println("Configuration error: " + e.getMessage());
 * }
 * }</pre>
 * 
 * <p>Note: This exception is NOT retryable as it indicates a programming error
 * that must be fixed in the code.
 */
public class ConfigurationException extends RuntimeException {
    
    /**
     * Creates a ConfigurationException with a message.
     *
     * @param message descriptive error message
     */
    public ConfigurationException(String message) {
        super(message);
    }
    
    /**
     * Creates a ConfigurationException with a message and cause.
     *
     * @param message descriptive error message
     * @param cause the underlying cause
     */
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Configuration errors are never retryable as they indicate
     * programming errors that must be fixed in the code.
     *
     * @return false always
     */
    public boolean isRetryable() {
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{message='").append(getMessage()).append("'");
        
        Throwable cause = getCause();
        if (cause != null) {
            sb.append(", cause=").append(cause.getClass().getSimpleName());
            if (cause.getMessage() != null) {
                sb.append("('").append(cause.getMessage()).append("')");
            }
        }
        
        sb.append("}");
        return sb.toString();
    }
}
