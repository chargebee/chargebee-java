package com.chargebee.v4.core.responses.paymentIntent;

import com.chargebee.v4.core.models.paymentIntent.PaymentIntent;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentIntentUpdate operation. Contains the response data from the
 * API.
 */
public final class PaymentIntentUpdateResponse {

  private final PaymentIntent paymentIntent;

  private final Response httpResponse;

  private PaymentIntentUpdateResponse(Builder builder) {

    this.paymentIntent = builder.paymentIntent;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into PaymentIntentUpdateResponse object. */
  public static PaymentIntentUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentIntentUpdateResponse object with HTTP response. */
  public static PaymentIntentUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __paymentIntentJson = JsonUtil.getObject(json, "payment_intent");
      if (__paymentIntentJson != null) {
        builder.paymentIntent(PaymentIntent.fromJson(__paymentIntentJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentIntentUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentIntentUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentIntentUpdateResponse. */
  public static class Builder {

    private PaymentIntent paymentIntent;

    private Response httpResponse;

    private Builder() {}

    public Builder paymentIntent(PaymentIntent paymentIntent) {
      this.paymentIntent = paymentIntent;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PaymentIntentUpdateResponse build() {
      return new PaymentIntentUpdateResponse(this);
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
