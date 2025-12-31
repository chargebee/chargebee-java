package com.chargebee.v4.models.customer.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.card.Card;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerDeleteContact operation. Contains the response data from
 * the API.
 */
public final class CustomerDeleteContactResponse extends BaseResponse {
  private final Customer customer;

  private final Card card;

  private CustomerDeleteContactResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;

    this.card = builder.card;
  }

  /** Parse JSON response into CustomerDeleteContactResponse object. */
  public static CustomerDeleteContactResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerDeleteContactResponse object with HTTP response. */
  public static CustomerDeleteContactResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse CustomerDeleteContactResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerDeleteContactResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerDeleteContactResponse. */
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

    public CustomerDeleteContactResponse build() {
      return new CustomerDeleteContactResponse(this);
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

  @Override
  public String toString() {
    return "CustomerDeleteContactResponse{" + "customer=" + customer + ", card=" + card + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerDeleteContactResponse that = (CustomerDeleteContactResponse) o;
    return java.util.Objects.equals(customer, that.customer)
        && java.util.Objects.equals(card, that.card);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(customer, card);
  }
}
