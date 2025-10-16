package com.chargebee.v4.core.responses.card;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CardDeleteCardForCustomer operation. Contains the response data
 * from the API.
 */
public final class CardDeleteCardForCustomerResponse extends BaseResponse {
  private final Customer customer;

  private CardDeleteCardForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;
  }

  /** Parse JSON response into CardDeleteCardForCustomerResponse object. */
  public static CardDeleteCardForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CardDeleteCardForCustomerResponse object with HTTP response. */
  public static CardDeleteCardForCustomerResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CardDeleteCardForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for CardDeleteCardForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CardDeleteCardForCustomerResponse. */
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

    public CardDeleteCardForCustomerResponse build() {
      return new CardDeleteCardForCustomerResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
