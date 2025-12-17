package com.chargebee.v4.models.estimate.responses;

import com.chargebee.v4.models.estimate.Estimate;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreateSubscriptionEstimateForCustomer operation. Contains the
 * response data from a single resource get operation.
 */
public final class CreateSubscriptionEstimateForCustomerResponse extends BaseResponse {
  private final Estimate estimate;

  private CreateSubscriptionEstimateForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into CreateSubscriptionEstimateForCustomerResponse object. */
  public static CreateSubscriptionEstimateForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into CreateSubscriptionEstimateForCustomerResponse object with HTTP
   * response.
   */
  public static CreateSubscriptionEstimateForCustomerResponse fromJson(
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
          "Failed to parse CreateSubscriptionEstimateForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for CreateSubscriptionEstimateForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreateSubscriptionEstimateForCustomerResponse. */
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

    public CreateSubscriptionEstimateForCustomerResponse build() {
      return new CreateSubscriptionEstimateForCustomerResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
