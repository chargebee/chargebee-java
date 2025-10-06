package com.chargebee.v4.core.responses.feature;

import com.chargebee.v4.core.models.feature.Feature;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for FeatureReactivate operation. Contains the response data from the
 * API.
 */
public final class FeatureReactivateResponse {

  private final Feature feature;

  private FeatureReactivateResponse(Builder builder) {

    this.feature = builder.feature;
  }

  /** Parse JSON response into FeatureReactivateResponse object. */
  public static FeatureReactivateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        builder.feature(Feature.fromJson(__featureJson));
      }

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

    private Builder() {}

    public Builder feature(Feature feature) {
      this.feature = feature;
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
