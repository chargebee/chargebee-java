package com.chargebee.v4.core.responses.estimate;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for EstimateCreateSubForCustomerEstimate operation. Contains the
 * response data from a single resource get operation.
 */
public final class EstimateCreateSubForCustomerEstimateResponse {

  private final Estimate estimate;

  private EstimateCreateSubForCustomerEstimateResponse(Estimate estimate) {

    this.estimate = estimate;
  }

  /** Parse JSON response into EstimateCreateSubForCustomerEstimateResponse object. */
  public static EstimateCreateSubForCustomerEstimateResponse fromJson(String json) {
    try {

      Estimate estimate = Estimate.fromJson(JsonUtil.getObject(json, "estimate"));

      return new EstimateCreateSubForCustomerEstimateResponse(estimate);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EstimateCreateSubForCustomerEstimateResponse from JSON", e);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
