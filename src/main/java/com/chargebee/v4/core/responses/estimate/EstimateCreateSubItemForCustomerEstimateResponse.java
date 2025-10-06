package com.chargebee.v4.core.responses.estimate;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for EstimateCreateSubItemForCustomerEstimate operation. Contains the
 * response data from the API.
 */
public final class EstimateCreateSubItemForCustomerEstimateResponse {

  private final Estimate estimate;

  private EstimateCreateSubItemForCustomerEstimateResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateCreateSubItemForCustomerEstimateResponse object. */
  public static EstimateCreateSubItemForCustomerEstimateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EstimateCreateSubItemForCustomerEstimateResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateCreateSubItemForCustomerEstimateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateCreateSubItemForCustomerEstimateResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimateCreateSubItemForCustomerEstimateResponse build() {
      return new EstimateCreateSubItemForCustomerEstimateResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
