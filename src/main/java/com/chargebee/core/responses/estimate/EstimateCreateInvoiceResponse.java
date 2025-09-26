package com.chargebee.core.responses.estimate;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EstimateCreateInvoice operation. Contains the response data from
 * the API.
 */
public final class EstimateCreateInvoiceResponse {

  private final Estimate estimate;

  private EstimateCreateInvoiceResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateCreateInvoiceResponse object. */
  public static EstimateCreateInvoiceResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EstimateCreateInvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateCreateInvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateCreateInvoiceResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimateCreateInvoiceResponse build() {
      return new EstimateCreateInvoiceResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
