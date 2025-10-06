package com.chargebee.v4.core.responses.paymentSource;

import com.chargebee.v4.core.models.paymentSource.PaymentSource;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PaymentSourceRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PaymentSourceRetrieveResponse {

  private final PaymentSource paymentSource;

  private PaymentSourceRetrieveResponse(PaymentSource paymentSource) {

    this.paymentSource = paymentSource;
  }

  /** Parse JSON response into PaymentSourceRetrieveResponse object. */
  public static PaymentSourceRetrieveResponse fromJson(String json) {
    try {

      PaymentSource paymentSource =
          PaymentSource.fromJson(JsonUtil.getObject(json, "payment_source"));

      return new PaymentSourceRetrieveResponse(paymentSource);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSourceRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentSource from the response. */
  public PaymentSource getPaymentSource() {
    return paymentSource;
  }
}
