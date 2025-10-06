package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CustomerMerge operation. Contains the response data from the API.
 */
public final class CustomerMergeResponse {

  private final Customer customer;

  private CustomerMergeResponse(Builder builder) {

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerMergeResponse object. */
  public static CustomerMergeResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerMergeResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerMergeResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerMergeResponse. */
  public static class Builder {

    private Customer customer;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public CustomerMergeResponse build() {
      return new CustomerMergeResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
