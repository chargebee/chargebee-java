package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.subscription.Subscription;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionRemoveCoupons operation. Contains the response data
 * from the API.
 */
public final class SubscriptionRemoveCouponsResponse {

  private final Subscription subscription;

  private final Customer customer;

  private final Card card;

  private final Response httpResponse;

  private SubscriptionRemoveCouponsResponse(Builder builder) {

    this.subscription = builder.subscription;

    this.customer = builder.customer;

    this.card = builder.card;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into SubscriptionRemoveCouponsResponse object. */
  public static SubscriptionRemoveCouponsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into SubscriptionRemoveCouponsResponse object with HTTP response. */
  public static SubscriptionRemoveCouponsResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        builder.subscription(Subscription.fromJson(__subscriptionJson));
      }

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      String __cardJson = JsonUtil.getObject(json, "card");
      if (__cardJson != null) {
        builder.card(Card.fromJson(__cardJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SubscriptionRemoveCouponsResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionRemoveCouponsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionRemoveCouponsResponse. */
  public static class Builder {

    private Subscription subscription;

    private Customer customer;

    private Card card;

    private Response httpResponse;

    private Builder() {}

    public Builder subscription(Subscription subscription) {
      this.subscription = subscription;
      return this;
    }

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder card(Card card) {
      this.card = card;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public SubscriptionRemoveCouponsResponse build() {
      return new SubscriptionRemoveCouponsResponse(this);
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
