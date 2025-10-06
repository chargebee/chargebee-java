package com.chargebee.v4.core.responses.paymentIntent;

import com.chargebee.v4.core.models.paymentIntent.PaymentIntent;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PaymentIntentRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PaymentIntentRetrieveResponse {

  private final PaymentIntent paymentIntent;

  private PaymentIntentRetrieveResponse(PaymentIntent paymentIntent) {

    this.paymentIntent = paymentIntent;
  }

  /** Parse JSON response into PaymentIntentRetrieveResponse object. */
  public static PaymentIntentRetrieveResponse fromJson(String json) {
    try {

      PaymentIntent paymentIntent =
          PaymentIntent.fromJson(JsonUtil.getObject(json, "payment_intent"));

      return new PaymentIntentRetrieveResponse(paymentIntent);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentIntentRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentIntent from the response. */
  public PaymentIntent getPaymentIntent() {
    return paymentIntent;
  }
}
