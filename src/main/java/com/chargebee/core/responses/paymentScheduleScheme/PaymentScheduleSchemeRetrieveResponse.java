package com.chargebee.core.responses.paymentScheduleScheme;

import com.chargebee.core.models.paymentScheduleScheme.PaymentScheduleScheme;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PaymentScheduleSchemeRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class PaymentScheduleSchemeRetrieveResponse {

  private final PaymentScheduleScheme paymentScheduleScheme;

  private PaymentScheduleSchemeRetrieveResponse(PaymentScheduleScheme paymentScheduleScheme) {

    this.paymentScheduleScheme = paymentScheduleScheme;
  }

  /** Parse JSON response into PaymentScheduleSchemeRetrieveResponse object. */
  public static PaymentScheduleSchemeRetrieveResponse fromJson(String json) {
    try {

      PaymentScheduleScheme paymentScheduleScheme =
          PaymentScheduleScheme.fromJson(JsonUtil.getObject(json, "payment_schedule_scheme"));

      return new PaymentScheduleSchemeRetrieveResponse(paymentScheduleScheme);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse PaymentScheduleSchemeRetrieveResponse from JSON", e);
    }
  }

  /** Get the paymentScheduleScheme from the response. */
  public PaymentScheduleScheme getPaymentScheduleScheme() {
    return paymentScheduleScheme;
  }
}
