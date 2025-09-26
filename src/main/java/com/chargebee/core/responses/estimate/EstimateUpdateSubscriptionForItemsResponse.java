package com.chargebee.core.responses.estimate;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EstimateUpdateSubscriptionForItems operation. Contains the response
 * data from the API.
 */
public final class EstimateUpdateSubscriptionForItemsResponse {

  private final Estimate estimate;

  private EstimateUpdateSubscriptionForItemsResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateUpdateSubscriptionForItemsResponse object. */
  public static EstimateUpdateSubscriptionForItemsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EstimateUpdateSubscriptionForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateUpdateSubscriptionForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateUpdateSubscriptionForItemsResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimateUpdateSubscriptionForItemsResponse build() {
      return new EstimateUpdateSubscriptionForItemsResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
