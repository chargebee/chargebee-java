package com.chargebee.v4.models.customer.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerDeductPromotionalCredits operation. Contains the response
 * data from the API.
 */
public final class CustomerDeductPromotionalCreditsResponse extends BaseResponse {
  private final Customer customer;

  private CustomerDeductPromotionalCreditsResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerDeductPromotionalCreditsResponse object. */
  public static CustomerDeductPromotionalCreditsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into CustomerDeductPromotionalCreditsResponse object with HTTP response.
   */
  public static CustomerDeductPromotionalCreditsResponse fromJson(
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

    public CustomerDeductPromotionalCreditsResponse build() {
      return new CustomerDeductPromotionalCreditsResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  @Override
  public String toString() {
    return "CustomerDeductPromotionalCreditsResponse{" + "customer=" + customer + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerDeductPromotionalCreditsResponse that = (CustomerDeductPromotionalCreditsResponse) o;
    return java.util.Objects.equals(customer, that.customer);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(customer);
  }
}
