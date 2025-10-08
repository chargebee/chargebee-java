package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CustomerRetrieveResponse {

  private final Customer customer;

  private final Card card;

  private final Response httpResponse;

  private CustomerRetrieveResponse(Customer customer, Card card, Response httpResponse) {

    this.customer = customer;

    this.card = card;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into CustomerRetrieveResponse object. */
  public static CustomerRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerRetrieveResponse object with HTTP response. */
  public static CustomerRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Customer customer = Customer.fromJson(JsonUtil.getObject(json, "customer"));

      Card card = Card.fromJson(JsonUtil.getObject(json, "card"));

      return new CustomerRetrieveResponse(customer, card, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerRetrieveResponse from JSON", e);
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
