package com.chargebee.v4.models.thirdPartyConfiguration.responses;

import com.chargebee.v4.models.thirdPartyConfiguration.ThirdPartyConfiguration;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyConfigurationExecute operation. Contains the response
 * data from a single resource get operation.
 */
public final class ThirdPartyConfigurationExecuteResponse extends BaseResponse {
  private final ThirdPartyConfiguration thirdPartyConfiguration;

  private ThirdPartyConfigurationExecuteResponse(
      ThirdPartyConfiguration thirdPartyConfiguration, Response httpResponse) {
    super(httpResponse);

    this.thirdPartyConfiguration = thirdPartyConfiguration;
  }

  /** Parse JSON response into ThirdPartyConfigurationExecuteResponse object. */
  public static ThirdPartyConfigurationExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ThirdPartyConfigurationExecuteResponse object with HTTP response. */
  public static ThirdPartyConfigurationExecuteResponse fromJson(
      String json, Response httpResponse) {
    try {

      ThirdPartyConfiguration thirdPartyConfiguration =
          ThirdPartyConfiguration.fromJson(JsonUtil.getObject(json, "third_party_configuration"));

      return new ThirdPartyConfigurationExecuteResponse(thirdPartyConfiguration, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyConfigurationExecuteResponse from JSON", e);
    }
  }

  /** Get the thirdPartyConfiguration from the response. */
  public ThirdPartyConfiguration getThirdPartyConfiguration() {
    return thirdPartyConfiguration;
  }
}
