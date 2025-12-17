package com.chargebee.v4.models.feature.responses;

import com.chargebee.v4.models.feature.Feature;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ArchiveCommandForFeature operation. Contains the response data from
 * the API.
 */
public final class ArchiveCommandForFeatureResponse extends BaseResponse {
  private final Feature feature;

  private ArchiveCommandForFeatureResponse(Builder builder) {
    super(builder.httpResponse);

    this.feature = builder.feature;
  }

  /** Parse JSON response into ArchiveCommandForFeatureResponse object. */
  public static ArchiveCommandForFeatureResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ArchiveCommandForFeatureResponse object with HTTP response. */
  public static ArchiveCommandForFeatureResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        builder.feature(Feature.fromJson(__featureJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ArchiveCommandForFeatureResponse from JSON", e);
    }
  }

  /** Create a new builder for ArchiveCommandForFeatureResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ArchiveCommandForFeatureResponse. */
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

    public ArchiveCommandForFeatureResponse build() {
      return new ArchiveCommandForFeatureResponse(this);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }
}
