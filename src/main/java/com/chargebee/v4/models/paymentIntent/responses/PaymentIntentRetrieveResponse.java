package com.chargebee.v4.models.paymentIntent.responses;

import com.chargebee.v4.models.paymentIntent.PaymentIntent;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentIntentRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PaymentIntentRetrieveResponse extends BaseResponse {
  private final PaymentIntent paymentIntent;

  private PaymentIntentRetrieveResponse(PaymentIntent paymentIntent, Response httpResponse) {
    super(httpResponse);

    this.paymentIntent = paymentIntent;
  }

  /** Parse JSON response into PaymentIntentRetrieveResponse object. */
  public static PaymentIntentRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentIntentRetrieveResponse object with HTTP response. */
  public static PaymentIntentRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PaymentIntent paymentIntent =
          PaymentIntent.fromJson(JsonUtil.getObject(json, "payment_intent"));

      return new PaymentIntentRetrieveResponse(paymentIntent, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentIntentRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentIntent from the response. */
  public PaymentIntent getPaymentIntent() {
    return paymentIntent;
  }
}
