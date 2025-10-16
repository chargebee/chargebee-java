package com.chargebee.v4.core.responses.estimate;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EstimateChangeTermEnd operation. Contains the response data from
 * the API.
 */
public final class EstimateChangeTermEndResponse extends BaseResponse {
  private final Estimate estimate;

  private EstimateChangeTermEndResponse(Builder builder) {
    super(builder.httpResponse);

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimateChangeTermEndResponse object. */
  public static EstimateChangeTermEndResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into EstimateChangeTermEndResponse object with HTTP response. */
  public static EstimateChangeTermEndResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EstimateChangeTermEndResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimateChangeTermEndResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimateChangeTermEndResponse. */
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

    public EstimateChangeTermEndResponse build() {
      return new EstimateChangeTermEndResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
