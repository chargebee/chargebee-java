package com.chargebee.v4.core.responses.paymentIntent;

import com.chargebee.v4.core.models.paymentIntent.PaymentIntent;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentIntentCreate operation. Contains the response data from the
 * API.
 */
public final class PaymentIntentCreateResponse extends BaseResponse {
  private final PaymentIntent paymentIntent;

  private PaymentIntentCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.paymentIntent = builder.paymentIntent;
  }

  /** Parse JSON response into PaymentIntentCreateResponse object. */
  public static PaymentIntentCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentIntentCreateResponse object with HTTP response. */
  public static PaymentIntentCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __paymentIntentJson = JsonUtil.getObject(json, "payment_intent");
      if (__paymentIntentJson != null) {
        builder.paymentIntent(PaymentIntent.fromJson(__paymentIntentJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder paymentIntent(PaymentIntent paymentIntent) {
      this.paymentIntent = paymentIntent;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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
