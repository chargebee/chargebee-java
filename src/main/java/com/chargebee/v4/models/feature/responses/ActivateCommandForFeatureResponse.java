package com.chargebee.v4.models.feature.responses;

import com.chargebee.v4.models.feature.Feature;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ActivateCommandForFeature operation. Contains the response data
 * from the API.
 */
public final class ActivateCommandForFeatureResponse extends BaseResponse {
  private final Feature feature;

  private ActivateCommandForFeatureResponse(Builder builder) {
    super(builder.httpResponse);

    this.feature = builder.feature;
  }

  /** Parse JSON response into ActivateCommandForFeatureResponse object. */
  public static ActivateCommandForFeatureResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ActivateCommandForFeatureResponse object with HTTP response. */
  public static ActivateCommandForFeatureResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        builder.feature(Feature.fromJson(__featureJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ActivateCommandForFeatureResponse from JSON", e);
    }
  }

  /** Create a new builder for ActivateCommandForFeatureResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ActivateCommandForFeatureResponse. */
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

    public ActivateCommandForFeatureResponse build() {
      return new ActivateCommandForFeatureResponse(this);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }
}
