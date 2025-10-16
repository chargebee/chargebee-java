package com.chargebee.v4.core.responses.estimate;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EstimateUpcomingInvoicesEstimate operation. Contains the response
 * data from a single resource get operation.
 */
public final class EstimateUpcomingInvoicesEstimateResponse extends BaseResponse {
  private final Estimate estimate;

  private EstimateUpcomingInvoicesEstimateResponse(Estimate estimate, Response httpResponse) {
    super(httpResponse);

    this.estimate = estimate;
  }

  /** Parse JSON response into EstimateUpcomingInvoicesEstimateResponse object. */
  public static EstimateUpcomingInvoicesEstimateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into EstimateUpcomingInvoicesEstimateResponse object with HTTP response.
   */
  public static EstimateUpcomingInvoicesEstimateResponse fromJson(
      String json, Response httpResponse) {
    try {

      Estimate estimate = Estimate.fromJson(JsonUtil.getObject(json, "estimate"));

      return new EstimateUpcomingInvoicesEstimateResponse(estimate, httpResponse);
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
