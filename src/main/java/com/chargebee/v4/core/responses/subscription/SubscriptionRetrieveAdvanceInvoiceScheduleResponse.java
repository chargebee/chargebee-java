package com.chargebee.v4.core.responses.subscription;

import com.chargebee.v4.core.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for SubscriptionRetrieveAdvanceInvoiceSchedule operation. Contains the
 * response data from a single resource get operation.
 */
public final class SubscriptionRetrieveAdvanceInvoiceScheduleResponse extends BaseResponse {
  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private SubscriptionRetrieveAdvanceInvoiceScheduleResponse(
      List<AdvanceInvoiceSchedule> advanceInvoiceSchedules, Response httpResponse) {
    super(httpResponse);

    this.advanceInvoiceSchedules = advanceInvoiceSchedules;
  }

  /** Parse JSON response into SubscriptionRetrieveAdvanceInvoiceScheduleResponse object. */
  public static SubscriptionRetrieveAdvanceInvoiceScheduleResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionRetrieveAdvanceInvoiceScheduleResponse object with HTTP
   * response.
   */
  public static SubscriptionRetrieveAdvanceInvoiceScheduleResponse fromJson(
      String json, Response httpResponse) {
    try {

      List<AdvanceInvoiceSchedule> advanceInvoiceSchedules =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "advance_invoice_schedules")).stream()
              .map(AdvanceInvoiceSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new SubscriptionRetrieveAdvanceInvoiceScheduleResponse(
          advanceInvoiceSchedules, httpResponse);
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
