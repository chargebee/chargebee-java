package com.chargebee.v4.models.subscriptionEntitlement.responses;

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
  private final SubscriptionEntitlement subscriptionEntitlement;

  private SubscriptionEntitlementsSetAvailabilityForSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscriptionEntitlement = builder.subscriptionEntitlement;
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

      String __subscriptionEntitlementJson = JsonUtil.getObject(json, "subscription_entitlement");
      if (__subscriptionEntitlementJson != null) {
        builder.subscriptionEntitlement(
            SubscriptionEntitlement.fromJson(__subscriptionEntitlementJson));
      }

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

    private SubscriptionEntitlement subscriptionEntitlement;

    private Response httpResponse;

    private Builder() {}

    public Builder subscriptionEntitlement(SubscriptionEntitlement subscriptionEntitlement) {
      this.subscriptionEntitlement = subscriptionEntitlement;
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

  /** Get the subscriptionEntitlement from the response. */
  public SubscriptionEntitlement getSubscriptionEntitlement() {
    return subscriptionEntitlement;
  }
}
