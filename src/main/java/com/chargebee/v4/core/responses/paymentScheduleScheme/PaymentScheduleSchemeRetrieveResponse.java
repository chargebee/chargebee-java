package com.chargebee.v4.core.responses.paymentScheduleScheme;

import com.chargebee.v4.core.models.paymentScheduleScheme.PaymentScheduleScheme;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentScheduleSchemeRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class PaymentScheduleSchemeRetrieveResponse {

  private final PaymentScheduleScheme paymentScheduleScheme;

  private final Response httpResponse;

  private PaymentScheduleSchemeRetrieveResponse(
      PaymentScheduleScheme paymentScheduleScheme, Response httpResponse) {

    this.paymentScheduleScheme = paymentScheduleScheme;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into PaymentScheduleSchemeRetrieveResponse object. */
  public static PaymentScheduleSchemeRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentScheduleSchemeRetrieveResponse object with HTTP response. */
  public static PaymentScheduleSchemeRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PaymentScheduleScheme paymentScheduleScheme =
          PaymentScheduleScheme.fromJson(JsonUtil.getObject(json, "payment_schedule_scheme"));

      return new PaymentScheduleSchemeRetrieveResponse(paymentScheduleScheme, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentScheduleSchemeRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentScheduleScheme from the response. */
  public PaymentScheduleScheme getPaymentScheduleScheme() {
    return paymentScheduleScheme;
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
