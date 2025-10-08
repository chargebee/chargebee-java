package com.chargebee.v4.core.responses.subscription;

import java.util.List;

import com.chargebee.v4.core.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionEditAdvanceInvoiceSchedule operation. Contains the
 * response data from the API.
 */
public final class SubscriptionEditAdvanceInvoiceScheduleResponse {

  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private final Response httpResponse;

  private SubscriptionEditAdvanceInvoiceScheduleResponse(Builder builder) {

    this.advanceInvoiceSchedules = builder.advanceInvoiceSchedules;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into SubscriptionEditAdvanceInvoiceScheduleResponse object. */
  public static SubscriptionEditAdvanceInvoiceScheduleResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionEditAdvanceInvoiceScheduleResponse object with HTTP
   * response.
   */
  public static SubscriptionEditAdvanceInvoiceScheduleResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.advanceInvoiceSchedules(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "advance_invoice_schedules")).stream()
              .map(AdvanceInvoiceSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionEditAdvanceInvoiceScheduleResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionEditAdvanceInvoiceScheduleResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionEditAdvanceInvoiceScheduleResponse. */
  public static class Builder {

    private List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

    private Response httpResponse;

    private Builder() {}

    public Builder advanceInvoiceSchedules(List<AdvanceInvoiceSchedule> advanceInvoiceSchedules) {
      this.advanceInvoiceSchedules = advanceInvoiceSchedules;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public SubscriptionEditAdvanceInvoiceScheduleResponse build() {
      return new SubscriptionEditAdvanceInvoiceScheduleResponse(this);
    }
  }

  /** Get the advanceInvoiceSchedules from the response. */
  public List<AdvanceInvoiceSchedule> getAdvanceInvoiceSchedules() {
    return advanceInvoiceSchedules;
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
