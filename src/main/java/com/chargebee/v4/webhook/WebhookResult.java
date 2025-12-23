/*
 * Copyright 2025 Chargebee Inc.
 */

package com.chargebee.v4.webhook;

/**
 * Result of webhook processing.
 * 
 * <p>This class represents the outcome of processing a Chargebee webhook event.
 * Use the static factory methods to create appropriate results:</p>
 * <ul>
 *   <li>{@link #success(String, String)} - Event processed successfully</li>
 *   <li>{@link #unhandled(String, String)} - Event received but no handler registered</li>
 *   <li>{@link #failure(String)} - Event processing failed</li>
 * </ul>
 */
public class WebhookResult {
    
    private final boolean success;
    private final boolean handled;
    private final String eventType;
    private final String eventId;
    private final String errorMessage;

    private WebhookResult(boolean success, boolean handled, String eventType, String eventId, String errorMessage) {
        this.success = success;
        this.handled = handled;
        this.eventType = eventType;
        this.eventId = eventId;
        this.errorMessage = errorMessage;
    }

    /**
     * Creates a success result indicating the event was processed successfully.
     *
     * @param eventType The event type that was processed
     * @param eventId The event ID
     * @return A success WebhookResult
     */
    public static WebhookResult success(String eventType, String eventId) {
        return new WebhookResult(true, true, eventType, eventId, null);
    }

    /**
     * Creates an unhandled result indicating the event was received but no handler was registered.
     *
     * @param eventType The event type
     * @param eventId The event ID
     * @return An unhandled WebhookResult
     */
    public static WebhookResult unhandled(String eventType, String eventId) {
        return new WebhookResult(true, false, eventType, eventId, null);
    }

    /**
     * Creates a failure result indicating the event processing failed.
     *
     * @param errorMessage Description of the failure
     * @return A failure WebhookResult
     */
    public static WebhookResult failure(String errorMessage) {
        return new WebhookResult(false, false, null, null, errorMessage);
    }

    /**
     * @return true if the webhook was processed without errors
     */
    public boolean isSuccess() { 
        return success; 
    }
    
    /**
     * @return true if a handler was found and executed for this event
     */
    public boolean isHandled() { 
        return handled; 
    }
    
    /**
     * @return The event type that was processed, or null on failure
     */
    public String getEventType() { 
        return eventType; 
    }
    
    /**
     * @return The event ID, or null on failure
     */
    public String getEventId() { 
        return eventId; 
    }
    
    /**
     * @return Error message if processing failed, null otherwise
     */
    public String getErrorMessage() { 
        return errorMessage; 
    }

    @Override
    public String toString() {
        return "WebhookResult{" +
                "success=" + success +
                ", handled=" + handled +
                ", eventType='" + eventType + '\'' +
                ", eventId='" + eventId + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

