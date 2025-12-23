package com.chargebee.v4.models.subscriptionEntitlement.responses;

import java.util.List;

import com.chargebee.v4.models.subscriptionEntitlement.SubscriptionEntitlement;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SetSubscriptionEntitlementAvailability operation. Contains the
 * response data from the API.
 */
public final class SetSubscriptionEntitlementAvailabilityResponse extends BaseResponse {
  private final List<SubscriptionEntitlement> list;

  private SetSubscriptionEntitlementAvailabilityResponse(Builder builder) {
    super(builder.httpResponse);

    this.list = builder.list;
  }

  /** Parse JSON response into SetSubscriptionEntitlementAvailabilityResponse object. */
  public static SetSubscriptionEntitlementAvailabilityResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SetSubscriptionEntitlementAvailabilityResponse object with HTTP
   * response.
   */
  public static SetSubscriptionEntitlementAvailabilityResponse fromJson(
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
          "Failed to parse SetSubscriptionEntitlementAvailabilityResponse from JSON", e);
    }
  }

  /** Create a new builder for SetSubscriptionEntitlementAvailabilityResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SetSubscriptionEntitlementAvailabilityResponse. */
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

    public SetSubscriptionEntitlementAvailabilityResponse build() {
      return new SetSubscriptionEntitlementAvailabilityResponse(this);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionEntitlement> getList() {
    return list;
  }
}
