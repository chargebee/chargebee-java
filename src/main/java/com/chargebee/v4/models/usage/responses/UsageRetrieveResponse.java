package com.chargebee.v4.models.usage.responses;

import com.chargebee.v4.models.usage.Usage;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsageRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class UsageRetrieveResponse extends BaseResponse {
  private final Usage usage;

  private UsageRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.usage = builder.usage;
  }

  /** Parse JSON response into UsageRetrieveResponse object. */
  public static UsageRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsageRetrieveResponse object with HTTP response. */
  public static UsageRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __usageJson = JsonUtil.getObject(json, "usage");
      if (__usageJson != null) {
        builder.usage(Usage.fromJson(__usageJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for UsageRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UsageRetrieveResponse. */
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

    public UsageRetrieveResponse build() {
      return new UsageRetrieveResponse(this);
    }
  }

  /** Get the usage from the response. */
  public Usage getUsage() {
    return usage;
  }

  @Override
  public String toString() {
    return "UsageRetrieveResponse{" + "usage=" + usage + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UsageRetrieveResponse that = (UsageRetrieveResponse) o;
    return java.util.Objects.equals(usage, that.usage);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(usage);
  }
}
