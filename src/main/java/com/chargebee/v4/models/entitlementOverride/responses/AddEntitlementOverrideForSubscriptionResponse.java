package com.chargebee.v4.models.entitlementOverride.responses;

import com.chargebee.v4.models.entitlementOverride.EntitlementOverride;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AddEntitlementOverrideForSubscription operation. Contains the
 * response data from the API.
 */
public final class AddEntitlementOverrideForSubscriptionResponse extends BaseResponse {
  private final EntitlementOverride entitlementOverride;

  private AddEntitlementOverrideForSubscriptionResponse(Builder builder) {
    super(builder.httpResponse);

    this.entitlementOverride = builder.entitlementOverride;
  }

  /** Parse JSON response into AddEntitlementOverrideForSubscriptionResponse object. */
  public static AddEntitlementOverrideForSubscriptionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into AddEntitlementOverrideForSubscriptionResponse object with HTTP
   * response.
   */
  public static AddEntitlementOverrideForSubscriptionResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __entitlementOverrideJson = JsonUtil.getObject(json, "entitlement_override");
      if (__entitlementOverrideJson != null) {
        builder.entitlementOverride(EntitlementOverride.fromJson(__entitlementOverrideJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse AddEntitlementOverrideForSubscriptionResponse from JSON", e);
    }
  }

  /** Create a new builder for AddEntitlementOverrideForSubscriptionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AddEntitlementOverrideForSubscriptionResponse. */
  public static class Builder {

    private EntitlementOverride entitlementOverride;

    private Response httpResponse;

    private Builder() {}

    public Builder entitlementOverride(EntitlementOverride entitlementOverride) {
      this.entitlementOverride = entitlementOverride;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public AddEntitlementOverrideForSubscriptionResponse build() {
      return new AddEntitlementOverrideForSubscriptionResponse(this);
    }
  }

  /** Get the entitlementOverride from the response. */
  public EntitlementOverride getEntitlementOverride() {
    return entitlementOverride;
  }
}
