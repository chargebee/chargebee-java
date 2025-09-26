package com.chargebee.core.responses.customer;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CustomerUpdateHierarchySettings operation. Contains the response
 * data from the API.
 */
public final class CustomerUpdateHierarchySettingsResponse {

  private final Customer customer;

  private CustomerUpdateHierarchySettingsResponse(Builder builder) {

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerUpdateHierarchySettingsResponse object. */
  public static CustomerUpdateHierarchySettingsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CustomerUpdateHierarchySettingsResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerUpdateHierarchySettingsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerUpdateHierarchySettingsResponse. */
  public static class Builder {

    private Customer customer;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public CustomerUpdateHierarchySettingsResponse build() {
      return new CustomerUpdateHierarchySettingsResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
