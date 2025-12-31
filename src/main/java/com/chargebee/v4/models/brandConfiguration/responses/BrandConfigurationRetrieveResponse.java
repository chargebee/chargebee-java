package com.chargebee.v4.models.brandConfiguration.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BrandConfigurationRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class BrandConfigurationRetrieveResponse extends BaseResponse {
  private final Object brandConfiguration;

  private BrandConfigurationRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.brandConfiguration = builder.brandConfiguration;
  }

  /** Parse JSON response into BrandConfigurationRetrieveResponse object. */
  public static BrandConfigurationRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BrandConfigurationRetrieveResponse object with HTTP response. */
  public static BrandConfigurationRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.brandConfiguration(JsonUtil.getObject(json, "brand_configuration"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BrandConfigurationRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for BrandConfigurationRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BrandConfigurationRetrieveResponse. */
  public static class Builder {

    private Object brandConfiguration;

    private Response httpResponse;

    private Builder() {}

    public Builder brandConfiguration(Object brandConfiguration) {
      this.brandConfiguration = brandConfiguration;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public BrandConfigurationRetrieveResponse build() {
      return new BrandConfigurationRetrieveResponse(this);
    }
  }

  /** Get the brandConfiguration from the response. */
  public Object getBrandConfiguration() {
    return brandConfiguration;
  }

  @Override
  public String toString() {
    return "BrandConfigurationRetrieveResponse{" + "brandConfiguration=" + brandConfiguration + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BrandConfigurationRetrieveResponse that = (BrandConfigurationRetrieveResponse) o;
    return java.util.Objects.equals(brandConfiguration, that.brandConfiguration);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(brandConfiguration);
  }
}
