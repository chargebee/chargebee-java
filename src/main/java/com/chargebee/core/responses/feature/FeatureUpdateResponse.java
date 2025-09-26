package com.chargebee.core.responses.feature;

import com.chargebee.core.models.feature.Feature;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for FeatureUpdate operation. Contains the response data from the API.
 */
public final class FeatureUpdateResponse {

  private final Feature feature;

  private FeatureUpdateResponse(Builder builder) {

    this.feature = builder.feature;
  }

  /** Parse JSON response into FeatureUpdateResponse object. */
  public static FeatureUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        builder.feature(Feature.fromJson(__featureJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for FeatureUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for FeatureUpdateResponse. */
  public static class Builder {

    private Feature feature;

    private Builder() {}

    public Builder feature(Feature feature) {
      this.feature = feature;
      return this;
    }

    public FeatureUpdateResponse build() {
      return new FeatureUpdateResponse(this);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }
}
