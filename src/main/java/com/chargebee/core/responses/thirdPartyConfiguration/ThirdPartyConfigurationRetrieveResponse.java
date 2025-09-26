package com.chargebee.core.responses.thirdPartyConfiguration;

import com.chargebee.core.models.thirdPartyConfiguration.ThirdPartyConfiguration;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ThirdPartyConfigurationRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class ThirdPartyConfigurationRetrieveResponse {

  private final ThirdPartyConfiguration thirdPartyConfiguration;

  private ThirdPartyConfigurationRetrieveResponse(ThirdPartyConfiguration thirdPartyConfiguration) {

    this.thirdPartyConfiguration = thirdPartyConfiguration;
  }

  /** Parse JSON response into ThirdPartyConfigurationRetrieveResponse object. */
  public static ThirdPartyConfigurationRetrieveResponse fromJson(String json) {
    try {

      ThirdPartyConfiguration thirdPartyConfiguration =
          ThirdPartyConfiguration.fromJson(JsonUtil.getObject(json, "third_party_configuration"));

      return new ThirdPartyConfigurationRetrieveResponse(thirdPartyConfiguration);
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
