package com.chargebee.v4.models.brandConfiguration.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BrandConfigurationExecute operation. Contains the response data
 * from a single resource get operation.
 */
public final class BrandConfigurationExecuteResponse extends BaseResponse {
  private final Object brandConfiguration;

  private BrandConfigurationExecuteResponse(Builder builder) {
    super(builder.httpResponse);

    this.brandConfiguration = builder.brandConfiguration;
  }

  /** Parse JSON response into BrandConfigurationExecuteResponse object. */
  public static BrandConfigurationExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BrandConfigurationExecuteResponse object with HTTP response. */
  public static BrandConfigurationExecuteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.brandConfiguration(JsonUtil.getObject(json, "brand_configuration"));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BrandConfigurationExecuteResponse from JSON", e);
    }
  }

  /** Create a new builder for BrandConfigurationExecuteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for BrandConfigurationExecuteResponse. */
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

    public BrandConfigurationExecuteResponse build() {
      return new BrandConfigurationExecuteResponse(this);
    }
  }

  /** Get the brandConfiguration from the response. */
  public Object getBrandConfiguration() {
    return brandConfiguration;
  }
}
