package com.chargebee.v4.core.responses.paymentSource;

import com.chargebee.v4.core.models.customer.Customer;

import com.chargebee.v4.core.models.paymentSource.PaymentSource;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PaymentSourceSwitchGatewayAccount operation. Contains the response
 * data from the API.
 */
public final class PaymentSourceSwitchGatewayAccountResponse {

  private final Customer customer;

  private final PaymentSource paymentSource;

  private PaymentSourceSwitchGatewayAccountResponse(Builder builder) {

    this.customer = builder.customer;

    this.paymentSource = builder.paymentSource;
  }

  /** Parse JSON response into PaymentSourceSwitchGatewayAccountResponse object. */
  public static PaymentSourceSwitchGatewayAccountResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __customerJson = JsonUtil.getObject(json, "customer");
      if (__customerJson != null) {
        builder.customer(Customer.fromJson(__customerJson));
      }

      String __paymentSourceJson = JsonUtil.getObject(json, "payment_source");
      if (__paymentSourceJson != null) {
        builder.paymentSource(PaymentSource.fromJson(__paymentSourceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentSourceSwitchGatewayAccountResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentSourceSwitchGatewayAccountResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentSourceSwitchGatewayAccountResponse. */
  public static class Builder {

    private Customer customer;

    private PaymentSource paymentSource;

    private Builder() {}

    public Builder customer(Customer customer) {
      this.customer = customer;
      return this;
    }

    public Builder paymentSource(PaymentSource paymentSource) {
      this.paymentSource = paymentSource;
      return this;
    }

    public PaymentSourceSwitchGatewayAccountResponse build() {
      return new PaymentSourceSwitchGatewayAccountResponse(this);
    }
  }

  /** Get the customer from the response. */
  public Customer getCustomer() {
    return customer;
  }

  /** Get the paymentSource from the response. */
  public PaymentSource getPaymentSource() {
    return paymentSource;
  }
}
