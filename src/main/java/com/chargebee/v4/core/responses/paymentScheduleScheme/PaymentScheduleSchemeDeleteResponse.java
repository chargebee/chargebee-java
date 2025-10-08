package com.chargebee.v4.core.responses.paymentScheduleScheme;

import com.chargebee.v4.core.models.paymentScheduleScheme.PaymentScheduleScheme;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentScheduleSchemeDelete operation. Contains the response data
 * from the API.
 */
public final class PaymentScheduleSchemeDeleteResponse {

  private final PaymentScheduleScheme paymentScheduleScheme;

  private final Response httpResponse;

  private PaymentScheduleSchemeDeleteResponse(Builder builder) {

    this.paymentScheduleScheme = builder.paymentScheduleScheme;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into PaymentScheduleSchemeDeleteResponse object. */
  public static PaymentScheduleSchemeDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentScheduleSchemeDeleteResponse object with HTTP response. */
  public static PaymentScheduleSchemeDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __paymentScheduleSchemeJson = JsonUtil.getObject(json, "payment_schedule_scheme");
      if (__paymentScheduleSchemeJson != null) {
        builder.paymentScheduleScheme(PaymentScheduleScheme.fromJson(__paymentScheduleSchemeJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentScheduleSchemeDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentScheduleSchemeDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentScheduleSchemeDeleteResponse. */
  public static class Builder {

    private PaymentScheduleScheme paymentScheduleScheme;

    private Response httpResponse;

    private Builder() {}

    public Builder paymentScheduleScheme(PaymentScheduleScheme paymentScheduleScheme) {
      this.paymentScheduleScheme = paymentScheduleScheme;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PaymentScheduleSchemeDeleteResponse build() {
      return new PaymentScheduleSchemeDeleteResponse(this);
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
