package com.chargebee.v4.models.paymentSource.responses;

import com.chargebee.v4.models.paymentSource.PaymentSource;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VerifyBankAccountForPaymentSource operation. Contains the response
 * data from the API.
 */
public final class VerifyBankAccountForPaymentSourceResponse extends BaseResponse {
  private final PaymentSource paymentSource;

  private VerifyBankAccountForPaymentSourceResponse(Builder builder) {
    super(builder.httpResponse);

    this.paymentSource = builder.paymentSource;
  }

  /** Parse JSON response into VerifyBankAccountForPaymentSourceResponse object. */
  public static VerifyBankAccountForPaymentSourceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into VerifyBankAccountForPaymentSourceResponse object with HTTP response.
   */
  public static VerifyBankAccountForPaymentSourceResponse fromJson(
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
          "Failed to parse VerifyBankAccountForPaymentSourceResponse from JSON", e);
    }
  }

  /** Create a new builder for VerifyBankAccountForPaymentSourceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VerifyBankAccountForPaymentSourceResponse. */
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

    public VerifyBankAccountForPaymentSourceResponse build() {
      return new VerifyBankAccountForPaymentSourceResponse(this);
    }
  }

  /** Get the paymentSource from the response. */
  public PaymentSource getPaymentSource() {
    return paymentSource;
  }
}
