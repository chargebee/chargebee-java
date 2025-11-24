package com.chargebee.v4.models.paymentScheduleScheme.responses;

import com.chargebee.v4.models.paymentScheduleScheme.PaymentScheduleScheme;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PaymentScheduleSchemeRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class PaymentScheduleSchemeRetrieveResponse extends BaseResponse {
  private final PaymentScheduleScheme paymentScheduleScheme;

  private PaymentScheduleSchemeRetrieveResponse(
      PaymentScheduleScheme paymentScheduleScheme, Response httpResponse) {
    super(httpResponse);

    this.paymentScheduleScheme = paymentScheduleScheme;
  }

  /** Parse JSON response into PaymentScheduleSchemeRetrieveResponse object. */
  public static PaymentScheduleSchemeRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentScheduleSchemeRetrieveResponse object with HTTP response. */
  public static PaymentScheduleSchemeRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PaymentScheduleScheme paymentScheduleScheme =
          PaymentScheduleScheme.fromJson(JsonUtil.getObject(json, "payment_schedule_scheme"));

      return new PaymentScheduleSchemeRetrieveResponse(paymentScheduleScheme, httpResponse);
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
