package com.chargebee.v4.models.thirdPartyConfiguration.responses;

import com.chargebee.v4.models.thirdPartyConfiguration.ThirdPartyConfiguration;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyConfigurationRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class ThirdPartyConfigurationRetrieveResponse extends BaseResponse {
  private final ThirdPartyConfiguration thirdPartyConfiguration;

  private ThirdPartyConfigurationRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartyConfiguration = builder.thirdPartyConfiguration;
  }

  /** Parse JSON response into ThirdPartyConfigurationRetrieveResponse object. */
  public static ThirdPartyConfigurationRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ThirdPartyConfigurationRetrieveResponse object with HTTP response. */
  public static ThirdPartyConfigurationRetrieveResponse fromJson(
      String json, Response httpResponse) {
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
          "Failed to parse ThirdPartyConfigurationRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for ThirdPartyConfigurationRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ThirdPartyConfigurationRetrieveResponse. */
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

    public ThirdPartyConfigurationRetrieveResponse build() {
      return new ThirdPartyConfigurationRetrieveResponse(this);
    }
  }

  /** Get the thirdPartyConfiguration from the response. */
  public ThirdPartyConfiguration getThirdPartyConfiguration() {
    return thirdPartyConfiguration;
  }
}
