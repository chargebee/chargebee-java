package com.chargebee.core.responses.unbilledCharge;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for UnbilledChargeInvoiceNowEstimate operation. Contains the response
 * data from the API.
 */
public final class UnbilledChargeInvoiceNowEstimateResponse {

  private final Estimate estimate;

  private UnbilledChargeInvoiceNowEstimateResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into UnbilledChargeInvoiceNowEstimateResponse object. */
  public static UnbilledChargeInvoiceNowEstimateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UnbilledChargeInvoiceNowEstimateResponse from JSON", e);
    }
  }

  /** Create a new builder for UnbilledChargeInvoiceNowEstimateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UnbilledChargeInvoiceNowEstimateResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public UnbilledChargeInvoiceNowEstimateResponse build() {
      return new UnbilledChargeInvoiceNowEstimateResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
