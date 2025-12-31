package com.chargebee.v4.models.paymentIntent.responses;

import com.chargebee.v4.models.paymentIntent.PaymentIntent;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentIntentUpdate operation. Contains the response data from the
 * API.
 */
public final class PaymentIntentUpdateResponse extends BaseResponse {
  private final PaymentIntent paymentIntent;

  private PaymentIntentUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.paymentIntent = builder.paymentIntent;
  }

  /** Parse JSON response into PaymentIntentUpdateResponse object. */
  public static PaymentIntentUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentIntentUpdateResponse object with HTTP response. */
  public static PaymentIntentUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __paymentIntentJson = JsonUtil.getObject(json, "payment_intent");
      if (__paymentIntentJson != null) {
        builder.paymentIntent(PaymentIntent.fromJson(__paymentIntentJson));
      }

      builder.httpResponse(httpResponse);
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

    public PaymentIntentUpdateResponse build() {
      return new PaymentIntentUpdateResponse(this);
    }
  }

  /** Get the paymentIntent from the response. */
  public PaymentIntent getPaymentIntent() {
    return paymentIntent;
  }

  @Override
  public String toString() {
    return "PaymentIntentUpdateResponse{" + "paymentIntent=" + paymentIntent + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PaymentIntentUpdateResponse that = (PaymentIntentUpdateResponse) o;
    return java.util.Objects.equals(paymentIntent, that.paymentIntent);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(paymentIntent);
  }
}
