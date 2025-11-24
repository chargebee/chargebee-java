package com.chargebee.v4.models.subscription.responses;

import java.util.List;

import com.chargebee.v4.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EditAdvanceInvoiceScheduleForSubscription operation. Contains the
 * response data from the API.
 */
public final class EditAdvanceInvoiceScheduleForSubscriptionResponse extends BaseResponse {
  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private EditAdvanceInvoiceScheduleForSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.advanceInvoiceSchedules = builder.advanceInvoiceSchedules;
  }

  /** Parse JSON response into EditAdvanceInvoiceScheduleForSubscriptionResponse object. */
  public static EditAdvanceInvoiceScheduleForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into EditAdvanceInvoiceScheduleForSubscriptionResponse object with HTTP
   * response.
   */
  public static EditAdvanceInvoiceScheduleForSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.advanceInvoiceSchedules(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "advance_invoice_schedules")).stream()
              .map(AdvanceInvoiceSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EditAdvanceInvoiceScheduleForSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for EditAdvanceInvoiceScheduleForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EditAdvanceInvoiceScheduleForSubscriptionResponse. */
  public static class Builder {

    private List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

    private Response httpResponse;

    private Builder() {}

    public Builder advanceInvoiceSchedules(List<AdvanceInvoiceSchedule> advanceInvoiceSchedules) {
      this.advanceInvoiceSchedules = advanceInvoiceSchedules;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public EditAdvanceInvoiceScheduleForSubscriptionResponse build() {
      return new EditAdvanceInvoiceScheduleForSubscriptionResponse(this);
    }
  }

  /** Get the advanceInvoiceSchedules from the response. */
  public List<AdvanceInvoiceSchedule> getAdvanceInvoiceSchedules() {
    return advanceInvoiceSchedules;
  }
}
