package com.chargebee.v4.models.estimate.responses;

import com.chargebee.v4.models.estimate.Estimate;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EstimatePaymentSchedules operation. Contains the response data from
 * the API.
 */
public final class EstimatePaymentSchedulesResponse extends BaseResponse {
  private final Estimate estimate;

  private EstimatePaymentSchedulesResponse(Builder builder) {
    super(builder.httpResponse);

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimatePaymentSchedulesResponse object. */
  public static EstimatePaymentSchedulesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into EstimatePaymentSchedulesResponse object with HTTP response. */
  public static EstimatePaymentSchedulesResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EstimatePaymentSchedulesResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimatePaymentSchedulesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimatePaymentSchedulesResponse. */
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

    public EstimatePaymentSchedulesResponse build() {
      return new EstimatePaymentSchedulesResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
