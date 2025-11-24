package com.chargebee.v4.models.subscription.responses;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for MoveForSubscription operation. Contains the response data from the
 * API.
 */
public final class MoveForSubscriptionResponse extends BaseResponse {
  private final Subscription subscription;

  private MoveForSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscription = builder.subscription;
  }

  /** Parse JSON response into MoveForSubscriptionResponse object. */
  public static MoveForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into MoveForSubscriptionResponse object with HTTP response. */
  public static MoveForSubscriptionResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        builder.subscription(Subscription.fromJson(__subscriptionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse MoveForSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for MoveForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for MoveForSubscriptionResponse. */
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

    public MoveForSubscriptionResponse build() {
      return new MoveForSubscriptionResponse(this);
    }
  }

  /** Get the subscription from the response. */
  public Subscription getSubscription() {
    return subscription;
  }
}
