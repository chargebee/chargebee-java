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

  private ConfigurationExecuteResponse(Builder builder) {
    super(builder.httpResponse);

    this.configurations = builder.configurations;
  }

  /** Parse JSON response into ConfigurationExecuteResponse object. */
  public static ConfigurationExecuteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ConfigurationExecuteResponse object with HTTP response. */
  public static ConfigurationExecuteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __configurationsJson = JsonUtil.getArray(json, "configurations");
      if (__configurationsJson != null) {
        builder.configurations(
            JsonUtil.parseObjectArray(__configurationsJson).stream()
                .map(Configuration::fromJson)
                .collect(java.util.stream.Collectors.toList()));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ConfigurationExecuteResponse from JSON", e);
    }
  }

  /** Create a new builder for ConfigurationExecuteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ConfigurationExecuteResponse. */
  public static class Builder {

    private List<Configuration> configurations;

    private Response httpResponse;

    private Builder() {}

    public Builder configurations(List<Configuration> configurations) {
      this.configurations = configurations;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ConfigurationExecuteResponse build() {
      return new ConfigurationExecuteResponse(this);
    }
  }

  /** Get the configurations from the response. */
  public List<Configuration> getConfigurations() {
    return configurations;
  }
}
