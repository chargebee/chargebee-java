package com.chargebee.core.responses.entitlementOverride;

import com.chargebee.core.models.entitlementOverride.EntitlementOverride;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EntitlementOverrideAddEntitlementOverrideForSubscription operation.
 * Contains the response data from the API.
 */
public final class EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse {

  private final EntitlementOverride entitlementOverride;

  private EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse(Builder builder) {

    this.entitlementOverride = builder.entitlementOverride;
  }

  /**
   * Parse JSON response into EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse
   * object.
   */
  public static EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse fromJson(
      String json) {
    try {
      Builder builder = builder();

      String __entitlementOverrideJson = JsonUtil.getObject(json, "entitlement_override");
      if (__entitlementOverrideJson != null) {
        builder.entitlementOverride(EntitlementOverride.fromJson(__entitlementOverrideJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse from JSON",
          e);
    }
  }

  /** Create a new builder for EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse. */
  public static class Builder {

    private EntitlementOverride entitlementOverride;

    private Builder() {}

    public Builder entitlementOverride(EntitlementOverride entitlementOverride) {
      this.entitlementOverride = entitlementOverride;
      return this;
    }

    public EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse build() {
      return new EntitlementOverrideAddEntitlementOverrideForSubscriptionResponse(this);
    }
  }

  /** Get the entitlementOverride from the response. */
  public EntitlementOverride getEntitlementOverride() {
    return entitlementOverride;
  }
}
