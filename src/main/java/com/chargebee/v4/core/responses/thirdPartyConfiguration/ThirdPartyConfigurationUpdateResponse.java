package com.chargebee.v4.core.responses.thirdPartyConfiguration;

import com.chargebee.v4.core.models.thirdPartyConfiguration.ThirdPartyConfiguration;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyConfigurationUpdate operation. Contains the response data
 * from the API.
 */
public final class ThirdPartyConfigurationUpdateResponse {

  private final ThirdPartyConfiguration thirdPartyConfiguration;

  private final Response httpResponse;

  private ThirdPartyConfigurationUpdateResponse(Builder builder) {

    this.thirdPartyConfiguration = builder.thirdPartyConfiguration;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into ThirdPartyConfigurationUpdateResponse object. */
  public static ThirdPartyConfigurationUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ThirdPartyConfigurationUpdateResponse object with HTTP response. */
  public static ThirdPartyConfigurationUpdateResponse fromJson(String json, Response httpResponse) {
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
