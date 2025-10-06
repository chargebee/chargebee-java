package com.chargebee.v4.core.responses.estimate;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for EstimateCreateSubItemEstimate operation. Contains the response data
 * from the API.
 */
public final class EstimateCreateSubItemEstimateResponse {

  private final Estimate estimate;

  private EstimateCreateSubItemEstimateResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateCreateSubItemEstimateResponse object. */
  public static EstimateCreateSubItemEstimateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EstimateCreateSubItemEstimateResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateCreateSubItemEstimateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateCreateSubItemEstimateResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimateCreateSubItemEstimateResponse build() {
      return new EstimateCreateSubItemEstimateResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
