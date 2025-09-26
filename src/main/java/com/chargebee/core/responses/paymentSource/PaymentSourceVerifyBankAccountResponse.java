package com.chargebee.core.responses.paymentSource;

import com.chargebee.core.models.paymentSource.PaymentSource;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PaymentSourceVerifyBankAccount operation. Contains the response
 * data from the API.
 */
public final class PaymentSourceVerifyBankAccountResponse {

  private final PaymentSource paymentSource;

  private PaymentSourceVerifyBankAccountResponse(Builder builder) {

    this.paymentSource = builder.paymentSource;
  }

  /** Parse JSON response into PaymentSourceVerifyBankAccountResponse object. */
  public static PaymentSourceVerifyBankAccountResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __paymentSourceJson = JsonUtil.getObject(json, "payment_source");
      if (__paymentSourceJson != null) {
        builder.paymentSource(PaymentSource.fromJson(__paymentSourceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentSourceVerifyBankAccountResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentSourceVerifyBankAccountResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentSourceVerifyBankAccountResponse. */
  public static class Builder {

    private PaymentSource paymentSource;

    private Builder() {}

    public Builder paymentSource(PaymentSource paymentSource) {
      this.paymentSource = paymentSource;
      return this;
    }

    public PaymentSourceVerifyBankAccountResponse build() {
      return new PaymentSourceVerifyBankAccountResponse(this);
    }
  }

  /** Get the paymentSource from the response. */
  public PaymentSource getPaymentSource() {
    return paymentSource;
  }
}
