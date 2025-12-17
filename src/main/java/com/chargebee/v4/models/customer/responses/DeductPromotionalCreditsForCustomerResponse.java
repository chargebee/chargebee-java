package com.chargebee.v4.models.customer.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeductPromotionalCreditsForCustomer operation. Contains the
 * response data from the API.
 */
public final class DeductPromotionalCreditsForCustomerResponse extends BaseResponse {
  private final Customer customer;

  private DeductPromotionalCreditsForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;
  }

  /** Parse JSON response into DeductPromotionalCreditsForCustomerResponse object. */
  public static DeductPromotionalCreditsForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into DeductPromotionalCreditsForCustomerResponse object with HTTP response.
   */
  public static DeductPromotionalCreditsForCustomerResponse fromJson(
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
          "Failed to parse DeductPromotionalCreditsForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for DeductPromotionalCreditsForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeductPromotionalCreditsForCustomerResponse. */
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

    public DeductPromotionalCreditsForCustomerResponse build() {
      return new DeductPromotionalCreditsForCustomerResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
