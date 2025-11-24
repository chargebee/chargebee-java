package com.chargebee.v4.models.thirdPartyConfiguration.responses;

import com.chargebee.v4.models.thirdPartyConfiguration.ThirdPartyConfiguration;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyConfigurationCreate operation. Contains the response data
 * from the API.
 */
public final class ThirdPartyConfigurationCreateResponse extends BaseResponse {
  private final ThirdPartyConfiguration thirdPartyConfiguration;

  private ThirdPartyConfigurationCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartyConfiguration = builder.thirdPartyConfiguration;
  }

  /** Parse JSON response into ThirdPartyConfigurationCreateResponse object. */
  public static ThirdPartyConfigurationCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ThirdPartyConfigurationCreateResponse object with HTTP response. */
  public static ThirdPartyConfigurationCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __thirdPartyConfigurationJson = JsonUtil.getObject(json, "third_party_configuration");
      if (__thirdPartyConfigurationJson != null) {
        builder.thirdPartyConfiguration(
            ThirdPartyConfiguration.fromJson(__thirdPartyConfigurationJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyConfigurationCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for ThirdPartyConfigurationCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ThirdPartyConfigurationCreateResponse. */
  public static class Builder {

    private ThirdPartyConfiguration thirdPartyConfiguration;

    private Response httpResponse;

    private Builder() {}

    public Builder thirdPartyConfiguration(ThirdPartyConfiguration thirdPartyConfiguration) {
      this.thirdPartyConfiguration = thirdPartyConfiguration;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ThirdPartyConfigurationCreateResponse build() {
      return new ThirdPartyConfigurationCreateResponse(this);
    }
  }

  /** Get the thirdPartyConfiguration from the response. */
  public ThirdPartyConfiguration getThirdPartyConfiguration() {
    return thirdPartyConfiguration;
  }
}
