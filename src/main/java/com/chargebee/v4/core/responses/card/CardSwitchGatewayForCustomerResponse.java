package com.chargebee.v4.core.responses.card;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CardSwitchGatewayForCustomer operation. Contains the response data
 * from the API.
 */
public final class CardSwitchGatewayForCustomerResponse {

  private final Customer customer;

  private final Card card;

  private CardSwitchGatewayForCustomerResponse(Builder builder) {

    this.customer = builder.customer;

    this.card = builder.card;
  }

  /** Parse JSON response into CardSwitchGatewayForCustomerResponse object. */
  public static CardSwitchGatewayForCustomerResponse fromJson(String json) {
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

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CardSwitchGatewayForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for CardSwitchGatewayForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CardSwitchGatewayForCustomerResponse. */
  public static class Builder {

    private Customer customer;

    private Card card;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder card(Card card) {
      this.card = card;
      return this;
    }

    public CardSwitchGatewayForCustomerResponse build() {
      return new CardSwitchGatewayForCustomerResponse(this);
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
