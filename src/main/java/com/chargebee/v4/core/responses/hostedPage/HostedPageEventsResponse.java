package com.chargebee.v4.core.responses.hostedPage;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for HostedPageEvents operation. Contains the response data from the
 * API.
 */
public final class HostedPageEventsResponse {

  private final Boolean success;

  private final Response httpResponse;

  private HostedPageEventsResponse(Builder builder) {

    this.success = builder.success;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into HostedPageEventsResponse object. */
  public static HostedPageEventsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into HostedPageEventsResponse object with HTTP response. */
  public static HostedPageEventsResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.success(JsonUtil.getBoolean(json, "success"));

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder success(Boolean success) {
      this.success = success;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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
