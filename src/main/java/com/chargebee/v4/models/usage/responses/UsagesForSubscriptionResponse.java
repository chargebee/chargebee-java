package com.chargebee.v4.models.usage.responses;

import com.chargebee.v4.models.usage.Usage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsagesForSubscription operation. Contains the response data from a
 * single resource get operation.
 */
public final class UsagesForSubscriptionResponse extends BaseResponse {
  private final Usage usage;

  private UsagesForSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.usage = builder.usage;
  }

  /** Parse JSON response into UsagesForSubscriptionResponse object. */
  public static UsagesForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsagesForSubscriptionResponse object with HTTP response. */
  public static UsagesForSubscriptionResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __usageJson = JsonUtil.getObject(json, "usage");
      if (__usageJson != null) {
        builder.usage(Usage.fromJson(__usageJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsagesForSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for UsagesForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UsagesForSubscriptionResponse. */
  public static class Builder {

    private Usage usage;

    private Response httpResponse;

    private Builder() {}

    public Builder usage(Usage usage) {
      this.usage = usage;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public UsagesForSubscriptionResponse build() {
      return new UsagesForSubscriptionResponse(this);
    }
  }

  /** Get the usage from the response. */
  public Usage getUsage() {
    return usage;
  }
}
