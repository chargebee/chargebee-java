package com.chargebee.core.responses.estimate;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EstimateRegenerateInvoiceEstimate operation. Contains the response
 * data from the API.
 */
public final class EstimateRegenerateInvoiceEstimateResponse {

  private final Estimate estimate;

  private EstimateRegenerateInvoiceEstimateResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateRegenerateInvoiceEstimateResponse object. */
  public static EstimateRegenerateInvoiceEstimateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EstimateRegenerateInvoiceEstimateResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateRegenerateInvoiceEstimateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateRegenerateInvoiceEstimateResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimateRegenerateInvoiceEstimateResponse build() {
      return new EstimateRegenerateInvoiceEstimateResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
