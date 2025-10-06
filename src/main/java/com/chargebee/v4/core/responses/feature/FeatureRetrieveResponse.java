package com.chargebee.v4.core.responses.feature;

import com.chargebee.v4.core.models.feature.Feature;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for FeatureRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class FeatureRetrieveResponse {

  private final Feature feature;

  private FeatureRetrieveResponse(Feature feature) {

    this.feature = feature;
  }

  /** Parse JSON response into FeatureRetrieveResponse object. */
  public static FeatureRetrieveResponse fromJson(String json) {
    try {

      Feature feature = Feature.fromJson(JsonUtil.getObject(json, "feature"));

      return new FeatureRetrieveResponse(feature);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureRetrieveResponse from JSON", e);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }
}
