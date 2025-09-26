package com.chargebee.core.responses.estimate;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EstimateChangeTermEnd operation. Contains the response data from
 * the API.
 */
public final class EstimateChangeTermEndResponse {

  private final Estimate estimate;

  private EstimateChangeTermEndResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateChangeTermEndResponse object. */
  public static EstimateChangeTermEndResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EstimateChangeTermEndResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateChangeTermEndResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateChangeTermEndResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimateChangeTermEndResponse build() {
      return new EstimateChangeTermEndResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
