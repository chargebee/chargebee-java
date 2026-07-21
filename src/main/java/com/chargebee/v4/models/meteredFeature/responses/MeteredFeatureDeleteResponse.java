package com.chargebee.v4.models.meteredFeature.responses;

import com.chargebee.v4.models.meter.Meter;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for MeteredFeatureDelete operation. Contains the response data from the
 * API.
 */
public final class MeteredFeatureDeleteResponse extends BaseResponse {
  private final Meter meter;

  private MeteredFeatureDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.meter = builder.meter;
  }

  /** Parse JSON response into MeteredFeatureDeleteResponse object. */
  public static MeteredFeatureDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into MeteredFeatureDeleteResponse object with HTTP response. */
  public static MeteredFeatureDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __meterObj = JsonUtil.getJsonObject(jsonObj, "meter");
      if (__meterObj != null) {
        builder.meter(Meter.fromJson(__meterObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse MeteredFeatureDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for MeteredFeatureDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for MeteredFeatureDeleteResponse. */
  public static class Builder {

    private Meter meter;

    private Response httpResponse;

    private Builder() {}

    public Builder meter(Meter meter) {
      this.meter = meter;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public MeteredFeatureDeleteResponse build() {
      return new MeteredFeatureDeleteResponse(this);
    }
  }

  /** Get the meter from the response. */
  public Meter getMeter() {
    return meter;
  }

  @Override
  public String toString() {
    return "MeteredFeatureDeleteResponse{" + "meter=" + meter + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MeteredFeatureDeleteResponse that = (MeteredFeatureDeleteResponse) o;
    return java.util.Objects.equals(meter, that.meter);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(meter);
  }
}
