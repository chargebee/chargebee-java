package com.chargebee.core.responses.usage;

import com.chargebee.core.models.usage.Usage;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for UsageDelete operation. Contains the response data from the API. */
public final class UsageDeleteResponse {

  private final Usage usage;

  private UsageDeleteResponse(Builder builder) {

    this.usage = builder.usage;
  }

  /** Parse JSON response into UsageDeleteResponse object. */
  public static UsageDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __usageJson = JsonUtil.getObject(json, "usage");
      if (__usageJson != null) {
        builder.usage(Usage.fromJson(__usageJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for UsageDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UsageDeleteResponse. */
  public static class Builder {

    private Usage usage;

    private Builder() {}

    public Builder usage(Usage usage) {
      this.usage = usage;
      return this;
    }

    public UsageDeleteResponse build() {
      return new UsageDeleteResponse(this);
    }
  }

  /** Get the usage from the response. */
  public Usage getUsage() {
    return usage;
  }
}
