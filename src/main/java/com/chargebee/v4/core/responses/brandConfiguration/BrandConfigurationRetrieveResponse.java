package com.chargebee.v4.core.responses.brandConfiguration;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BrandConfigurationRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class BrandConfigurationRetrieveResponse extends BaseResponse {
  private final Object brandConfiguration;

  private BrandConfigurationRetrieveResponse(Object brandConfiguration, Response httpResponse) {
    super(httpResponse);

    this.brandConfiguration = brandConfiguration;
  }

  /** Parse JSON response into BrandConfigurationRetrieveResponse object. */
  public static BrandConfigurationRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BrandConfigurationRetrieveResponse object with HTTP response. */
  public static BrandConfigurationRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Object brandConfiguration = JsonUtil.getObject(json, "brand_configuration");

      return new BrandConfigurationRetrieveResponse(brandConfiguration, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BrandConfigurationRetrieveResponse from JSON", e);
    }
  }

  /** Get the brandConfiguration from the response. */
  public Object getBrandConfiguration() {
    return brandConfiguration;
  }
}
