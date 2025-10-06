package com.chargebee.v4.core.responses.configuration;

import com.chargebee.v4.core.models.configuration.Configuration;

import com.chargebee.v4.internal.JsonUtil;
import java.util.List;

/**
 * Immutable response object for ConfigurationList operation. Contains the response data from a
 * single resource get operation.
 */
public final class ConfigurationListResponse {

  private final List<Configuration> configurations;

  private ConfigurationListResponse(List<Configuration> configurations) {

    this.configurations = configurations;
  }

  /** Parse JSON response into ConfigurationListResponse object. */
  public static ConfigurationListResponse fromJson(String json) {
    try {

      List<Configuration> configurations =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "configurations")).stream()
              .map(Configuration::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new ConfigurationListResponse(configurations);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ConfigurationListResponse from JSON", e);
    }
  }

  /** Get the configurations from the response. */
  public List<Configuration> getConfigurations() {
    return configurations;
  }
}
