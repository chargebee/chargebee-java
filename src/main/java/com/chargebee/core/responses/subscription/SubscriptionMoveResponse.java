package com.chargebee.core.responses.subscription;

import com.chargebee.core.models.subscription.Subscription;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionMove operation. Contains the response data from the
 * API.
 */
public final class SubscriptionMoveResponse {

  private final Subscription subscription;

  private SubscriptionMoveResponse(Builder builder) {

    this.subscription = builder.subscription;
  }

  /** Parse JSON response into SubscriptionMoveResponse object. */
  public static SubscriptionMoveResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        builder.subscription(Subscription.fromJson(__subscriptionJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SubscriptionMoveResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionMoveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionMoveResponse. */
  public static class Builder {

    private Subscription subscription;

    private Builder() {}

    public Builder subscription(Subscription subscription) {
      this.subscription = subscription;
      return this;
    }

    public SubscriptionMoveResponse build() {
      return new SubscriptionMoveResponse(this);
    }
  }

  /** Get the subscription from the response. */
  public Subscription getSubscription() {
    return subscription;
  }
}
