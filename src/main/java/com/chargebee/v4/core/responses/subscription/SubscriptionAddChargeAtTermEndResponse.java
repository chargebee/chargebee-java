package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.estimate.Estimate;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionAddChargeAtTermEnd operation. Contains the response
 * data from the API.
 */
public final class SubscriptionAddChargeAtTermEndResponse extends BaseResponse {
  private final Estimate estimate;

  private SubscriptionAddChargeAtTermEndResponse(Builder builder) {
    super(builder.httpResponse);

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into SubscriptionAddChargeAtTermEndResponse object. */
  public static SubscriptionAddChargeAtTermEndResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into SubscriptionAddChargeAtTermEndResponse object with HTTP response. */
  public static SubscriptionAddChargeAtTermEndResponse fromJson(
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
          "Failed to parse SubscriptionAddChargeAtTermEndResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionAddChargeAtTermEndResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionAddChargeAtTermEndResponse. */
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

    public SubscriptionAddChargeAtTermEndResponse build() {
      return new SubscriptionAddChargeAtTermEndResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
