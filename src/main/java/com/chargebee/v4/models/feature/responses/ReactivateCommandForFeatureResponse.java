package com.chargebee.v4.models.feature.responses;

import com.chargebee.v4.models.feature.Feature;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ReactivateCommandForFeature operation. Contains the response data
 * from the API.
 */
public final class ReactivateCommandForFeatureResponse extends BaseResponse {
  private final Feature feature;

  private ReactivateCommandForFeatureResponse(Builder builder) {
    super(builder.httpResponse);

    this.feature = builder.feature;
  }

  /** Parse JSON response into ReactivateCommandForFeatureResponse object. */
  public static ReactivateCommandForFeatureResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ReactivateCommandForFeatureResponse object with HTTP response. */
  public static ReactivateCommandForFeatureResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        builder.feature(Feature.fromJson(__featureJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ReactivateCommandForFeatureResponse from JSON", e);
    }
  }

  /** Create a new builder for ReactivateCommandForFeatureResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ReactivateCommandForFeatureResponse. */
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

    public ReactivateCommandForFeatureResponse build() {
      return new ReactivateCommandForFeatureResponse(this);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }
}
