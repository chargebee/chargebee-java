package com.chargebee.v4.models.estimate.responses;

import com.chargebee.v4.models.estimate.Estimate;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AdvanceInvoiceEstimate operation. Contains the response data from
 * the API.
 */
public final class AdvanceInvoiceEstimateResponse extends BaseResponse {
  private final Estimate estimate;

  private AdvanceInvoiceEstimateResponse(Builder builder) {
    super(builder.httpResponse);

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into AdvanceInvoiceEstimateResponse object. */
  public static AdvanceInvoiceEstimateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AdvanceInvoiceEstimateResponse object with HTTP response. */
  public static AdvanceInvoiceEstimateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __estimateObj = JsonUtil.getJsonObject(jsonObj, "estimate");
      if (__estimateObj != null) {
        builder.estimate(Estimate.fromJson(__estimateObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AdvanceInvoiceEstimateResponse from JSON", e);
    }
  }

  /** Create a new builder for AdvanceInvoiceEstimateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AdvanceInvoiceEstimateResponse. */
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

    public AdvanceInvoiceEstimateResponse build() {
      return new AdvanceInvoiceEstimateResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }

  @Override
  public String toString() {
    return "AdvanceInvoiceEstimateResponse{" + "estimate=" + estimate + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AdvanceInvoiceEstimateResponse that = (AdvanceInvoiceEstimateResponse) o;
    return java.util.Objects.equals(estimate, that.estimate);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(estimate);
  }
}
