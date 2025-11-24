package com.chargebee.v4.models.subscription.responses;

import com.chargebee.v4.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for RetrieveAdvanceInvoiceScheduleForSubscription operation. Contains
 * the response data from a single resource get operation.
 */
public final class RetrieveAdvanceInvoiceScheduleForSubscriptionResponse extends BaseResponse {
  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private RetrieveAdvanceInvoiceScheduleForSubscriptionResponse(
      List<AdvanceInvoiceSchedule> advanceInvoiceSchedules, Response httpResponse) {
    super(httpResponse);

    this.advanceInvoiceSchedules = advanceInvoiceSchedules;
  }

  /** Parse JSON response into RetrieveAdvanceInvoiceScheduleForSubscriptionResponse object. */
  public static RetrieveAdvanceInvoiceScheduleForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into RetrieveAdvanceInvoiceScheduleForSubscriptionResponse object with HTTP
   * response.
   */
  public static RetrieveAdvanceInvoiceScheduleForSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {

      List<AdvanceInvoiceSchedule> advanceInvoiceSchedules =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "advance_invoice_schedules")).stream()
              .map(AdvanceInvoiceSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new RetrieveAdvanceInvoiceScheduleForSubscriptionResponse(
          advanceInvoiceSchedules, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse RetrieveAdvanceInvoiceScheduleForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the advanceInvoiceSchedules from the response. */
  public List<AdvanceInvoiceSchedule> getAdvanceInvoiceSchedules() {
    return advanceInvoiceSchedules;
  }
}
