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

  private UpcomingInvoicesEstimateForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.estimate = builder.estimate;
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
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse UpcomingInvoicesEstimateForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for UpcomingInvoicesEstimateForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UpcomingInvoicesEstimateForCustomerResponse. */
  public static class Builder {

    private Estimate estimate;

    private Response httpResponse;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public UpcomingInvoicesEstimateForCustomerResponse build() {
      return new UpcomingInvoicesEstimateForCustomerResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
