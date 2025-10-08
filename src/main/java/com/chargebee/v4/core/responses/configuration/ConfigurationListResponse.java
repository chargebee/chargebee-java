package com.chargebee.v4.core.responses.configuration;

import com.chargebee.v4.core.models.configuration.Configuration;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for ConfigurationList operation. Contains the response data from a
 * single resource get operation.
 */
public final class ConfigurationListResponse {

  private final List<Configuration> configurations;

  private final Response httpResponse;

  private ConfigurationListResponse(List<Configuration> configurations, Response httpResponse) {

    this.configurations = configurations;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into ConfigurationListResponse object. */
  public static ConfigurationListResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ConfigurationListResponse object with HTTP response. */
  public static ConfigurationListResponse fromJson(String json, Response httpResponse) {
    try {

      List<Configuration> configurations =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "configurations")).stream()
              .map(Configuration::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new ConfigurationListResponse(configurations, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ConfigurationListResponse from JSON", e);
    }
  }

  /** Get the configurations from the response. */
  public List<Configuration> getConfigurations() {
    return configurations;
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
