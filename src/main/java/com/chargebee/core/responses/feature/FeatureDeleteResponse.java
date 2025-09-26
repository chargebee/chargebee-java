package com.chargebee.core.responses.feature;

import com.chargebee.core.models.feature.Feature;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for FeatureDelete operation. Contains the response data from the API.
 */
public final class FeatureDeleteResponse {

  private final Feature feature;

  private FeatureDeleteResponse(Builder builder) {

    this.feature = builder.feature;
  }

  /** Parse JSON response into FeatureDeleteResponse object. */
  public static FeatureDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        builder.feature(Feature.fromJson(__featureJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for FeatureDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for FeatureDeleteResponse. */
  public static class Builder {

    private Feature feature;

    private Builder() {}

    public Builder feature(Feature feature) {
      this.feature = feature;
      return this;
    }

    public FeatureDeleteResponse build() {
      return new FeatureDeleteResponse(this);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }
}
