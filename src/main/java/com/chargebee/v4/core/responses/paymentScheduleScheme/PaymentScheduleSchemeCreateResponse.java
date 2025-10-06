package com.chargebee.v4.core.responses.paymentScheduleScheme;

import com.chargebee.v4.core.models.paymentScheduleScheme.PaymentScheduleScheme;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PaymentScheduleSchemeCreate operation. Contains the response data
 * from the API.
 */
public final class PaymentScheduleSchemeCreateResponse {

  private final PaymentScheduleScheme paymentScheduleScheme;

  private PaymentScheduleSchemeCreateResponse(Builder builder) {

    this.paymentScheduleScheme = builder.paymentScheduleScheme;
  }

  /** Parse JSON response into PaymentScheduleSchemeCreateResponse object. */
  public static PaymentScheduleSchemeCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __paymentScheduleSchemeJson = JsonUtil.getObject(json, "payment_schedule_scheme");
      if (__paymentScheduleSchemeJson != null) {
        builder.paymentScheduleScheme(PaymentScheduleScheme.fromJson(__paymentScheduleSchemeJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentScheduleSchemeCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentScheduleSchemeCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentScheduleSchemeCreateResponse. */
  public static class Builder {

    private PaymentScheduleScheme paymentScheduleScheme;

    private Builder() {}

    public Builder paymentScheduleScheme(PaymentScheduleScheme paymentScheduleScheme) {
      this.paymentScheduleScheme = paymentScheduleScheme;
      return this;
    }

    public PaymentScheduleSchemeCreateResponse build() {
      return new PaymentScheduleSchemeCreateResponse(this);
    }
  }

  /** Get the paymentScheduleScheme from the response. */
  public PaymentScheduleScheme getPaymentScheduleScheme() {
    return paymentScheduleScheme;
  }
}
