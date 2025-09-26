package com.chargebee.core.responses.paymentSource;

import com.chargebee.core.models.customer.Customer;

import com.chargebee.core.models.paymentSource.PaymentSource;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PaymentSourceUpdateBankAccount operation. Contains the response
 * data from the API.
 */
public final class PaymentSourceUpdateBankAccountResponse {

  private final Customer customer;

  private final PaymentSource paymentSource;

  private PaymentSourceUpdateBankAccountResponse(Builder builder) {

    this.customer = builder.customer;

    this.paymentSource = builder.paymentSource;
  }

  /** Parse JSON response into PaymentSourceUpdateBankAccountResponse object. */
  public static PaymentSourceUpdateBankAccountResponse fromJson(String json) {
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
          "Failed to parse PaymentSourceUpdateBankAccountResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentSourceUpdateBankAccountResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentSourceUpdateBankAccountResponse. */
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

    public PaymentSourceUpdateBankAccountResponse build() {
      return new PaymentSourceUpdateBankAccountResponse(this);
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
