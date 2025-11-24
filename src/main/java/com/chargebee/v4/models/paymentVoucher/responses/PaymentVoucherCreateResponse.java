package com.chargebee.v4.models.paymentVoucher.responses;

import com.chargebee.v4.models.paymentVoucher.PaymentVoucher;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentVoucherCreate operation. Contains the response data from the
 * API.
 */
public final class PaymentVoucherCreateResponse extends BaseResponse {
  private final PaymentVoucher paymentVoucher;

  private PaymentVoucherCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.paymentVoucher = builder.paymentVoucher;
  }

  /** Parse JSON response into PaymentVoucherCreateResponse object. */
  public static PaymentVoucherCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentVoucherCreateResponse object with HTTP response. */
  public static PaymentVoucherCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __paymentVoucherJson = JsonUtil.getObject(json, "payment_voucher");
      if (__paymentVoucherJson != null) {
        builder.paymentVoucher(PaymentVoucher.fromJson(__paymentVoucherJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder paymentVoucher(PaymentVoucher paymentVoucher) {
      this.paymentVoucher = paymentVoucher;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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
