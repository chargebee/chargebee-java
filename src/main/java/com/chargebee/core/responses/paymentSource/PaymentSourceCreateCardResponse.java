package com.chargebee.core.responses.paymentSource;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.core.models.paymentSource.PaymentSource;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PaymentSourceCreateCard operation. Contains the response data from
 * the API.
 */
public final class PaymentSourceCreateCardResponse {

  private final Customer customer;

  private final PaymentSource paymentSource;

  private PaymentSourceCreateCardResponse(Builder builder) {

    this.customer = builder.customer;

    this.paymentSource = builder.paymentSource;
  }

  /** Parse JSON response into PaymentSourceCreateCardResponse object. */
  public static PaymentSourceCreateCardResponse fromJson(String json) {
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
      throw new RuntimeException("Failed to parse PaymentSourceCreateCardResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentSourceCreateCardResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentSourceCreateCardResponse. */
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

    public PaymentSourceCreateCardResponse build() {
      return new PaymentSourceCreateCardResponse(this);
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
