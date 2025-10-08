package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for SubscriptionRetrieveAdvanceInvoiceSchedule operation. Contains the
 * response data from a single resource get operation.
 */
public final class SubscriptionRetrieveAdvanceInvoiceScheduleResponse {

  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private final Response httpResponse;

  private SubscriptionRetrieveAdvanceInvoiceScheduleResponse(
      List<AdvanceInvoiceSchedule> advanceInvoiceSchedules, Response httpResponse) {

    this.advanceInvoiceSchedules = advanceInvoiceSchedules;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into SubscriptionRetrieveAdvanceInvoiceScheduleResponse object. */
  public static SubscriptionRetrieveAdvanceInvoiceScheduleResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionRetrieveAdvanceInvoiceScheduleResponse object with HTTP
   * response.
   */
  public static SubscriptionRetrieveAdvanceInvoiceScheduleResponse fromJson(
      String json, Response httpResponse) {
    try {

      List<AdvanceInvoiceSchedule> advanceInvoiceSchedules =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "advance_invoice_schedules")).stream()
              .map(AdvanceInvoiceSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new SubscriptionRetrieveAdvanceInvoiceScheduleResponse(
          advanceInvoiceSchedules, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionRetrieveAdvanceInvoiceScheduleResponse from JSON", e);
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
