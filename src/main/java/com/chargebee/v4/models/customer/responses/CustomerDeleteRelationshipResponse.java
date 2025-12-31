package com.chargebee.v4.models.customer.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerDeleteRelationship operation. Contains the response data
 * from the API.
 */
public final class CustomerDeleteRelationshipResponse extends BaseResponse {
  private final Customer customer;

  private CustomerDeleteRelationshipResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerDeleteRelationshipResponse object. */
  public static CustomerDeleteRelationshipResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerDeleteRelationshipResponse object with HTTP response. */
  public static CustomerDeleteRelationshipResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      builder.httpResponse(httpResponse);
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

    public CustomerDeleteRelationshipResponse build() {
      return new CustomerDeleteRelationshipResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  @Override
  public String toString() {
    return "CustomerDeleteRelationshipResponse{" + "customer=" + customer + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerDeleteRelationshipResponse that = (CustomerDeleteRelationshipResponse) o;
    return java.util.Objects.equals(customer, that.customer);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(customer);
  }
}
