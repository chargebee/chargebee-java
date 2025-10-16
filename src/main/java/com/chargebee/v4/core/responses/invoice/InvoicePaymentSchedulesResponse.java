package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.paymentSchedule.PaymentSchedule;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for InvoicePaymentSchedules operation. Contains the response data from
 * a single resource get operation.
 */
public final class InvoicePaymentSchedulesResponse extends BaseResponse {
  private final List<PaymentSchedule> paymentSchedules;

  private InvoicePaymentSchedulesResponse(
      List<PaymentSchedule> paymentSchedules, Response httpResponse) {
    super(httpResponse);

    this.paymentSchedules = paymentSchedules;
  }

  /** Parse JSON response into InvoicePaymentSchedulesResponse object. */
  public static InvoicePaymentSchedulesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoicePaymentSchedulesResponse object with HTTP response. */
  public static InvoicePaymentSchedulesResponse fromJson(String json, Response httpResponse) {
    try {

      List<PaymentSchedule> paymentSchedules =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "payment_schedules")).stream()
              .map(PaymentSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new InvoicePaymentSchedulesResponse(paymentSchedules, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoicePaymentSchedulesResponse from JSON", e);
    }
  }

  /** Get the paymentSchedules from the response. */
  public List<PaymentSchedule> getPaymentSchedules() {
    return paymentSchedules;
  }
}
