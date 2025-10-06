package com.chargebee.v4.core.responses.subscription;

import java.util.List;

import com.chargebee.v4.core.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionEditAdvanceInvoiceSchedule operation. Contains the
 * response data from the API.
 */
public final class SubscriptionEditAdvanceInvoiceScheduleResponse {

  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private SubscriptionEditAdvanceInvoiceScheduleResponse(Builder builder) {

    this.advanceInvoiceSchedules = builder.advanceInvoiceSchedules;
  }

  /** Parse JSON response into SubscriptionEditAdvanceInvoiceScheduleResponse object. */
  public static SubscriptionEditAdvanceInvoiceScheduleResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.advanceInvoiceSchedules(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "advance_invoice_schedules")).stream()
              .map(AdvanceInvoiceSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionEditAdvanceInvoiceScheduleResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionEditAdvanceInvoiceScheduleResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionEditAdvanceInvoiceScheduleResponse. */
  public static class Builder {

    private List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

    private Builder() {}

    public Builder advanceInvoiceSchedules(List<AdvanceInvoiceSchedule> advanceInvoiceSchedules) {
      this.advanceInvoiceSchedules = advanceInvoiceSchedules;
      return this;
    }

    public SubscriptionEditAdvanceInvoiceScheduleResponse build() {
      return new SubscriptionEditAdvanceInvoiceScheduleResponse(this);
    }
  }

  /** Get the advanceInvoiceSchedules from the response. */
  public List<AdvanceInvoiceSchedule> getAdvanceInvoiceSchedules() {
    return advanceInvoiceSchedules;
  }
}
