package com.chargebee.core.responses.customer;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CustomerDeductPromotionalCredits operation. Contains the response
 * data from the API.
 */
public final class CustomerDeductPromotionalCreditsResponse {

  private final Customer customer;

  private CustomerDeductPromotionalCreditsResponse(Builder builder) {

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerDeductPromotionalCreditsResponse object. */
  public static CustomerDeductPromotionalCreditsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerDeductPromotionalCreditsResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerDeductPromotionalCreditsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerDeductPromotionalCreditsResponse. */
  public static class Builder {

    private Customer customer;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public CustomerDeductPromotionalCreditsResponse build() {
      return new CustomerDeductPromotionalCreditsResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
