package com.chargebee.v4.models.configuration.responses;

import com.chargebee.v4.models.configuration.Configuration;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for ConfigurationExecute operation. Contains the response data from a
 * single resource get operation.
 */
public final class ConfigurationExecuteResponse extends BaseResponse {
  private final List<Configuration> configurations;

  private ConfigurationExecuteResponse(List<Configuration> configurations, Response httpResponse) {
    super(httpResponse);

    this.configurations = configurations;
  }

  /** Parse JSON response into ConfigurationExecuteResponse object. */
  public static ConfigurationExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ConfigurationExecuteResponse object with HTTP response. */
  public static ConfigurationExecuteResponse fromJson(String json, Response httpResponse) {
    try {

      List<Configuration> configurations =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "configurations")).stream()
              .map(Configuration::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new ConfigurationExecuteResponse(configurations, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ConfigurationExecuteResponse from JSON", e);
    }
  }

  /** Get the configurations from the response. */
  public List<Configuration> getConfigurations() {
    return configurations;
  }
}
