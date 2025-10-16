package com.chargebee.v4.core.responses.subscription;

import java.util.List;

import com.chargebee.v4.core.models.advanceInvoiceSchedule.AdvanceInvoiceSchedule;

import com.chargebee.v4.core.models.subscription.Subscription;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionRemoveAdvanceInvoiceSchedule operation. Contains the
 * response data from the API.
 */
public final class SubscriptionRemoveAdvanceInvoiceScheduleResponse extends BaseResponse {
  private final Subscription subscription;

  private final List<AdvanceInvoiceSchedule> advanceInvoiceSchedules;

  private SubscriptionRemoveAdvanceInvoiceScheduleResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscription = builder.subscription;

    this.advanceInvoiceSchedules = builder.advanceInvoiceSchedules;
  }

  /** Parse JSON response into SubscriptionRemoveAdvanceInvoiceScheduleResponse object. */
  public static SubscriptionRemoveAdvanceInvoiceScheduleResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionRemoveAdvanceInvoiceScheduleResponse object with HTTP
   * response.
   */
  public static SubscriptionRemoveAdvanceInvoiceScheduleResponse fromJson(
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
          "Failed to parse SubscriptionRemoveAdvanceInvoiceScheduleResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionRemoveAdvanceInvoiceScheduleResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionRemoveAdvanceInvoiceScheduleResponse. */
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

    public SubscriptionRemoveAdvanceInvoiceScheduleResponse build() {
      return new SubscriptionRemoveAdvanceInvoiceScheduleResponse(this);
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
