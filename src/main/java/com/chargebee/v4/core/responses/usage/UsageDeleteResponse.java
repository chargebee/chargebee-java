package com.chargebee.v4.core.responses.usage;

import com.chargebee.v4.core.models.usage.Usage;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for UsageDelete operation. Contains the response data from the API. */
public final class UsageDeleteResponse {

  private final Usage usage;

  private final Response httpResponse;

  private UsageDeleteResponse(Builder builder) {

    this.usage = builder.usage;

    this.httpResponse = builder.httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
