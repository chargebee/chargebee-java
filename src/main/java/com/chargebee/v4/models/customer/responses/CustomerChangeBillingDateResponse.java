package com.chargebee.v4.models.customer.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerChangeBillingDate operation. Contains the response data
 * from the API.
 */
public final class CustomerChangeBillingDateResponse extends BaseResponse {
  private final Customer customer;

  private CustomerChangeBillingDateResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerChangeBillingDateResponse object. */
  public static CustomerChangeBillingDateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerChangeBillingDateResponse object with HTTP response. */
  public static CustomerChangeBillingDateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CustomerChangeBillingDateResponse from JSON", e);
    }
  }

  /** Create a new builder for CustomerChangeBillingDateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CustomerChangeBillingDateResponse. */
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

    public CustomerChangeBillingDateResponse build() {
      return new CustomerChangeBillingDateResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
