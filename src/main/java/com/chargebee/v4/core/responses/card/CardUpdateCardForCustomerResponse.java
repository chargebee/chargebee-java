package com.chargebee.v4.core.responses.card;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.card.Card;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CardUpdateCardForCustomer operation. Contains the response data
 * from the API.
 */
public final class CardUpdateCardForCustomerResponse extends BaseResponse {
  private final Customer customer;

  private final Card card;

  private CardUpdateCardForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;

    this.card = builder.card;
  }

  /** Parse JSON response into CardUpdateCardForCustomerResponse object. */
  public static CardUpdateCardForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CardUpdateCardForCustomerResponse object with HTTP response. */
  public static CardUpdateCardForCustomerResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse CardUpdateCardForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for CardUpdateCardForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CardUpdateCardForCustomerResponse. */
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

    public CardUpdateCardForCustomerResponse build() {
      return new CardUpdateCardForCustomerResponse(this);
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
