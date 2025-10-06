package com.chargebee.v4.core.responses.estimate;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for EstimateUpdateSubscription operation. Contains the response data
 * from the API.
 */
public final class EstimateUpdateSubscriptionResponse {

  private final Estimate estimate;

  private EstimateUpdateSubscriptionResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateUpdateSubscriptionResponse object. */
  public static EstimateUpdateSubscriptionResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EstimateUpdateSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateUpdateSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateUpdateSubscriptionResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimateUpdateSubscriptionResponse build() {
      return new EstimateUpdateSubscriptionResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
