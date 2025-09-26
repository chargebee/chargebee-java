package com.chargebee.core.responses.hostedPage;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for HostedPageEvents operation. Contains the response data from the
 * API.
 */
public final class HostedPageEventsResponse {

  private final Boolean success;

  private HostedPageEventsResponse(Builder builder) {

    this.success = builder.success;
  }

  /** Parse JSON response into HostedPageEventsResponse object. */
  public static HostedPageEventsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.success(JsonUtil.getBoolean(json, "success"));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageEventsResponse from JSON", e);
    }
  }

  /** Create a new builder for HostedPageEventsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for HostedPageEventsResponse. */
  public static class Builder {

    private Boolean success;

    private Builder() {}

    public Builder success(Boolean success) {
      this.success = success;
      return this;
    }

    public HostedPageEventsResponse build() {
      return new HostedPageEventsResponse(this);
    }
  }

  /** Get the success from the response. */
  public Boolean getSuccess() {
    return success;
  }
}
