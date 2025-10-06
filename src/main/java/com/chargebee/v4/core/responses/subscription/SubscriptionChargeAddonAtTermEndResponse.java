package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionChargeAddonAtTermEnd operation. Contains the response
 * data from the API.
 */
public final class SubscriptionChargeAddonAtTermEndResponse {

  private final Estimate estimate;

  private SubscriptionChargeAddonAtTermEndResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into SubscriptionChargeAddonAtTermEndResponse object. */
  public static SubscriptionChargeAddonAtTermEndResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionChargeAddonAtTermEndResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionChargeAddonAtTermEndResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionChargeAddonAtTermEndResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public SubscriptionChargeAddonAtTermEndResponse build() {
      return new SubscriptionChargeAddonAtTermEndResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
