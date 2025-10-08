package com.chargebee.v4.core.responses.usageEvent;

import java.util.List;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsageEventBatchIngest operation. Contains the response data from
 * the API.
 */
public final class UsageEventBatchIngestResponse {

  private final String batchId;

  private final List<String> failedEvents;

  private final Response httpResponse;

  private UsageEventBatchIngestResponse(Builder builder) {

    this.batchId = builder.batchId;

    this.failedEvents = builder.failedEvents;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into UsageEventBatchIngestResponse object. */
  public static UsageEventBatchIngestResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UsageEventBatchIngestResponse object with HTTP response. */
  public static UsageEventBatchIngestResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.batchId(JsonUtil.getString(json, "batch_id"));

      builder.failedEvents(JsonUtil.parseArrayOfString(JsonUtil.getArray(json, "failed_events")));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageEventBatchIngestResponse from JSON", e);
    }
  }

  /** Create a new builder for UsageEventBatchIngestResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UsageEventBatchIngestResponse. */
  public static class Builder {

    private String batchId;

    private List<String> failedEvents;

    private Response httpResponse;

    private Builder() {}

    public Builder batchId(String batchId) {
      this.batchId = batchId;
      return this;
    }

    public Builder failedEvents(List<String> failedEvents) {
      this.failedEvents = failedEvents;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public UsageEventBatchIngestResponse build() {
      return new UsageEventBatchIngestResponse(this);
    }
  }

  /** Get the batchId from the response. */
  public String getBatchId() {
    return batchId;
  }

  /** Get the failedEvents from the response. */
  public List<String> getFailedEvents() {
    return failedEvents;
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
