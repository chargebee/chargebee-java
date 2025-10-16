package com.chargebee.v4.core.responses.thirdPartyConfiguration;

import com.chargebee.v4.core.models.thirdPartyConfiguration.ThirdPartyConfiguration;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyConfigurationRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class ThirdPartyConfigurationRetrieveResponse extends BaseResponse {
  private final ThirdPartyConfiguration thirdPartyConfiguration;

  private ThirdPartyConfigurationRetrieveResponse(
      ThirdPartyConfiguration thirdPartyConfiguration, Response httpResponse) {
    super(httpResponse);

    this.thirdPartyConfiguration = thirdPartyConfiguration;
  }

  /** Parse JSON response into ThirdPartyConfigurationRetrieveResponse object. */
  public static ThirdPartyConfigurationRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ThirdPartyConfigurationRetrieveResponse object with HTTP response. */
  public static ThirdPartyConfigurationRetrieveResponse fromJson(
      String json, Response httpResponse) {
    try {

      ThirdPartyConfiguration thirdPartyConfiguration =
          ThirdPartyConfiguration.fromJson(JsonUtil.getObject(json, "third_party_configuration"));

      return new ThirdPartyConfigurationRetrieveResponse(thirdPartyConfiguration, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyConfigurationRetrieveResponse from JSON", e);
    }
  }

  /** Get the thirdPartyConfiguration from the response. */
  public ThirdPartyConfiguration getThirdPartyConfiguration() {
    return thirdPartyConfiguration;
  }
}
