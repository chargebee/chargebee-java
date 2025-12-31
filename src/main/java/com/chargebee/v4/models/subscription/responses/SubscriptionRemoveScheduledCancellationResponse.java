package com.chargebee.v4.models.subscription.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.models.card.Card;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionRemoveScheduledCancellation operation. Contains the
 * response data from the API.
 */
public final class SubscriptionRemoveScheduledCancellationResponse extends BaseResponse {
  private final Subscription subscription;

  private final Customer customer;

  private final Card card;

  private SubscriptionRemoveScheduledCancellationResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscription = builder.subscription;

    this.customer = builder.customer;

    this.card = builder.card;
  }

  /** Parse JSON response into SubscriptionRemoveScheduledCancellationResponse object. */
  public static SubscriptionRemoveScheduledCancellationResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionRemoveScheduledCancellationResponse object with HTTP
   * response.
   */
  public static SubscriptionRemoveScheduledCancellationResponse fromJson(
      String json, Response httpResponse) {
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
      throw new RuntimeException(
          "Failed to parse SubscriptionRemoveScheduledCancellationResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionRemoveScheduledCancellationResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionRemoveScheduledCancellationResponse. */
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

    public SubscriptionRemoveScheduledCancellationResponse build() {
      return new SubscriptionRemoveScheduledCancellationResponse(this);
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

  @Override
  public String toString() {
    return "SubscriptionRemoveScheduledCancellationResponse{"
        + "subscription="
        + subscription
        + ", customer="
        + customer
        + ", card="
        + card
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SubscriptionRemoveScheduledCancellationResponse that =
        (SubscriptionRemoveScheduledCancellationResponse) o;
    return java.util.Objects.equals(subscription, that.subscription)
        && java.util.Objects.equals(customer, that.customer)
        && java.util.Objects.equals(card, that.card);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(subscription, customer, card);
  }
}
