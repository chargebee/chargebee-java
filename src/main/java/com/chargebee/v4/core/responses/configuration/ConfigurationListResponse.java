package com.chargebee.v4.core.responses.configuration;

import com.chargebee.v4.core.models.configuration.Configuration;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for ConfigurationList operation. Contains the response data from a
 * single resource get operation.
 */
public final class ConfigurationListResponse extends BaseResponse {
  private final List<Configuration> configurations;

  private ConfigurationListResponse(List<Configuration> configurations, Response httpResponse) {
    super(httpResponse);

    this.configurations = configurations;
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
}
