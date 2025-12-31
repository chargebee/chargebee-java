package com.chargebee.v4.models.ramp.responses;

import com.chargebee.v4.models.ramp.Ramp;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RampRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class RampRetrieveResponse extends BaseResponse {
  private final Ramp ramp;

  private RampRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.ramp = builder.ramp;
  }

  /** Parse JSON response into RampRetrieveResponse object. */
  public static RampRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RampRetrieveResponse object with HTTP response. */
  public static RampRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __rampJson = JsonUtil.getObject(json, "ramp");
      if (__rampJson != null) {
        builder.ramp(Ramp.fromJson(__rampJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for RampRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RampRetrieveResponse. */
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

    public RampRetrieveResponse build() {
      return new RampRetrieveResponse(this);
    }
  }

  /** Get the ramp from the response. */
  public Ramp getRamp() {
    return ramp;
  }

  @Override
  public String toString() {
    return "RampRetrieveResponse{" + "ramp=" + ramp + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RampRetrieveResponse that = (RampRetrieveResponse) o;
    return java.util.Objects.equals(ramp, that.ramp);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(ramp);
  }
}
