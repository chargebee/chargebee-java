/*
 * Copyright 2025 Chargebee Inc.
 */

package com.chargebee.v4.webhook;

import com.chargebee.v4.models.event.Event;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Central handler for processing Chargebee webhook events.
 * 
 * <p>This class provides a fluent API for registering event callbacks and processing 
 * incoming webhook notifications. It handles optional Basic Auth verification, event 
 * parsing, and routing to appropriate handlers.</p>
 * 
 * <h2>Quick Start:</h2>
 * <pre>
 * // Create the handler
 * WebhookEventHandler handler = WebhookEventHandler.create();
 * 
 * // Register callbacks for specific event types (class is auto-resolved via type witness)
 * handler.&lt;CustomerCreatedEvent&gt;on(Event.EventType.CUSTOMER_CREATED, event -> {
 *     Customer customer = event.getContent().getCustomer();
 *     System.out.println("Customer created: " + customer.getEmail());
 *     return WebhookResult.success(event.getEventType(), event.getId());
 * });
 * 
 * handler.&lt;SubscriptionCancelledEvent&gt;on(Event.EventType.SUBSCRIPTION_CANCELLED, event -> {
 *     Subscription sub = event.getContent().getSubscription();
 *     System.out.println("Subscription cancelled: " + sub.getId());
 *     return WebhookResult.success(event.getEventType(), event.getId());
 * });
 * 
 * // Process incoming webhook (in your controller)
 * WebhookResult result = handler.handleWebhook(requestBody);
 * </pre>
 * 
 * <h2>With Basic Auth (Optional):</h2>
 * <pre>
 * WebhookEventHandler handler = WebhookEventHandler.builder()
 *     .basicAuth("username", "password")
 *     .fallbackCallback((event, eventType, rawPayload) -> {
 *         System.out.println("Unhandled event: " + eventType);
 *         return WebhookResult.unhandled(eventType, event.getId());
 *     })
 *     .build();
 * 
 * // Process with auth verification
 * WebhookResult result = handler.handleWebhook(requestBody, authorizationHeader);
 * </pre>
 * 
 * <h2>Common Event Types:</h2>
 * <ul>
 *   <li>customer_created, customer_changed, customer_deleted</li>
 *   <li>subscription_created, subscription_activated, subscription_cancelled, subscription_renewed</li>
 *   <li>invoice_generated, invoice_updated, invoice_deleted</li>
 *   <li>payment_succeeded, payment_failed, payment_refunded</li>
 *   <li>card_added, card_updated, card_expired</li>
 * </ul>
 * 
 * @see WebhookEventCallback
 * @see UnhandledEventCallback
 * @see WebhookResult
 */
public class WebhookEventHandler {

    private final String webhookUsername;
    private final String webhookPassword;
    private final WebhookVerifier webhookVerifier;
    private final UnhandledEventCallback fallbackCallback;
    private final Map<String, EventCallbackWrapper<?>> callbacks;

    private WebhookEventHandler(Builder builder) {
        this.webhookUsername = builder.webhookUsername;
        this.webhookPassword = builder.webhookPassword;
        this.webhookVerifier = builder.webhookVerifier;
        this.fallbackCallback = builder.fallbackCallback;
        this.callbacks = new ConcurrentHashMap<>();
    }

    /**
     * Creates a simple WebhookEventHandler without authentication.
     *
     * @return A new WebhookEventHandler instance
     */
    public static WebhookEventHandler create() {
        return builder().build();
    }

    /**
     * Creates a new builder for WebhookEventHandler.
     *
     * @return A new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    private static final String EVENT_CLASS_PACKAGE = "com.chargebee.v4.models.event";

    /**
     * Registers a callback for a specific event type.
     * The event class is automatically derived from the event type.
     *
     * <p>Usage with type witness:</p>
     * <pre>
     * handler.&lt;CustomerCreatedEvent&gt;on(Event.EventType.CUSTOMER_CREATED, event -> {
     *     Customer customer = event.getContent().getCustomer();
     *     return WebhookResult.success(event.getEventType(), event.getId());
     * });
     * </pre>
     *
     * @param eventType The event type enum value (e.g., Event.EventType.CUSTOMER_CREATED)
     * @param callback The callback to invoke when this event type is received
     * @param <T> The type of the event class
     * @return This handler for method chaining
     * @throws WebhookException if the event class cannot be found
     */
    @SuppressWarnings("unchecked")
    public <T> WebhookEventHandler on(Event.EventType eventType, WebhookEventCallback<T> callback) {
        String eventTypeValue = eventType.getValue();
        if (eventTypeValue == null) {
            throw new WebhookException("Event type value cannot be null");
        }
        Class<T> eventClass = (Class<T>) resolveEventClass(eventTypeValue);
        callbacks.put(eventTypeValue, new EventCallbackWrapper<>(eventClass, callback));
        return this;
    }

    /**
     * Resolves the event class from the event type string.
     * Converts snake_case event type to PascalCase class name.
     * e.g., "customer_moved_in" -> CustomerMovedInEvent
     *
     * @param eventType The event type string (e.g., "customer_moved_in")
     * @return The resolved event class
     * @throws WebhookException if the class cannot be found
     */
    private Class<?> resolveEventClass(String eventType) {
        String className = Arrays.stream(eventType.split("_"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining()) + "Event";
        
        String fullClassName = EVENT_CLASS_PACKAGE + "." + className;
        
        try {
            return Class.forName(fullClassName);
        } catch (ClassNotFoundException e) {
            throw new WebhookException("Event class not found: " + fullClassName + 
                    ". Ensure the event type '" + eventType + "' is supported.", e);
        }
    }

    /**
     * Processes an incoming webhook request without authentication.
     *
     * @param payload The raw request body (JSON)
     * @return WebhookResult from the callback
     * @throws WebhookException if processing fails
     */
    public WebhookResult handleWebhook(String payload) {
        return handleWebhook(payload, null);
    }

    /**
     * Processes an incoming webhook request with optional authentication.
     * 
     * <p>This method:</p>
     * <ol>
     *   <li>Verifies Basic Auth credentials (if configured)</li>
     *   <li>Parses the payload to determine event type</li>
     *   <li>Parses the payload to the typed event class</li>
     *   <li>Routes to the appropriate registered callback or fallback</li>
     *   <li>Returns the WebhookResult from the callback</li>
     * </ol>
     *
     * @param payload The raw request body (JSON)
     * @param authorizationHeader The Authorization header value (can be null)
     * @return WebhookResult from the callback
     * @throws WebhookException if processing fails
     */
    public WebhookResult handleWebhook(String payload, String authorizationHeader) {
        // Verify Basic Auth if configured
        if (webhookUsername != null && !webhookUsername.isEmpty() && 
            webhookPassword != null && !webhookPassword.isEmpty()) {
            
            if (webhookVerifier == null) {
                throw new WebhookException("Basic Auth is configured but verifier is not set");
            }
            
            if (!webhookVerifier.verifyBasicAuth(authorizationHeader, webhookUsername, webhookPassword)) {
                return WebhookResult.failure("Invalid webhook credentials");
            }
        }

        // Parse the event to get event type
        Event event;
        try {
            event = Event.fromJson(payload);
        } catch (Exception e) {
            throw new WebhookException("Failed to parse webhook payload", e);
        }

        if (event.getEventType() == null) {
            return WebhookResult.failure("Event type is missing");
        }

        String eventType = event.getEventType().getValue();
        
        if (eventType == null) {
            return WebhookResult.failure("Event type value is null");
        }

        // Route to appropriate callback
        EventCallbackWrapper<?> wrapper = callbacks.get(eventType);
        
        try {
            if (wrapper != null) {
                return invokeCallback(wrapper, payload);
            } else if (fallbackCallback != null) {
                return fallbackCallback.handle(event, eventType, payload);
            } else {
                return WebhookResult.unhandled(eventType, event.getId());
            }
        } catch (Exception e) {
            throw new WebhookException("Failed to process webhook event: " + eventType, e);
        }
    }

    /**
     * Checks if a callback is registered for the given event type.
     *
     * @param eventType The event type to check
     * @return true if a callback is registered
     */
    public boolean hasCallback(String eventType) {
        return callbacks.containsKey(eventType);
    }

    /**
     * Returns the number of registered callbacks.
     *
     * @return The number of registered callbacks
     */
    public int getRegisteredCallbackCount() {
        return callbacks.size();
    }

    @SuppressWarnings("unchecked")
    private <T> WebhookResult invokeCallback(EventCallbackWrapper<T> wrapper, String payload) throws Exception {
        // Parse to typed event using the event class's fromJson method
        Method fromJsonMethod = wrapper.eventClass.getMethod("fromJson", String.class);
        T typedEvent = (T) fromJsonMethod.invoke(null, payload);
        return wrapper.callback.handle(typedEvent);
    }

    private static class EventCallbackWrapper<T> {
        final Class<T> eventClass;
        final WebhookEventCallback<T> callback;

        EventCallbackWrapper(Class<T> eventClass, WebhookEventCallback<T> callback) {
            this.eventClass = eventClass;
            this.callback = callback;
        }
    }

    /**
     * Builder for WebhookEventHandler.
     */
    public static class Builder {
        private String webhookUsername;
        private String webhookPassword;
        private WebhookVerifier webhookVerifier;
        private UnhandledEventCallback fallbackCallback;

        /**
         * Configures Basic Auth credentials for webhook verification (optional).
         *
         * @param username The username configured in Chargebee webhook settings
         * @param password The password configured in Chargebee webhook settings
         * @return This builder
         */
        public Builder basicAuth(String username, String password) {
            this.webhookUsername = username;
            this.webhookPassword = password;
            this.webhookVerifier = new WebhookVerifier();
            return this;
        }

        /**
         * Sets a custom webhook verifier instance.
         *
         * @param webhookVerifier The WebhookVerifier to use
         * @return This builder
         */
        public Builder webhookVerifier(WebhookVerifier webhookVerifier) {
            this.webhookVerifier = webhookVerifier;
            return this;
        }

        /**
         * Sets the fallback callback for unhandled events.
         *
         * @param fallbackCallback The callback for unregistered event types
         * @return This builder
         */
        public Builder fallbackCallback(UnhandledEventCallback fallbackCallback) {
            this.fallbackCallback = fallbackCallback;
            return this;
        }

        /**
         * Builds the WebhookEventHandler.
         *
         * @return The configured WebhookEventHandler
         */
        public WebhookEventHandler build() {
            return new WebhookEventHandler(this);
        }
    }

    /**
     * Exception thrown when webhook processing fails.
     */
    public static class WebhookException extends RuntimeException {
        public WebhookException(String message) {
            super(message);
        }

        public WebhookException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
