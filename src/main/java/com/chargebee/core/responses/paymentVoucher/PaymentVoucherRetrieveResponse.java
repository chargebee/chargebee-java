package com.chargebee.core.responses.paymentVoucher;

import com.chargebee.core.models.paymentVoucher.PaymentVoucher;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PaymentVoucherRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PaymentVoucherRetrieveResponse {

  private final PaymentVoucher paymentVoucher;

  private PaymentVoucherRetrieveResponse(PaymentVoucher paymentVoucher) {

    this.paymentVoucher = paymentVoucher;
  }

  /** Parse JSON response into PaymentVoucherRetrieveResponse object. */
  public static PaymentVoucherRetrieveResponse fromJson(String json) {
    try {

      PaymentVoucher paymentVoucher =
          PaymentVoucher.fromJson(JsonUtil.getObject(json, "payment_voucher"));

      return new PaymentVoucherRetrieveResponse(paymentVoucher);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentVoucherRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentVoucher from the response. */
  public PaymentVoucher getPaymentVoucher() {
    return paymentVoucher;
  }
}
