package com.chargebee.v4.models.subscription.responses;

import com.chargebee.v4.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for SubscriptionRetrieveAdvanceInvoiceSchedule operation. Contains the
 * response data from a single resource get operation.
 */
public final class SubscriptionRetrieveAdvanceInvoiceScheduleResponse extends BaseResponse {
  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private SubscriptionRetrieveAdvanceInvoiceScheduleResponse(Builder builder) {
    super(builder.httpResponse);

    this.advanceInvoiceSchedules = builder.advanceInvoiceSchedules;
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
      Builder builder = builder();

      String __advanceInvoiceSchedulesJson = JsonUtil.getArray(json, "advance_invoice_schedules");
      if (__advanceInvoiceSchedulesJson != null) {
        builder.advanceInvoiceSchedules(
            JsonUtil.parseObjectArray(__advanceInvoiceSchedulesJson).stream()
                .map(AdvanceInvoiceSchedule::fromJson)
                .collect(java.util.stream.Collectors.toList()));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionRetrieveAdvanceInvoiceScheduleResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionRetrieveAdvanceInvoiceScheduleResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionRetrieveAdvanceInvoiceScheduleResponse. */
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

    public SubscriptionRetrieveAdvanceInvoiceScheduleResponse build() {
      return new SubscriptionRetrieveAdvanceInvoiceScheduleResponse(this);
    }
  }

  /** Get the advanceInvoiceSchedules from the response. */
  public List<AdvanceInvoiceSchedule> getAdvanceInvoiceSchedules() {
    return advanceInvoiceSchedules;
  }

  @Override
  public String toString() {
    return "SubscriptionRetrieveAdvanceInvoiceScheduleResponse{"
        + "advanceInvoiceSchedules="
        + advanceInvoiceSchedules
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SubscriptionRetrieveAdvanceInvoiceScheduleResponse that =
        (SubscriptionRetrieveAdvanceInvoiceScheduleResponse) o;
    return java.util.Objects.equals(advanceInvoiceSchedules, that.advanceInvoiceSchedules);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(advanceInvoiceSchedules);
  }
}
