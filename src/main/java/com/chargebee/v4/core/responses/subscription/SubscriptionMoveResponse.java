package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.subscription.Subscription;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionMove operation. Contains the response data from the
 * API.
 */
public final class SubscriptionMoveResponse extends BaseResponse {
  private final Subscription subscription;

  private SubscriptionMoveResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscription = builder.subscription;
  }

  /** Parse JSON response into SubscriptionMoveResponse object. */
  public static SubscriptionMoveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into SubscriptionMoveResponse object with HTTP response. */
  public static SubscriptionMoveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        builder.subscription(Subscription.fromJson(__subscriptionJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder subscription(Subscription subscription) {
      this.subscription = subscription;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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
