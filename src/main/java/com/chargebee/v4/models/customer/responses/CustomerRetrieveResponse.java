package com.chargebee.v4.models.customer.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.card.Card;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CustomerRetrieveResponse extends BaseResponse {
  private final Customer customer;

  private final Card card;

  private CustomerRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;

    this.card = builder.card;
  }

  /** Parse JSON response into CustomerRetrieveResponse object. */
  public static CustomerRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerRetrieveResponse object with HTTP response. */
  public static CustomerRetrieveResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse CustomerRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerRetrieveResponse. */
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

    public CustomerRetrieveResponse build() {
      return new CustomerRetrieveResponse(this);
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
    return "CustomerRetrieveResponse{" + "customer=" + customer + ", card=" + card + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerRetrieveResponse that = (CustomerRetrieveResponse) o;
    return java.util.Objects.equals(customer, that.customer)
        && java.util.Objects.equals(card, that.card);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(customer, card);
  }
}
