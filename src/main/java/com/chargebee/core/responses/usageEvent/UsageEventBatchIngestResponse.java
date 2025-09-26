package com.chargebee.core.responses.usageEvent;

import java.util.List;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for UsageEventBatchIngest operation. Contains the response data from
 * the API.
 */
public final class UsageEventBatchIngestResponse {

  private final String batchId;

  private final List<String> failedEvents;

  private UsageEventBatchIngestResponse(Builder builder) {

    this.batchId = builder.batchId;

    this.failedEvents = builder.failedEvents;
  }

  /** Parse JSON response into UsageEventBatchIngestResponse object. */
  public static UsageEventBatchIngestResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.batchId(JsonUtil.getString(json, "batch_id"));

      builder.failedEvents(JsonUtil.parseArrayOfString(JsonUtil.getArray(json, "failed_events")));

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

    private Builder() {}

    public Builder batchId(String batchId) {
      this.batchId = batchId;
      return this;
    }

    public Builder failedEvents(List<String> failedEvents) {
      this.failedEvents = failedEvents;
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
}
