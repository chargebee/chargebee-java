package com.chargebee.core.responses.subscriptionEntitlement;

import com.chargebee.core.models.subscriptionEntitlement.SubscriptionEntitlement;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionEntitlementSetSubscriptionEntitlementAvailability
 * operation. Contains the response data from the API.
 */
public final class SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse {

  private final SubscriptionEntitlement subscriptionEntitlement;

  private SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse(Builder builder) {

    this.subscriptionEntitlement = builder.subscriptionEntitlement;
  }

  /**
   * Parse JSON response into SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse
   * object.
   */
  public static SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse fromJson(
      String json) {
    try {
      Builder builder = builder();

      String __subscriptionEntitlementJson = JsonUtil.getObject(json, "subscription_entitlement");
      if (__subscriptionEntitlementJson != null) {
        builder.subscriptionEntitlement(
            SubscriptionEntitlement.fromJson(__subscriptionEntitlementJson));
      }

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

    private SubscriptionEntitlement subscriptionEntitlement;

    private Builder() {}

    public Builder subscriptionEntitlement(SubscriptionEntitlement subscriptionEntitlement) {
      this.subscriptionEntitlement = subscriptionEntitlement;
      return this;
    }

    public SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse build() {
      return new SubscriptionEntitlementSetSubscriptionEntitlementAvailabilityResponse(this);
    }
  }

  /** Get the subscriptionEntitlement from the response. */
  public SubscriptionEntitlement getSubscriptionEntitlement() {
    return subscriptionEntitlement;
  }
}
