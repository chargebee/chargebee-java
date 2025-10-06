package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.internal.JsonUtil;
import java.util.List;

/**
 * Immutable response object for SubscriptionRetrieveAdvanceInvoiceSchedule operation. Contains the
 * response data from a single resource get operation.
 */
public final class SubscriptionRetrieveAdvanceInvoiceScheduleResponse {

  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private SubscriptionRetrieveAdvanceInvoiceScheduleResponse(
      List<AdvanceInvoiceSchedule> advanceInvoiceSchedules) {

    this.advanceInvoiceSchedules = advanceInvoiceSchedules;
  }

  /** Parse JSON response into SubscriptionRetrieveAdvanceInvoiceScheduleResponse object. */
  public static SubscriptionRetrieveAdvanceInvoiceScheduleResponse fromJson(String json) {
    try {

      List<AdvanceInvoiceSchedule> advanceInvoiceSchedules =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "advance_invoice_schedules")).stream()
              .map(AdvanceInvoiceSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new SubscriptionRetrieveAdvanceInvoiceScheduleResponse(advanceInvoiceSchedules);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionRetrieveAdvanceInvoiceScheduleResponse from JSON", e);
    }
  }

  /** Get the advanceInvoiceSchedules from the response. */
  public List<AdvanceInvoiceSchedule> getAdvanceInvoiceSchedules() {
    return advanceInvoiceSchedules;
  }
}
