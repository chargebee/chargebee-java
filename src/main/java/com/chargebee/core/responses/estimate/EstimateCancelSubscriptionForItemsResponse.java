package com.chargebee.core.responses.estimate;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EstimateCancelSubscriptionForItems operation. Contains the response
 * data from the API.
 */
public final class EstimateCancelSubscriptionForItemsResponse {

  private final Estimate estimate;

  private EstimateCancelSubscriptionForItemsResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateCancelSubscriptionForItemsResponse object. */
  public static EstimateCancelSubscriptionForItemsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EstimateCancelSubscriptionForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateCancelSubscriptionForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateCancelSubscriptionForItemsResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimateCancelSubscriptionForItemsResponse build() {
      return new EstimateCancelSubscriptionForItemsResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
