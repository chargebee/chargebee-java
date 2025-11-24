package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.paymentSchedule.PaymentSchedule;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for PaymentSchedulesForInvoice operation. Contains the response data
 * from a single resource get operation.
 */
public final class PaymentSchedulesForInvoiceResponse extends BaseResponse {
  private final List<PaymentSchedule> paymentSchedules;

  private PaymentSchedulesForInvoiceResponse(
      List<PaymentSchedule> paymentSchedules, Response httpResponse) {
    super(httpResponse);

    this.paymentSchedules = paymentSchedules;
  }

  /** Parse JSON response into PaymentSchedulesForInvoiceResponse object. */
  public static PaymentSchedulesForInvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentSchedulesForInvoiceResponse object with HTTP response. */
  public static PaymentSchedulesForInvoiceResponse fromJson(String json, Response httpResponse) {
    try {

      List<PaymentSchedule> paymentSchedules =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "payment_schedules")).stream()
              .map(PaymentSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new PaymentSchedulesForInvoiceResponse(paymentSchedules, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSchedulesForInvoiceResponse from JSON", e);
    }
  }

  /** Get the paymentSchedules from the response. */
  public List<PaymentSchedule> getPaymentSchedules() {
    return paymentSchedules;
  }
}
