package com.chargebee.v4.core.responses.usage;

import com.chargebee.v4.core.models.usage.Usage;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for UsageDelete operation. Contains the response data from the API. */
public final class UsageDeleteResponse extends BaseResponse {
  private final Usage usage;

  private UsageDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.usage = builder.usage;
  }

  /** Parse JSON response into UsageDeleteResponse object. */
  public static UsageDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsageDeleteResponse object with HTTP response. */
  public static UsageDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __usageJson = JsonUtil.getObject(json, "usage");
      if (__usageJson != null) {
        builder.usage(Usage.fromJson(__usageJson));
      }

      builder.httpResponse(httpResponse);
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

    public UsageDeleteResponse build() {
      return new UsageDeleteResponse(this);
    }
  }

  /** Get the usage from the response. */
  public Usage getUsage() {
    return usage;
  }
}
