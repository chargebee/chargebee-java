package com.chargebee.core.responses.ramp;

import com.chargebee.core.models.ramp.Ramp;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for RampCreateForSubscription operation. Contains the response data
 * from the API.
 */
public final class RampCreateForSubscriptionResponse {

  private final Ramp ramp;

  private RampCreateForSubscriptionResponse(Builder builder) {

    this.ramp = builder.ramp;
  }

  /** Parse JSON response into RampCreateForSubscriptionResponse object. */
  public static RampCreateForSubscriptionResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __rampJson = JsonUtil.getObject(json, "ramp");
      if (__rampJson != null) {
        builder.ramp(Ramp.fromJson(__rampJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampCreateForSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for RampCreateForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RampCreateForSubscriptionResponse. */
  public static class Builder {

    private Ramp ramp;

    private Builder() {}

    public Builder ramp(Ramp ramp) {
      this.ramp = ramp;
      return this;
    }

    public RampCreateForSubscriptionResponse build() {
      return new RampCreateForSubscriptionResponse(this);
    }
  }

  /** Get the ramp from the response. */
  public Ramp getRamp() {
    return ramp;
  }
}
