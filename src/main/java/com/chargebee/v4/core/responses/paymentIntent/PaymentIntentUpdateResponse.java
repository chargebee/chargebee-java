package com.chargebee.v4.core.responses.paymentIntent;

import com.chargebee.v4.core.models.paymentIntent.PaymentIntent;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PaymentIntentUpdate operation. Contains the response data from the
 * API.
 */
public final class PaymentIntentUpdateResponse {

  private final PaymentIntent paymentIntent;

  private PaymentIntentUpdateResponse(Builder builder) {

    this.paymentIntent = builder.paymentIntent;
  }

  /** Parse JSON response into PaymentIntentUpdateResponse object. */
  public static PaymentIntentUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __paymentIntentJson = JsonUtil.getObject(json, "payment_intent");
      if (__paymentIntentJson != null) {
        builder.paymentIntent(PaymentIntent.fromJson(__paymentIntentJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentIntentUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentIntentUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentIntentUpdateResponse. */
  public static class Builder {

    private PaymentIntent paymentIntent;

    private Builder() {}

    public Builder paymentIntent(PaymentIntent paymentIntent) {
      this.paymentIntent = paymentIntent;
      return this;
    }

    public PaymentIntentUpdateResponse build() {
      return new PaymentIntentUpdateResponse(this);
    }
  }

  /** Get the paymentIntent from the response. */
  public PaymentIntent getPaymentIntent() {
    return paymentIntent;
  }
}
