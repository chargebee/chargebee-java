package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionAddChargeAtTermEnd operation. Contains the response
 * data from the API.
 */
public final class SubscriptionAddChargeAtTermEndResponse {

  private final Estimate estimate;

  private SubscriptionAddChargeAtTermEndResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into SubscriptionAddChargeAtTermEndResponse object. */
  public static SubscriptionAddChargeAtTermEndResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionAddChargeAtTermEndResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionAddChargeAtTermEndResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionAddChargeAtTermEndResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public SubscriptionAddChargeAtTermEndResponse build() {
      return new SubscriptionAddChargeAtTermEndResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
