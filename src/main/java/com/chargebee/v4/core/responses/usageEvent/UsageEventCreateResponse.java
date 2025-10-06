package com.chargebee.v4.core.responses.usageEvent;

import com.chargebee.v4.core.models.usageEvent.UsageEvent;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for UsageEventCreate operation. Contains the response data from the
 * API.
 */
public final class UsageEventCreateResponse {

  private final UsageEvent usageEvent;

  private UsageEventCreateResponse(Builder builder) {

    this.usageEvent = builder.usageEvent;
  }

  /** Parse JSON response into UsageEventCreateResponse object. */
  public static UsageEventCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __usageEventJson = JsonUtil.getObject(json, "usage_event");
      if (__usageEventJson != null) {
        builder.usageEvent(UsageEvent.fromJson(__usageEventJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageEventCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for UsageEventCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UsageEventCreateResponse. */
  public static class Builder {

    private UsageEvent usageEvent;

    private Builder() {}

    public Builder usageEvent(UsageEvent usageEvent) {
      this.usageEvent = usageEvent;
      return this;
    }

    public UsageEventCreateResponse build() {
      return new UsageEventCreateResponse(this);
    }
  }

  /** Get the usageEvent from the response. */
  public UsageEvent getUsageEvent() {
    return usageEvent;
  }
}
