package com.chargebee.v4.models.usageEvent.responses;

import java.util.List;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UsageEventBatchIngest operation. Contains the response data from
 * the API.
 */
public final class UsageEventBatchIngestResponse extends BaseResponse {
  private final String batchId;

  private final List<Object> failedEvents;

  private UsageEventBatchIngestResponse(Builder builder) {
    super(builder.httpResponse);

    this.batchId = builder.batchId;

    this.failedEvents = builder.failedEvents;
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

      String __failedEventsJson = JsonUtil.getArray(json, "failed_events);");
      if (__failedEventsJson != null) {
        builder.failedEvents(
            JsonUtil.parseObjectArray(__failedEventsJson).stream()
                .map(JsonUtil::parseJsonObjectToMap)
                .collect(java.util.stream.Collectors.toList()));
      }

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

    private List<Object> failedEvents;

    private Response httpResponse;

    private Builder() {}

    public Builder batchId(String batchId) {
      this.batchId = batchId;
      return this;
    }

    public Builder failedEvents(List<Object> failedEvents) {
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
  public List<Object> getFailedEvents() {
    return failedEvents;
  }

  @Override
  public String toString() {
    return "UsageEventBatchIngestResponse{"
        + "batchId="
        + batchId
        + ", failedEvents="
        + failedEvents
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UsageEventBatchIngestResponse that = (UsageEventBatchIngestResponse) o;
    return java.util.Objects.equals(batchId, that.batchId)
        && java.util.Objects.equals(failedEvents, that.failedEvents);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(batchId, failedEvents);
  }
}
