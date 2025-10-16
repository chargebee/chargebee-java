package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerUpdateHierarchySettings operation. Contains the response
 * data from the API.
 */
public final class CustomerUpdateHierarchySettingsResponse extends BaseResponse {
  private final Customer customer;

  private CustomerUpdateHierarchySettingsResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerUpdateHierarchySettingsResponse object. */
  public static CustomerUpdateHierarchySettingsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerUpdateHierarchySettingsResponse object with HTTP response. */
  public static CustomerUpdateHierarchySettingsResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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
