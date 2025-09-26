package com.chargebee.core.responses.estimate;

import com.chargebee.core.models.estimate.Estimate;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EstimatePauseSubscription operation. Contains the response data
 * from the API.
 */
public final class EstimatePauseSubscriptionResponse {

  private final Estimate estimate;

  private EstimatePauseSubscriptionResponse(Builder builder) {

    this.estimate = builder.estimate;
  }

  /** Parse JSON response into EstimatePauseSubscriptionResponse object. */
  public static EstimatePauseSubscriptionResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __estimateJson = JsonUtil.getObject(json, "estimate");
      if (__estimateJson != null) {
        builder.estimate(Estimate.fromJson(__estimateJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EstimatePauseSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for EstimatePauseSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EstimatePauseSubscriptionResponse. */
  public static class Builder {

    private Estimate estimate;

    private Builder() {}

    public Builder estimate(Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public EstimatePauseSubscriptionResponse build() {
      return new EstimatePauseSubscriptionResponse(this);
    }
  }

  /** Get the estimate from the response. */
  public Estimate getEstimate() {
    return estimate;
  }
}
