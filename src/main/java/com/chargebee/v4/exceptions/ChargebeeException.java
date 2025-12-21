package com.chargebee.v4.exceptions;

/**
 * The root exception class for all errors originating from the Chargebee SDK.
 * 
 * <p>{@code ChargebeeException} serves as the unified base for the SDK's exception hierarchy,
 * enabling developers to catch all Chargebee-related errors with a single exception type
 * while still allowing fine-grained handling of specific error categories.</p>
 * 
 * <h2>Exception Hierarchy</h2>
 * <pre>
 * ChargebeeException
 * ├── {@link ConfigurationException}     — Invalid SDK configuration (missing API key, malformed URL)
 * └── {@link TransportException}         — Runtime communication errors
 *     ├── {@link NetworkException}       — Connection failures, DNS resolution errors
 *     ├── {@link TimeoutException}       — Connect or read timeout exceeded
 *     └── {@link HttpException}          — HTTP-level errors (4xx, 5xx responses)
 *         ├── {@link ClientErrorException}   — Client errors (400-499)
 *         ├── {@link ServerErrorException}   — Server errors (500-599)
 *         └── {@link APIException}           — Chargebee API errors with structured details
 * </pre>
 * 
 * <h2>Usage Patterns</h2>
 * 
 * <h3>Catch-all handling</h3>
 * <pre>{@code
 * try {
 *     client.subscriptions().create(params);
 * } catch (ChargebeeException e) {
 *     log.error("Chargebee operation failed: {}", e.getMessage());
 *     if (e.isRetryable()) {
 *         // Schedule retry
 *     }
 * }
 * }</pre>
 * 
 * <h3>Granular error handling</h3>
 * <pre>{@code
 * try {
 *     client.subscriptions().create(params);
 * } catch (InvalidRequestException e) {
 *     // Handle validation errors - check e.getParams() for invalid fields
 * } catch (PaymentException e) {
 *     // Handle payment failures - check e.getErrorCauseId() for gateway details
 * } catch (NetworkException | TimeoutException e) {
 *     // Handle transient failures - safe to retry
 * } catch (ChargebeeException e) {
 *     // Fallback for unexpected SDK errors
 * }
 * }</pre>
 * 
 * @see TransportException
 * @see ConfigurationException
 * @see APIException
 */
public class ChargebeeException extends RuntimeException {
    
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message describing the error condition
     */
    public ChargebeeException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message describing the error condition
     * @param cause the underlying exception that triggered this error
     */
    public ChargebeeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Indicates whether retrying the failed operation might succeed.
     * 
     * <p>Subclasses override this method to provide accurate retry guidance:</p>
     * <ul>
     *   <li>{@link NetworkException} — returns {@code true} (transient connectivity issues)</li>
     *   <li>{@link TimeoutException} — returns {@code true} (temporary overload)</li>
     *   <li>{@link ServerErrorException} — returns {@code true} for 5xx except 501</li>
     *   <li>{@link ClientErrorException} — returns {@code true} only for 429 (rate limited)</li>
     *   <li>{@link ConfigurationException} — returns {@code false} (requires code fix)</li>
     * </ul>
     *
     * @return {@code true} if the operation may succeed on retry; {@code false} otherwise
     */
    public boolean isRetryable() {
        return false;
    }
}

