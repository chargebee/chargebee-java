package com.chargebee.core.responses.ramp;

import com.chargebee.core.models.ramp.Ramp;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for RampDelete operation. Contains the response data from the API. */
public final class RampDeleteResponse {

  private final Ramp ramp;

  private RampDeleteResponse(Builder builder) {

    this.ramp = builder.ramp;
  }

  /** Parse JSON response into RampDeleteResponse object. */
  public static RampDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __rampJson = JsonUtil.getObject(json, "ramp");
      if (__rampJson != null) {
        builder.ramp(Ramp.fromJson(__rampJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for RampDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RampDeleteResponse. */
  public static class Builder {

    private Ramp ramp;

    private Builder() {}

    public Builder ramp(Ramp ramp) {
      this.ramp = ramp;
      return this;
    }

    public RampDeleteResponse build() {
      return new RampDeleteResponse(this);
    }
  }

  /** Get the ramp from the response. */
  public Ramp getRamp() {
    return ramp;
  }
}
