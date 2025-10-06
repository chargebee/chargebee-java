package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CustomerAddPromotionalCredits operation. Contains the response data
 * from the API.
 */
public final class CustomerAddPromotionalCreditsResponse {

  private final Customer customer;

  private CustomerAddPromotionalCreditsResponse(Builder builder) {

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerAddPromotionalCreditsResponse object. */
  public static CustomerAddPromotionalCreditsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerAddPromotionalCreditsResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerAddPromotionalCreditsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerAddPromotionalCreditsResponse. */
  public static class Builder {

    private Customer customer;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public CustomerAddPromotionalCreditsResponse build() {
      return new CustomerAddPromotionalCreditsResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
