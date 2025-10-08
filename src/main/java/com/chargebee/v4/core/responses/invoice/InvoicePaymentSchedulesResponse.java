package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.paymentSchedule.PaymentSchedule;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for InvoicePaymentSchedules operation. Contains the response data from
 * a single resource get operation.
 */
public final class InvoicePaymentSchedulesResponse {

  private final List<PaymentSchedule> paymentSchedules;

  private final Response httpResponse;

  private InvoicePaymentSchedulesResponse(
      List<PaymentSchedule> paymentSchedules, Response httpResponse) {

    this.paymentSchedules = paymentSchedules;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into InvoicePaymentSchedulesResponse object. */
  public static InvoicePaymentSchedulesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoicePaymentSchedulesResponse object with HTTP response. */
  public static InvoicePaymentSchedulesResponse fromJson(String json, Response httpResponse) {
    try {

      List<PaymentSchedule> paymentSchedules =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "payment_schedules")).stream()
              .map(PaymentSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new InvoicePaymentSchedulesResponse(paymentSchedules, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoicePaymentSchedulesResponse from JSON", e);
    }
  }

  /** Get the paymentSchedules from the response. */
  public List<PaymentSchedule> getPaymentSchedules() {
    return paymentSchedules;
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
