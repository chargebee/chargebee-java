package com.chargebee.v4.models.feature.responses;

import com.chargebee.v4.models.feature.Feature;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for FeatureArchive operation. Contains the response data from the API.
 */
public final class FeatureArchiveResponse extends BaseResponse {
  private final Feature feature;

  private FeatureArchiveResponse(Builder builder) {
    super(builder.httpResponse);

    this.feature = builder.feature;
  }

  /** Parse JSON response into FeatureArchiveResponse object. */
  public static FeatureArchiveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into FeatureArchiveResponse object with HTTP response. */
  public static FeatureArchiveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        builder.feature(Feature.fromJson(__featureJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureArchiveResponse from JSON", e);
    }
  }

  /** Create a new builder for FeatureArchiveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for FeatureArchiveResponse. */
  public static class Builder {

    private Feature feature;

    private Response httpResponse;

    private Builder() {}

    public Builder feature(Feature feature) {
      this.feature = feature;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public FeatureArchiveResponse build() {
      return new FeatureArchiveResponse(this);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }

  @Override
  public String toString() {
    return "FeatureArchiveResponse{" + "feature=" + feature + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FeatureArchiveResponse that = (FeatureArchiveResponse) o;
    return java.util.Objects.equals(feature, that.feature);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(feature);
  }
}
