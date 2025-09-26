package com.chargebee.core.responses.thirdPartyConfiguration;

import com.chargebee.core.models.thirdPartyConfiguration.ThirdPartyConfiguration;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ThirdPartyConfigurationUpdate operation. Contains the response data
 * from the API.
 */
public final class ThirdPartyConfigurationUpdateResponse {

  private final ThirdPartyConfiguration thirdPartyConfiguration;

  private ThirdPartyConfigurationUpdateResponse(Builder builder) {

    this.thirdPartyConfiguration = builder.thirdPartyConfiguration;
  }

  /** Parse JSON response into ThirdPartyConfigurationUpdateResponse object. */
  public static ThirdPartyConfigurationUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __thirdPartyConfigurationJson = JsonUtil.getObject(json, "third_party_configuration");
      if (__thirdPartyConfigurationJson != null) {
        builder.thirdPartyConfiguration(
            ThirdPartyConfiguration.fromJson(__thirdPartyConfigurationJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyConfigurationUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for ThirdPartyConfigurationUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ThirdPartyConfigurationUpdateResponse. */
  public static class Builder {

    private ThirdPartyConfiguration thirdPartyConfiguration;

    private Builder() {}

    public Builder thirdPartyConfiguration(ThirdPartyConfiguration thirdPartyConfiguration) {
      this.thirdPartyConfiguration = thirdPartyConfiguration;
      return this;
    }

    public ThirdPartyConfigurationUpdateResponse build() {
      return new ThirdPartyConfigurationUpdateResponse(this);
    }
  }

  /** Get the thirdPartyConfiguration from the response. */
  public ThirdPartyConfiguration getThirdPartyConfiguration() {
    return thirdPartyConfiguration;
  }
}
