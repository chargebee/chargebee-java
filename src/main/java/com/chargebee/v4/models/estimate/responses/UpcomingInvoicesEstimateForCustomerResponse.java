package com.chargebee.v4.models.estimate.responses;

import com.chargebee.v4.models.estimate.Estimate;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UpcomingInvoicesEstimateForCustomer operation. Contains the
 * response data from a single resource get operation.
 */
public final class UpcomingInvoicesEstimateForCustomerResponse extends BaseResponse {
  private final Estimate estimate;

  private UpcomingInvoicesEstimateForCustomerResponse(Estimate estimate, Response httpResponse) {
    super(httpResponse);

    this.estimate = estimate;
  }

  /** Parse JSON response into UpcomingInvoicesEstimateForCustomerResponse object. */
  public static UpcomingInvoicesEstimateForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into UpcomingInvoicesEstimateForCustomerResponse object with HTTP response.
   */
  public static UpcomingInvoicesEstimateForCustomerResponse fromJson(
      String json, Response httpResponse) {
    try {

      Estimate estimate = Estimate.fromJson(JsonUtil.getObject(json, "estimate"));

      return new UpcomingInvoicesEstimateForCustomerResponse(estimate, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UpcomingInvoicesEstimateForCustomerResponse from JSON", e);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
