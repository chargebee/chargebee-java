package com.chargebee.v4.core.responses.customer;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CustomerAddPromotionalCredits operation. Contains the response data
 * from the API.
 */
public final class CustomerAddPromotionalCreditsResponse extends BaseResponse {
  private final Customer customer;

  private CustomerAddPromotionalCreditsResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;
  }

  /** Parse JSON response into CustomerAddPromotionalCreditsResponse object. */
  public static CustomerAddPromotionalCreditsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CustomerAddPromotionalCreditsResponse object with HTTP response. */
  public static CustomerAddPromotionalCreditsResponse fromJson(String json, Response httpResponse) {
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

    public CustomerAddPromotionalCreditsResponse build() {
      return new CustomerAddPromotionalCreditsResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
