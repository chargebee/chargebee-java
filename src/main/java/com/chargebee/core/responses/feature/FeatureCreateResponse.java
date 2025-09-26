package com.chargebee.core.responses.feature;

import com.chargebee.core.models.feature.Feature;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for FeatureCreate operation. Contains the response data from the API.
 */
public final class FeatureCreateResponse {

  private final Feature feature;

  private FeatureCreateResponse(Builder builder) {

    this.feature = builder.feature;
  }

  /** Parse JSON response into FeatureCreateResponse object. */
  public static FeatureCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        builder.feature(Feature.fromJson(__featureJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for FeatureCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for FeatureCreateResponse. */
  public static class Builder {

    private Feature feature;

    private Builder() {}

    public Builder feature(Feature feature) {
      this.feature = feature;
      return this;
    }

    public FeatureCreateResponse build() {
      return new FeatureCreateResponse(this);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }
}
