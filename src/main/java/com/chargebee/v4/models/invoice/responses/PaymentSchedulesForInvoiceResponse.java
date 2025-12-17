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

  private PaymentSchedulesForInvoiceResponse(Builder builder) {
    super(builder.httpResponse);

    this.paymentSchedules = builder.paymentSchedules;
  }

  /** Parse JSON response into PaymentSchedulesForInvoiceResponse object. */
  public static PaymentSchedulesForInvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PaymentSchedulesForInvoiceResponse object with HTTP response. */
  public static PaymentSchedulesForInvoiceResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __paymentSchedulesJson = JsonUtil.getArray(json, "payment_schedules");
      if (__paymentSchedulesJson != null) {
        builder.paymentSchedules(
            JsonUtil.parseObjectArray(__paymentSchedulesJson).stream()
                .map(PaymentSchedule::fromJson)
                .collect(java.util.stream.Collectors.toList()));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSchedulesForInvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for PaymentSchedulesForInvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PaymentSchedulesForInvoiceResponse. */
  public static class Builder {

    private List<PaymentSchedule> paymentSchedules;

    private Response httpResponse;

    private Builder() {}

    public Builder paymentSchedules(List<PaymentSchedule> paymentSchedules) {
      this.paymentSchedules = paymentSchedules;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PaymentSchedulesForInvoiceResponse build() {
      return new PaymentSchedulesForInvoiceResponse(this);
    }
  }

  /** Get the paymentSchedules from the response. */
  public List<PaymentSchedule> getPaymentSchedules() {
    return paymentSchedules;
  }
}
