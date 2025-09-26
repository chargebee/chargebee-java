package com.chargebee.core.responses.customer;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CustomerClearPersonalData operation. Contains the response data
 * from the API.
 */
public final class CustomerClearPersonalDataResponse {

  private final Customer customer;

  private CustomerClearPersonalDataResponse(Builder builder) {

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerClearPersonalDataResponse object. */
  public static CustomerClearPersonalDataResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerClearPersonalDataResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerClearPersonalDataResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerClearPersonalDataResponse. */
  public static class Builder {

    private Customer customer;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public CustomerClearPersonalDataResponse build() {
      return new CustomerClearPersonalDataResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
