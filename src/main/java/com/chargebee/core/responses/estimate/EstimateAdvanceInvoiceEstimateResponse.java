package com.chargebee.core.responses.estimate;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EstimateAdvanceInvoiceEstimate operation. Contains the response
 * data from the API.
 */
public final class EstimateAdvanceInvoiceEstimateResponse {

  private final Estimate estimate;

  private EstimateAdvanceInvoiceEstimateResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateAdvanceInvoiceEstimateResponse object. */
  public static EstimateAdvanceInvoiceEstimateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EstimateAdvanceInvoiceEstimateResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateAdvanceInvoiceEstimateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateAdvanceInvoiceEstimateResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimateAdvanceInvoiceEstimateResponse build() {
      return new EstimateAdvanceInvoiceEstimateResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
