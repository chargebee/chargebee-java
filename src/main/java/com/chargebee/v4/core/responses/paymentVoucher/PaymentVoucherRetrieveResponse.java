package com.chargebee.v4.core.responses.paymentVoucher;

import com.chargebee.v4.core.models.paymentVoucher.PaymentVoucher;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentVoucherRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PaymentVoucherRetrieveResponse extends BaseResponse {
  private final PaymentVoucher paymentVoucher;

  private PaymentVoucherRetrieveResponse(PaymentVoucher paymentVoucher, Response httpResponse) {
    super(httpResponse);

    this.paymentVoucher = paymentVoucher;
  }

  /** Parse JSON response into PaymentVoucherRetrieveResponse object. */
  public static PaymentVoucherRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentVoucherRetrieveResponse object with HTTP response. */
  public static PaymentVoucherRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PaymentVoucher paymentVoucher =
          PaymentVoucher.fromJson(JsonUtil.getObject(json, "payment_voucher"));

      return new PaymentVoucherRetrieveResponse(paymentVoucher, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentVoucherRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentVoucher from the response. */
  public PaymentVoucher getPaymentVoucher() {
    return paymentVoucher;
  }
}
