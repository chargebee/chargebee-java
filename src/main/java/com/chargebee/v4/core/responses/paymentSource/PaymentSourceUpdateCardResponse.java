package com.chargebee.v4.core.responses.paymentSource;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.paymentSource.PaymentSource;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentSourceUpdateCard operation. Contains the response data from
 * the API.
 */
public final class PaymentSourceUpdateCardResponse {

  private final Customer customer;

  private final PaymentSource paymentSource;

  private final Response httpResponse;

  private PaymentSourceUpdateCardResponse(Builder builder) {

    this.customer = builder.customer;

    this.paymentSource = builder.paymentSource;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into PaymentSourceUpdateCardResponse object. */
  public static PaymentSourceUpdateCardResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentSourceUpdateCardResponse object with HTTP response. */
  public static PaymentSourceUpdateCardResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      String __paymentSourceJson = JsonUtil.getObject(json, "payment_source");
      if (__paymentSourceJson != null) {
        builder.paymentSource(PaymentSource.fromJson(__paymentSourceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSourceUpdateCardResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentSourceUpdateCardResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentSourceUpdateCardResponse. */
  public static class Builder {

    private Customer customer;

    private PaymentSource paymentSource;

    private Response httpResponse;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder paymentSource(PaymentSource paymentSource) {
      this.paymentSource = paymentSource;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PaymentSourceUpdateCardResponse build() {
      return new PaymentSourceUpdateCardResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
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
