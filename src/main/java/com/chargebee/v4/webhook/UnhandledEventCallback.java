/*
 * Copyright 2025 Chargebee Inc.
 */

package com.chargebee.v4.webhook;

import com.chargebee.v4.models.event.Event;

/**
 * Functional interface for handling unregistered/unhandled Chargebee webhook events.
 * 
 * <p>This interface is invoked when no specific handler is registered for an event type.
 * This allows you to log, store, or process unknown/unhandled events.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * UnhandledEventCallback fallbackHandler = (event, eventType, rawPayload) -> {
 *     log.warn("Unhandled event: type={}, id={}", eventType, event.getId());
 *     return WebhookResult.unhandled(eventType, event.getId());
 * };
 * </pre>
 */
@FunctionalInterface
public interface UnhandledEventCallback {

    /**
     * Handles an unhandled/unregistered webhook event and returns the processing result.
     *
     * @param event The base Event object (with content as Map)
     * @param eventType The event type string that was not handled
     * @param rawPayload The raw JSON payload (for custom parsing if needed)
     * @return WebhookResult indicating the outcome of processing
     * @throws Exception if event processing fails
     */
    WebhookResult handle(Event event, String eventType, String rawPayload) throws Exception;
}
