package com.chargebee.core.responses.estimate;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EstimateGiftSubscriptionForItems operation. Contains the response
 * data from the API.
 */
public final class EstimateGiftSubscriptionForItemsResponse {

  private final Estimate estimate;

  private EstimateGiftSubscriptionForItemsResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateGiftSubscriptionForItemsResponse object. */
  public static EstimateGiftSubscriptionForItemsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EstimateGiftSubscriptionForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateGiftSubscriptionForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateGiftSubscriptionForItemsResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimateGiftSubscriptionForItemsResponse build() {
      return new EstimateGiftSubscriptionForItemsResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
