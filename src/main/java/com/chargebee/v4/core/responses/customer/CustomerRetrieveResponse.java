package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CustomerRetrieveResponse extends BaseResponse {
  private final Customer customer;

  private final Card card;

  private CustomerRetrieveResponse(Customer customer, Card card, Response httpResponse) {
    super(httpResponse);

    this.customer = customer;

    this.card = card;
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
}
