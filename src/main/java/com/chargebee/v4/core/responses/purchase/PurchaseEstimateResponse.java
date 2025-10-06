package com.chargebee.v4.core.responses.purchase;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PurchaseEstimate operation. Contains the response data from the
 * API.
 */
public final class PurchaseEstimateResponse {

  private final Estimate estimate;

  private PurchaseEstimateResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into PurchaseEstimateResponse object. */
  public static PurchaseEstimateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PurchaseEstimateResponse from JSON", e);
    }
  }

  /** Create a new builder for PurchaseEstimateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PurchaseEstimateResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public PurchaseEstimateResponse build() {
      return new PurchaseEstimateResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
