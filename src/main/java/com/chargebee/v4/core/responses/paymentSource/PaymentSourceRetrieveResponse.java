package com.chargebee.v4.core.responses.paymentSource;

import com.chargebee.v4.core.models.paymentSource.PaymentSource;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentSourceRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PaymentSourceRetrieveResponse {

  private final PaymentSource paymentSource;

  private final Response httpResponse;

  private PaymentSourceRetrieveResponse(PaymentSource paymentSource, Response httpResponse) {

    this.paymentSource = paymentSource;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into PaymentSourceRetrieveResponse object. */
  public static PaymentSourceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentSourceRetrieveResponse object with HTTP response. */
  public static PaymentSourceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PaymentSource paymentSource =
          PaymentSource.fromJson(JsonUtil.getObject(json, "payment_source"));

      return new PaymentSourceRetrieveResponse(paymentSource, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSourceRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentSource from the response. */
  public PaymentSource getPaymentSource() {
    return paymentSource;
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
