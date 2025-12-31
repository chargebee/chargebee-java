package com.chargebee.v4.models.estimate.responses;

import com.chargebee.v4.models.estimate.Estimate;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EstimateGiftSubscription operation. Contains the response data from
 * the API.
 */
public final class EstimateGiftSubscriptionResponse extends BaseResponse {
  private final Estimate estimate;

  private EstimateGiftSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateGiftSubscriptionResponse object. */
  public static EstimateGiftSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into EstimateGiftSubscriptionResponse object with HTTP response. */
  public static EstimateGiftSubscriptionResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EstimateGiftSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateGiftSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateGiftSubscriptionResponse. */
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

    public EstimateGiftSubscriptionResponse build() {
      return new EstimateGiftSubscriptionResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }

  @Override
  public String toString() {
    return "EstimateGiftSubscriptionResponse{" + "estimate=" + estimate + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EstimateGiftSubscriptionResponse that = (EstimateGiftSubscriptionResponse) o;
    return java.util.Objects.equals(estimate, that.estimate);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(estimate);
  }
}
