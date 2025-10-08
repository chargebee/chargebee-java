package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.subscription.Subscription;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionRetrieveWithScheduledChanges operation. Contains the
 * response data from a single resource get operation.
 */
public final class SubscriptionRetrieveWithScheduledChangesResponse {

  private final Subscription subscription;

  private final Customer customer;

  private final Card card;

  private final Response httpResponse;

  private SubscriptionRetrieveWithScheduledChangesResponse(
      Subscription subscription, Customer customer, Card card, Response httpResponse) {

    this.subscription = subscription;

    this.customer = customer;

    this.card = card;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into SubscriptionRetrieveWithScheduledChangesResponse object. */
  public static SubscriptionRetrieveWithScheduledChangesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionRetrieveWithScheduledChangesResponse object with HTTP
   * response.
   */
  public static SubscriptionRetrieveWithScheduledChangesResponse fromJson(
      String json, Response httpResponse) {
    try {

      Subscription subscription = Subscription.fromJson(JsonUtil.getObject(json, "subscription"));

      Customer customer = Customer.fromJson(JsonUtil.getObject(json, "customer"));

      Card card = Card.fromJson(JsonUtil.getObject(json, "card"));

      return new SubscriptionRetrieveWithScheduledChangesResponse(
          subscription, customer, card, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionRetrieveWithScheduledChangesResponse from JSON", e);
    }
  }

  /** Get the subscription from the response. */
  public Subscription getSubscription() {
    return subscription;
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  /** Get the card from the response. */
  public Card getCard() {
    return card;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
