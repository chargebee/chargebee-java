package com.chargebee.v4.core.responses.feature;

import com.chargebee.v4.core.models.feature.Feature;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for FeatureRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class FeatureRetrieveResponse extends BaseResponse {
  private final Feature feature;

  private FeatureRetrieveResponse(Feature feature, Response httpResponse) {
    super(httpResponse);

    this.feature = feature;
  }

  /** Parse JSON response into FeatureRetrieveResponse object. */
  public static FeatureRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into FeatureRetrieveResponse object with HTTP response. */
  public static FeatureRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Feature feature = Feature.fromJson(JsonUtil.getObject(json, "feature"));

      return new FeatureRetrieveResponse(feature, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureRetrieveResponse from JSON", e);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }
}
