package com.chargebee.v4.models.ramp.responses;

import com.chargebee.v4.models.ramp.Ramp;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for UpdateRamp operation. Contains the response data from the API. */
public final class UpdateRampResponse extends BaseResponse {
  private final Ramp ramp;

  private UpdateRampResponse(Builder builder) {
    super(builder.httpResponse);

    this.ramp = builder.ramp;
  }

  /** Parse JSON response into UpdateRampResponse object. */
  public static UpdateRampResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UpdateRampResponse object with HTTP response. */
  public static UpdateRampResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __rampJson = JsonUtil.getObject(json, "ramp");
      if (__rampJson != null) {
        builder.ramp(Ramp.fromJson(__rampJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UpdateRampResponse from JSON", e);
    }
  }

  /** Create a new builder for UpdateRampResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UpdateRampResponse. */
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

    public UpdateRampResponse build() {
      return new UpdateRampResponse(this);
    }
  }

  /** Get the ramp from the response. */
  public Ramp getRamp() {
    return ramp;
  }
}
