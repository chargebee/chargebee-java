package com.chargebee.v4.models.feature.responses;

import com.chargebee.v4.models.feature.Feature;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteFeature operation. Contains the response data from the API.
 */
public final class DeleteFeatureResponse extends BaseResponse {
  private final Feature feature;

  private DeleteFeatureResponse(Builder builder) {
    super(builder.httpResponse);

    this.feature = builder.feature;
  }

  /** Parse JSON response into DeleteFeatureResponse object. */
  public static DeleteFeatureResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteFeatureResponse object with HTTP response. */
  public static DeleteFeatureResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        builder.feature(Feature.fromJson(__featureJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteFeatureResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteFeatureResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteFeatureResponse. */
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

    public DeleteFeatureResponse build() {
      return new DeleteFeatureResponse(this);
    }
  }

  /** Get the feature from the response. */
  public Feature getFeature() {
    return feature;
  }
}
