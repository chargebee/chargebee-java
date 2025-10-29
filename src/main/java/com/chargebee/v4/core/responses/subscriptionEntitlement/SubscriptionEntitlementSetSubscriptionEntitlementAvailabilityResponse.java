package com.chargebee.v4.core.responses.subscriptionEntitlement;

import java.util.List;

import com.chargebee.v4.core.models.subscriptionEntitlement.SubscriptionEntitlement;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionEntitlementSetSubscriptionEntitlementAvailability
 * operation. Contains the response data from the API.
 */
public final class SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse
    extends BaseResponse {
  private final List<SubscriptionEntitlement> list;

  private SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse(Builder builder) {
    super(builder.httpResponse);

    this.list = builder.list;
  }

  /**
   * Parse JSON response into SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse
   * object.
   */
  public static SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse fromJson(
      String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse
   * object with HTTP response.
   */
  public static SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.list(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionEntitlement::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse from JSON",
          e);
    }
  }

  /**
   * Create a new builder for SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse.
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse. */
  public static class Builder {

    private List<SubscriptionEntitlement> list;

    private Response httpResponse;

    private Builder() {}

    public Builder list(List<SubscriptionEntitlement> list) {
      this.list = list;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse build() {
      return new SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse(this);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionEntitlement> getList() {
    return list;
  }
}
