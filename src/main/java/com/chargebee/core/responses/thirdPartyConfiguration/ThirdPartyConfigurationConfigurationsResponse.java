package com.chargebee.core.responses.thirdPartyConfiguration;

import com.chargebee.core.models.thirdPartyConfiguration.ThirdPartyConfiguration;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ThirdPartyConfigurationConfigurations operation. Contains the
 * response data from a single resource get operation.
 */
public final class ThirdPartyConfigurationConfigurationsResponse {

  private final ThirdPartyConfiguration thirdPartyConfiguration;

  private ThirdPartyConfigurationConfigurationsResponse(
      ThirdPartyConfiguration thirdPartyConfiguration) {

    this.thirdPartyConfiguration = thirdPartyConfiguration;
  }

  /** Parse JSON response into ThirdPartyConfigurationConfigurationsResponse object. */
  public static ThirdPartyConfigurationConfigurationsResponse fromJson(String json) {
    try {

      ThirdPartyConfiguration thirdPartyConfiguration =
          ThirdPartyConfiguration.fromJson(JsonUtil.getObject(json, "third_party_configuration"));

      return new ThirdPartyConfigurationConfigurationsResponse(thirdPartyConfiguration);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyConfigurationConfigurationsResponse from JSON", e);
    }
  }

  /** Get the thirdPartyConfiguration from the response. */
  public ThirdPartyConfiguration getThirdPartyConfiguration() {
    return thirdPartyConfiguration;
  }
}
