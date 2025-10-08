package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerUpdateContact operation. Contains the response data from
 * the API.
 */
public final class CustomerUpdateContactResponse {

  private final Customer customer;

  private final Card card;

  private final Response httpResponse;

  private CustomerUpdateContactResponse(Builder builder) {

    this.customer = builder.customer;

    this.card = builder.card;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CustomerUpdateContactResponse object. */
  public static CustomerUpdateContactResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerUpdateContactResponse object with HTTP response. */
  public static CustomerUpdateContactResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      String __cardJson = JsonUtil.getObject(json, "card");
      if (__cardJson != null) {
        builder.card(Card.fromJson(__cardJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerUpdateContactResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerUpdateContactResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerUpdateContactResponse. */
  public static class Builder {

    private Customer customer;

    private Card card;

    private Response httpResponse;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder card(Card card) {
      this.card = card;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CustomerUpdateContactResponse build() {
      return new CustomerUpdateContactResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  /** Get the card from the response. */
  public Card getCard() {
    return card;
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
