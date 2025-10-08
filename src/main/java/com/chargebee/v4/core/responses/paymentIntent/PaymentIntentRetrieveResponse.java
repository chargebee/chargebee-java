package com.chargebee.v4.core.responses.paymentIntent;

import com.chargebee.v4.core.models.paymentIntent.PaymentIntent;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentIntentRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PaymentIntentRetrieveResponse {

  private final PaymentIntent paymentIntent;

  private final Response httpResponse;

  private PaymentIntentRetrieveResponse(PaymentIntent paymentIntent, Response httpResponse) {

    this.paymentIntent = paymentIntent;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into PaymentIntentRetrieveResponse object. */
  public static PaymentIntentRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentIntentRetrieveResponse object with HTTP response. */
  public static PaymentIntentRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PaymentIntent paymentIntent =
          PaymentIntent.fromJson(JsonUtil.getObject(json, "payment_intent"));

      return new PaymentIntentRetrieveResponse(paymentIntent, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentIntentRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentIntent from the response. */
  public PaymentIntent getPaymentIntent() {
    return paymentIntent;
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
