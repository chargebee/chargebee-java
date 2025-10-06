package com.chargebee.v4.core.responses.card;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CardDeleteCardForCustomer operation. Contains the response data
 * from the API.
 */
public final class CardDeleteCardForCustomerResponse {

  private final Customer customer;

  private CardDeleteCardForCustomerResponse(Builder builder) {

    this.customer = builder.customer;
  }

  /** Parse JSON response into CardDeleteCardForCustomerResponse object. */
  public static CardDeleteCardForCustomerResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CardDeleteCardForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for CardDeleteCardForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CardDeleteCardForCustomerResponse. */
  public static class Builder {

    private Customer customer;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public CardDeleteCardForCustomerResponse build() {
      return new CardDeleteCardForCustomerResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
