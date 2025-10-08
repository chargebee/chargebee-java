package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerCollectPayment operation. Contains the response data from
 * the API.
 */
public final class CustomerCollectPaymentResponse {

  private final Customer customer;

  private final Transaction transaction;

  private final Response httpResponse;

  private CustomerCollectPaymentResponse(Builder builder) {

    this.customer = builder.customer;

    this.transaction = builder.transaction;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CustomerCollectPaymentResponse object. */
  public static CustomerCollectPaymentResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerCollectPaymentResponse object with HTTP response. */
  public static CustomerCollectPaymentResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerCollectPaymentResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerCollectPaymentResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerCollectPaymentResponse. */
  public static class Builder {

    private Customer customer;

    private Transaction transaction;

    private Response httpResponse;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CustomerCollectPaymentResponse build() {
      return new CustomerCollectPaymentResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
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
