package com.chargebee.v4.models.usageEvent.responses;

import com.chargebee.v4.models.usageEvent.UsageEvent;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsageEventCreate operation. Contains the response data from the
 * API.
 */
public final class UsageEventCreateResponse extends BaseResponse {
  private final UsageEvent usageEvent;

  private UsageEventCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.usageEvent = builder.usageEvent;
  }

  /** Parse JSON response into UsageEventCreateResponse object. */
  public static UsageEventCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsageEventCreateResponse object with HTTP response. */
  public static UsageEventCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __usageEventJson = JsonUtil.getObject(json, "usage_event");
      if (__usageEventJson != null) {
        builder.usageEvent(UsageEvent.fromJson(__usageEventJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder usageEvent(UsageEvent usageEvent) {
      this.usageEvent = usageEvent;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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
