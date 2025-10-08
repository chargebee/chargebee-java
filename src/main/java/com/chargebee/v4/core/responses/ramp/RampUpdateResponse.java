package com.chargebee.v4.core.responses.ramp;

import com.chargebee.v4.core.models.ramp.Ramp;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for RampUpdate operation. Contains the response data from the API. */
public final class RampUpdateResponse {

  private final Ramp ramp;

  private final Response httpResponse;

  private RampUpdateResponse(Builder builder) {

    this.ramp = builder.ramp;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into RampUpdateResponse object. */
  public static RampUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RampUpdateResponse object with HTTP response. */
  public static RampUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __rampJson = JsonUtil.getObject(json, "ramp");
      if (__rampJson != null) {
        builder.ramp(Ramp.fromJson(__rampJson));
      }

      builder.httpResponse(httpResponse);
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

    public RampUpdateResponse build() {
      return new RampUpdateResponse(this);
    }
  }

  /** Get the ramp from the response. */
  public Ramp getRamp() {
    return ramp;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
