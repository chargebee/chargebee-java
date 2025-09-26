package com.chargebee.core.responses.estimate;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EstimateUpcomingInvoicesEstimate operation. Contains the response
 * data from a single resource get operation.
 */
public final class EstimateUpcomingInvoicesEstimateResponse {

  private final Estimate estimate;

  private EstimateUpcomingInvoicesEstimateResponse(Estimate estimate) {

    this.estimate = estimate;
  }

  /** Parse JSON response into EstimateUpcomingInvoicesEstimateResponse object. */
  public static EstimateUpcomingInvoicesEstimateResponse fromJson(String json) {
    try {

      Estimate estimate = Estimate.fromJson(JsonUtil.getObject(json, "estimate"));

      return new EstimateUpcomingInvoicesEstimateResponse(estimate);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EstimateUpcomingInvoicesEstimateResponse from JSON", e);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
