package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CustomerDeleteRelationship operation. Contains the response data
 * from the API.
 */
public final class CustomerDeleteRelationshipResponse {

  private final Customer customer;

  private CustomerDeleteRelationshipResponse(Builder builder) {

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerDeleteRelationshipResponse object. */
  public static CustomerDeleteRelationshipResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerDeleteRelationshipResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerDeleteRelationshipResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerDeleteRelationshipResponse. */
  public static class Builder {

    private Customer customer;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public CustomerDeleteRelationshipResponse build() {
      return new CustomerDeleteRelationshipResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
