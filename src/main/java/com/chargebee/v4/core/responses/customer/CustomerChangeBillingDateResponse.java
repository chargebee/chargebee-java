package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerChangeBillingDate operation. Contains the response data
 * from the API.
 */
public final class CustomerChangeBillingDateResponse {

  private final Customer customer;

  private final Response httpResponse;

  private CustomerChangeBillingDateResponse(Builder builder) {

    this.customer = builder.customer;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CustomerChangeBillingDateResponse object. */
  public static CustomerChangeBillingDateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerChangeBillingDateResponse object with HTTP response. */
  public static CustomerChangeBillingDateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerChangeBillingDateResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerChangeBillingDateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerChangeBillingDateResponse. */
  public static class Builder {

    private Customer customer;

    private Response httpResponse;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CustomerChangeBillingDateResponse build() {
      return new CustomerChangeBillingDateResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
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
