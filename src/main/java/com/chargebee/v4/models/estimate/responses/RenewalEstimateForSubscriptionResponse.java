package com.chargebee.v4.models.estimate.responses;

import com.chargebee.v4.models.estimate.Estimate;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RenewalEstimateForSubscription operation. Contains the response
 * data from a single resource get operation.
 */
public final class RenewalEstimateForSubscriptionResponse extends BaseResponse {
  private final Estimate estimate;

  private RenewalEstimateForSubscriptionResponse(Estimate estimate, Response httpResponse) {
    super(httpResponse);

    this.estimate = estimate;
  }

  /** Parse JSON response into RenewalEstimateForSubscriptionResponse object. */
  public static RenewalEstimateForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RenewalEstimateForSubscriptionResponse object with HTTP response. */
  public static RenewalEstimateForSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {

      Estimate estimate = Estimate.fromJson(JsonUtil.getObject(json, "estimate"));

      return new RenewalEstimateForSubscriptionResponse(estimate, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse RenewalEstimateForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
