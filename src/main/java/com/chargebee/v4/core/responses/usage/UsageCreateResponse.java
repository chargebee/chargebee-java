package com.chargebee.v4.core.responses.usage;

import com.chargebee.v4.core.models.usage.Usage;

import com.chargebee.v4.internal.JsonUtil;

/** Immutable response object for UsageCreate operation. Contains the response data from the API. */
public final class UsageCreateResponse {

  private final Usage usage;

  private UsageCreateResponse(Builder builder) {

    this.usage = builder.usage;
  }

  /** Parse JSON response into UsageCreateResponse object. */
  public static UsageCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __usageJson = JsonUtil.getObject(json, "usage");
      if (__usageJson != null) {
        builder.usage(Usage.fromJson(__usageJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for UsageCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UsageCreateResponse. */
  public static class Builder {

    private Usage usage;

    private Builder() {}

    public Builder usage(Usage usage) {
      this.usage = usage;
      return this;
    }

    public UsageCreateResponse build() {
      return new UsageCreateResponse(this);
    }
  }

  /** Get the usage from the response. */
  public Usage getUsage() {
    return usage;
  }
}
