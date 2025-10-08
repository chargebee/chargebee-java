package com.chargebee.v4.core.responses.thirdPartyConfiguration;

import com.chargebee.v4.core.models.thirdPartyConfiguration.ThirdPartyConfiguration;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ThirdPartyConfigurationConfigurations operation. Contains the
 * response data from a single resource get operation.
 */
public final class ThirdPartyConfigurationConfigurationsResponse {

  private final ThirdPartyConfiguration thirdPartyConfiguration;

  private final Response httpResponse;

  private ThirdPartyConfigurationConfigurationsResponse(
      ThirdPartyConfiguration thirdPartyConfiguration, Response httpResponse) {

    this.thirdPartyConfiguration = thirdPartyConfiguration;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into ThirdPartyConfigurationConfigurationsResponse object. */
  public static ThirdPartyConfigurationConfigurationsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into ThirdPartyConfigurationConfigurationsResponse object with HTTP
   * response.
   */
  public static ThirdPartyConfigurationConfigurationsResponse fromJson(
      String json, Response httpResponse) {
    try {

      ThirdPartyConfiguration thirdPartyConfiguration =
          ThirdPartyConfiguration.fromJson(JsonUtil.getObject(json, "third_party_configuration"));

      return new ThirdPartyConfigurationConfigurationsResponse(
          thirdPartyConfiguration, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ThirdPartyConfigurationConfigurationsResponse from JSON", e);
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
