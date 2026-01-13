/*
 * Copyright 2025 Chargebee Inc.
 */

package com.chargebee.v4.webhook;

/**
 * Functional interface for handling Chargebee webhook events.
 * 
 * <p>Implement this interface to create handlers for specific event types.
 * The callback receives the fully typed event object from the SDK.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * handler.on(Event.EventType.CUSTOMER_CREATED, CustomerCreatedEvent.class, (typedEvent) -> {
 *     Customer customer = typedEvent.getContent().getCustomer();
 *     System.out.println("New customer: " + customer.getId());
 *     return WebhookResult.success(typedEvent.getEventType(), typedEvent.getId());
 * });
 * </pre>
 *
 * @param <T> The typed event class (e.g., CustomerCreatedEvent, SubscriptionCreatedEvent)
 */
@FunctionalInterface
public interface WebhookEventCallback<T> {

    /**
     * Handles a webhook event and returns the processing result.
     *
     * @param typedEvent The typed event object with strongly-typed content
     * @return WebhookResult indicating the outcome of processing
     * @throws Exception if event processing fails
     */
    WebhookResult handle(T typedEvent) throws Exception;
}
