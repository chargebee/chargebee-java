package com.chargebee.core.responses.paymentIntent;

import com.chargebee.core.models.paymentIntent.PaymentIntent;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PaymentIntentCreate operation. Contains the response data from the
 * API.
 */
public final class PaymentIntentCreateResponse {

  private final PaymentIntent paymentIntent;

  private PaymentIntentCreateResponse(Builder builder) {

    this.paymentIntent = builder.paymentIntent;
  }

  /** Parse JSON response into PaymentIntentCreateResponse object. */
  public static PaymentIntentCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __paymentIntentJson = JsonUtil.getObject(json, "payment_intent");
      if (__paymentIntentJson != null) {
        builder.paymentIntent(PaymentIntent.fromJson(__paymentIntentJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentIntentCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentIntentCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentIntentCreateResponse. */
  public static class Builder {

    private PaymentIntent paymentIntent;

    private Builder() {}

    public Builder paymentIntent(PaymentIntent paymentIntent) {
      this.paymentIntent = paymentIntent;
      return this;
    }

    public PaymentIntentCreateResponse build() {
      return new PaymentIntentCreateResponse(this);
    }
  }

  /** Get the paymentIntent from the response. */
  public PaymentIntent getPaymentIntent() {
    return paymentIntent;
  }
}
