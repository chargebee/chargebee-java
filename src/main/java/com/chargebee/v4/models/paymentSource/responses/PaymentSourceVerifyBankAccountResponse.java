package com.chargebee.v4.models.paymentSource.responses;

import com.chargebee.v4.models.paymentSource.PaymentSource;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentSourceVerifyBankAccount operation. Contains the response
 * data from the API.
 */
public final class PaymentSourceVerifyBankAccountResponse extends BaseResponse {
  private final PaymentSource paymentSource;

  private PaymentSourceVerifyBankAccountResponse(Builder builder) {
    super(builder.httpResponse);

    this.paymentSource = builder.paymentSource;
  }

  /** Parse JSON response into PaymentSourceVerifyBankAccountResponse object. */
  public static PaymentSourceVerifyBankAccountResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentSourceVerifyBankAccountResponse object with HTTP response. */
  public static PaymentSourceVerifyBankAccountResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __paymentSourceJson = JsonUtil.getObject(json, "payment_source");
      if (__paymentSourceJson != null) {
        builder.paymentSource(PaymentSource.fromJson(__paymentSourceJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder paymentSource(PaymentSource paymentSource) {
      this.paymentSource = paymentSource;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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

  @Override
  public String toString() {
    return "PaymentSourceVerifyBankAccountResponse{" + "paymentSource=" + paymentSource + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PaymentSourceVerifyBankAccountResponse that = (PaymentSourceVerifyBankAccountResponse) o;
    return java.util.Objects.equals(paymentSource, that.paymentSource);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(paymentSource);
  }
}
