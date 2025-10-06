package com.chargebee.v4.core.responses.ramp;

import com.chargebee.v4.core.models.ramp.Ramp;

import com.chargebee.v4.internal.JsonUtil;

/** Immutable response object for RampUpdate operation. Contains the response data from the API. */
public final class RampUpdateResponse {

  private final Ramp ramp;

  private RampUpdateResponse(Builder builder) {

    this.ramp = builder.ramp;
  }

  /** Parse JSON response into RampUpdateResponse object. */
  public static RampUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __rampJson = JsonUtil.getObject(json, "ramp");
      if (__rampJson != null) {
        builder.ramp(Ramp.fromJson(__rampJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for RampUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RampUpdateResponse. */
  public static class Builder {

    private Ramp ramp;

    private Builder() {}

    public Builder ramp(Ramp ramp) {
      this.ramp = ramp;
      return this;
    }

    public RampUpdateResponse build() {
      return new RampUpdateResponse(this);
    }
  }

  /** Get the ramp from the response. */
  public Ramp getRamp() {
    return ramp;
  }
}
