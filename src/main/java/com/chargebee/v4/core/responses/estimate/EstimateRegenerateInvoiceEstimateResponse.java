package com.chargebee.v4.core.responses.estimate;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EstimateRegenerateInvoiceEstimate operation. Contains the response
 * data from the API.
 */
public final class EstimateRegenerateInvoiceEstimateResponse extends BaseResponse {
  private final Estimate estimate;

  private EstimateRegenerateInvoiceEstimateResponse(Builder builder) {
    super(builder.httpResponse);

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateRegenerateInvoiceEstimateResponse object. */
  public static EstimateRegenerateInvoiceEstimateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into EstimateRegenerateInvoiceEstimateResponse object with HTTP response.
   */
  public static EstimateRegenerateInvoiceEstimateResponse fromJson(
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
          "Failed to parse EstimateRegenerateInvoiceEstimateResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateRegenerateInvoiceEstimateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateRegenerateInvoiceEstimateResponse. */
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

    public EstimateRegenerateInvoiceEstimateResponse build() {
      return new EstimateRegenerateInvoiceEstimateResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
