package com.chargebee.v4.models.subscription.responses;

import java.util.List;

import com.chargebee.v4.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.models.subscription.Subscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RemoveAdvanceInvoiceScheduleForSubscription operation. Contains the
 * response data from the API.
 */
public final class RemoveAdvanceInvoiceScheduleForSubscriptionResponse extends BaseResponse {
  private final Subscription subscription;

  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private RemoveAdvanceInvoiceScheduleForSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscription = builder.subscription;

    this.advanceInvoiceSchedules = builder.advanceInvoiceSchedules;
  }

  /** Parse JSON response into RemoveAdvanceInvoiceScheduleForSubscriptionResponse object. */
  public static RemoveAdvanceInvoiceScheduleForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into RemoveAdvanceInvoiceScheduleForSubscriptionResponse object with HTTP
   * response.
   */
  public static RemoveAdvanceInvoiceScheduleForSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        builder.subscription(Subscription.fromJson(__subscriptionJson));
      }

      builder.advanceInvoiceSchedules(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "advance_invoice_schedules")).stream()
              .map(AdvanceInvoiceSchedule::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse RemoveAdvanceInvoiceScheduleForSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for RemoveAdvanceInvoiceScheduleForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RemoveAdvanceInvoiceScheduleForSubscriptionResponse. */
  public static class Builder {

    private Subscription subscription;

    private List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

    private Response httpResponse;

    private Builder() {}

    public Builder subscription(Subscription subscription) {
      this.subscription = subscription;
      return this;
    }

    public Builder advanceInvoiceSchedules(List<AdvanceInvoiceSchedule> advanceInvoiceSchedules) {
      this.advanceInvoiceSchedules = advanceInvoiceSchedules;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public RemoveAdvanceInvoiceScheduleForSubscriptionResponse build() {
      return new RemoveAdvanceInvoiceScheduleForSubscriptionResponse(this);
    }
  }

  /** Get the subscription from the response. */
  public Subscription getSubscription() {
    return subscription;
  }

  /** Get the advanceInvoiceSchedules from the response. */
  public List<AdvanceInvoiceSchedule> getAdvanceInvoiceSchedules() {
    return advanceInvoiceSchedules;
  }
}
