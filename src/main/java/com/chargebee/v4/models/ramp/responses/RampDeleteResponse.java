package com.chargebee.v4.models.ramp.responses;

import com.chargebee.v4.models.ramp.Ramp;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for RampDelete operation. Contains the response data from the API. */
public final class RampDeleteResponse extends BaseResponse {
  private final Ramp ramp;

  private RampDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.ramp = builder.ramp;
  }

  /** Parse JSON response into RampDeleteResponse object. */
  public static RampDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RampDeleteResponse object with HTTP response. */
  public static RampDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __rampJson = JsonUtil.getObject(json, "ramp");
      if (__rampJson != null) {
        builder.ramp(Ramp.fromJson(__rampJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder ramp(Ramp ramp) {
      this.ramp = ramp;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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

  @Override
  public String toString() {
    return "RampDeleteResponse{" + "ramp=" + ramp + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RampDeleteResponse that = (RampDeleteResponse) o;
    return java.util.Objects.equals(ramp, that.ramp);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(ramp);
  }
}
