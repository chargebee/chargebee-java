package com.chargebee.v4.core.responses.feature;

import com.chargebee.v4.core.models.feature.Feature;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for FeatureReactivate operation. Contains the response data from the
 * API.
 */
public final class FeatureReactivateResponse extends BaseResponse {
  private final Feature feature;

  private FeatureReactivateResponse(Builder builder) {
    super(builder.httpResponse);

    this.feature = builder.feature;
  }

  /** Parse JSON response into FeatureReactivateResponse object. */
  public static FeatureReactivateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into FeatureReactivateResponse object with HTTP response. */
  public static FeatureReactivateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        builder.feature(Feature.fromJson(__featureJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureReactivateResponse from JSON", e);
    }
  }

  /** Create a new builder for FeatureReactivateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for FeatureReactivateResponse. */
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

    public FeatureReactivateResponse build() {
      return new FeatureReactivateResponse(this);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }
}
