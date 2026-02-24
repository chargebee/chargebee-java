package com.chargebee.v4.models.paymentVoucher.responses;

import com.chargebee.v4.models.paymentVoucher.PaymentVoucher;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentVoucherRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PaymentVoucherRetrieveResponse extends BaseResponse {
  private final PaymentVoucher paymentVoucher;

  private PaymentVoucherRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.paymentVoucher = builder.paymentVoucher;
  }

  /** Parse JSON response into PaymentVoucherRetrieveResponse object. */
  public static PaymentVoucherRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentVoucherRetrieveResponse object with HTTP response. */
  public static PaymentVoucherRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __paymentVoucherObj = JsonUtil.getJsonObject(jsonObj, "payment_voucher");
      if (__paymentVoucherObj != null) {
        builder.paymentVoucher(PaymentVoucher.fromJson(__paymentVoucherObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentVoucherRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentVoucherRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentVoucherRetrieveResponse. */
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

    public PaymentVoucherRetrieveResponse build() {
      return new PaymentVoucherRetrieveResponse(this);
    }
  }

  /** Get the paymentVoucher from the response. */
  public PaymentVoucher getPaymentVoucher() {
    return paymentVoucher;
  }

  @Override
  public String toString() {
    return "PaymentVoucherRetrieveResponse{" + "paymentVoucher=" + paymentVoucher + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PaymentVoucherRetrieveResponse that = (PaymentVoucherRetrieveResponse) o;
    return java.util.Objects.equals(paymentVoucher, that.paymentVoucher);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(paymentVoucher);
  }
}
