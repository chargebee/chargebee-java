package com.chargebee.v4.core.responses.paymentVoucher;

import com.chargebee.v4.core.models.paymentVoucher.PaymentVoucher;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PaymentVoucherCreate operation. Contains the response data from the
 * API.
 */
public final class PaymentVoucherCreateResponse {

  private final PaymentVoucher paymentVoucher;

  private PaymentVoucherCreateResponse(Builder builder) {

    this.paymentVoucher = builder.paymentVoucher;
  }

  /** Parse JSON response into PaymentVoucherCreateResponse object. */
  public static PaymentVoucherCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __paymentVoucherJson = JsonUtil.getObject(json, "payment_voucher");
      if (__paymentVoucherJson != null) {
        builder.paymentVoucher(PaymentVoucher.fromJson(__paymentVoucherJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentVoucherCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentVoucherCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentVoucherCreateResponse. */
  public static class Builder {

    private PaymentVoucher paymentVoucher;

    private Builder() {}

    public Builder paymentVoucher(PaymentVoucher paymentVoucher) {
      this.paymentVoucher = paymentVoucher;
      return this;
    }

    public PaymentVoucherCreateResponse build() {
      return new PaymentVoucherCreateResponse(this);
    }
  }

  /** Get the paymentVoucher from the response. */
  public PaymentVoucher getPaymentVoucher() {
    return paymentVoucher;
  }
}
