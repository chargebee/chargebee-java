package com.chargebee.v4.models.thirdPartyConfiguration.responses;

import com.chargebee.v4.models.thirdPartyConfiguration.ThirdPartyConfiguration;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyConfigurationUpdate operation. Contains the response data
 * from the API.
 */
public final class ThirdPartyConfigurationUpdateResponse extends BaseResponse {
  private final ThirdPartyConfiguration thirdPartyConfiguration;

  private ThirdPartyConfigurationUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.thirdPartyConfiguration = builder.thirdPartyConfiguration;
  }

  /** Parse JSON response into ThirdPartyConfigurationUpdateResponse object. */
  public static ThirdPartyConfigurationUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ThirdPartyConfigurationUpdateResponse object with HTTP response. */
  public static ThirdPartyConfigurationUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __thirdPartyConfigurationObj =
          JsonUtil.getJsonObject(jsonObj, "third_party_configuration");
      if (__thirdPartyConfigurationObj != null) {
        builder.thirdPartyConfiguration(
            ThirdPartyConfiguration.fromJson(__thirdPartyConfigurationObj));
      }

      builder.httpResponse(httpResponse);
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

    public ThirdPartyConfigurationUpdateResponse build() {
      return new ThirdPartyConfigurationUpdateResponse(this);
    }
  }

  /** Get the thirdPartyConfiguration from the response. */
  public ThirdPartyConfiguration getThirdPartyConfiguration() {
    return thirdPartyConfiguration;
  }

  @Override
  public String toString() {
    return "ThirdPartyConfigurationUpdateResponse{"
        + "thirdPartyConfiguration="
        + thirdPartyConfiguration
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ThirdPartyConfigurationUpdateResponse that = (ThirdPartyConfigurationUpdateResponse) o;
    return java.util.Objects.equals(thirdPartyConfiguration, that.thirdPartyConfiguration);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(thirdPartyConfiguration);
  }
}
