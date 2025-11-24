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

  private BrandConfigurationExecuteResponse(Object brandConfiguration, Response httpResponse) {
    super(httpResponse);

    this.brandConfiguration = brandConfiguration;
  }

  /** Parse JSON response into BrandConfigurationExecuteResponse object. */
  public static BrandConfigurationExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BrandConfigurationExecuteResponse object with HTTP response. */
  public static BrandConfigurationExecuteResponse fromJson(String json, Response httpResponse) {
    try {

      Object brandConfiguration = JsonUtil.getObject(json, "brand_configuration");

      return new BrandConfigurationExecuteResponse(brandConfiguration, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BrandConfigurationExecuteResponse from JSON", e);
    }
  }

  /** Get the brandConfiguration from the response. */
  public Object getBrandConfiguration() {
    return brandConfiguration;
  }
}
