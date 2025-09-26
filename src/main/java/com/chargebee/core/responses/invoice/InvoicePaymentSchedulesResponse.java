package com.chargebee.core.responses.invoice;

import com.chargebee.core.models.paymentSchedule.PaymentSchedule;

import com.chargebee.internal.JsonUtil;
import java.util.List;

/**
 * Immutable response object for InvoicePaymentSchedules operation. Contains the response data from
 * a single resource get operation.
 */
public final class InvoicePaymentSchedulesResponse {

  private final List<PaymentSchedule> paymentSchedules;

  private InvoicePaymentSchedulesResponse(List<PaymentSchedule> paymentSchedules) {

    this.paymentSchedules = paymentSchedules;
  }

  /** Parse JSON response into InvoicePaymentSchedulesResponse object. */
  public static InvoicePaymentSchedulesResponse fromJson(String json) {
    try {

      List<PaymentSchedule> paymentSchedules =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "payment_schedules")).stream()
              .map(PaymentSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new InvoicePaymentSchedulesResponse(paymentSchedules);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoicePaymentSchedulesResponse from JSON", e);
    }
  }

  /** Get the paymentSchedules from the response. */
  public List<PaymentSchedule> getPaymentSchedules() {
    return paymentSchedules;
  }
}
