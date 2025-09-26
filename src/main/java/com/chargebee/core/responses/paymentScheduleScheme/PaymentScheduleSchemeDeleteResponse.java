package com.chargebee.core.responses.paymentScheduleScheme;

import com.chargebee.core.models.paymentScheduleScheme.PaymentScheduleScheme;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PaymentScheduleSchemeDelete operation. Contains the response data
 * from the API.
 */
public final class PaymentScheduleSchemeDeleteResponse {

  private final PaymentScheduleScheme paymentScheduleScheme;

  private PaymentScheduleSchemeDeleteResponse(Builder builder) {

    this.paymentScheduleScheme = builder.paymentScheduleScheme;
  }

  /** Parse JSON response into PaymentScheduleSchemeDeleteResponse object. */
  public static PaymentScheduleSchemeDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __paymentScheduleSchemeJson = JsonUtil.getObject(json, "payment_schedule_scheme");
      if (__paymentScheduleSchemeJson != null) {
        builder.paymentScheduleScheme(PaymentScheduleScheme.fromJson(__paymentScheduleSchemeJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentScheduleSchemeDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentScheduleSchemeDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentScheduleSchemeDeleteResponse. */
  public static class Builder {

    private PaymentScheduleScheme paymentScheduleScheme;

    private Builder() {}

    public Builder paymentScheduleScheme(PaymentScheduleScheme paymentScheduleScheme) {
      this.paymentScheduleScheme = paymentScheduleScheme;
      return this;
    }

    public PaymentScheduleSchemeDeleteResponse build() {
      return new PaymentScheduleSchemeDeleteResponse(this);
    }
  }

  /** Get the paymentScheduleScheme from the response. */
  public PaymentScheduleScheme getPaymentScheduleScheme() {
    return paymentScheduleScheme;
  }
}
