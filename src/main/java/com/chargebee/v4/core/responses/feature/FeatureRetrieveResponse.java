package com.chargebee.v4.core.responses.feature;

import com.chargebee.v4.core.models.feature.Feature;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for FeatureRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class FeatureRetrieveResponse {

  private final Feature feature;

  private final Response httpResponse;

  private FeatureRetrieveResponse(Feature feature, Response httpResponse) {

    this.feature = feature;

    this.httpResponse = httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
