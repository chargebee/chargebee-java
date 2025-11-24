package com.chargebee.v4.models.card.responses;

import com.chargebee.v4.models.customer.Customer;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteCardForCustomer operation. Contains the response data from
 * the API.
 */
public final class DeleteCardForCustomerResponse extends BaseResponse {
  private final Customer customer;

  private DeleteCardForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.customer = builder.customer;
  }

  /** Parse JSON response into DeleteCardForCustomerResponse object. */
  public static DeleteCardForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteCardForCustomerResponse object with HTTP response. */
  public static DeleteCardForCustomerResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteCardForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteCardForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteCardForCustomerResponse. */
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

    public DeleteCardForCustomerResponse build() {
      return new DeleteCardForCustomerResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }
}
