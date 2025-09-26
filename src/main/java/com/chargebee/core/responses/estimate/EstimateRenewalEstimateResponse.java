package com.chargebee.core.responses.estimate;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EstimateRenewalEstimate operation. Contains the response data from
 * a single resource get operation.
 */
public final class EstimateRenewalEstimateResponse {

  private final Estimate estimate;

  private EstimateRenewalEstimateResponse(Estimate estimate) {

    this.estimate = estimate;
  }

  /** Parse JSON response into EstimateRenewalEstimateResponse object. */
  public static EstimateRenewalEstimateResponse fromJson(String json) {
    try {

      Estimate estimate = Estimate.fromJson(JsonUtil.getObject(json, "estimate"));

      return new EstimateRenewalEstimateResponse(estimate);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EstimateRenewalEstimateResponse from JSON", e);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
