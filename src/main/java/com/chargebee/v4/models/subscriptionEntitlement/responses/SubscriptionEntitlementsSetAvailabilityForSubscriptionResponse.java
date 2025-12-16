package com.chargebee.v4.models.subscriptionEntitlement.responses;

import java.util.List;

import com.chargebee.v4.models.subscriptionEntitlement.SubscriptionEntitlement;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for SubscriptionEntitlementsSetAvailabilityForSubscription operation.
 * Contains the response data from the API.
 */
public final class SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse
    extends BaseResponse {
  private final List<SubscriptionEntitlement> list;

  private SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.list = builder.list;
  }

  /**
   * Parse JSON response into SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse object.
   */
  public static SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse fromJson(
      String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse object
   * with HTTP response.
   */
  public static SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse fromJson(
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
          "Failed to parse SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse from JSON",
          e);
    }
  }

  /** Create a new builder for SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse. */
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

    public SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse build() {
      return new SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse(this);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionEntitlement> getList() {
    return list;
  }
}
