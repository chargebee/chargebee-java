package com.chargebee.core.responses.entitlement;

import com.chargebee.core.models.entitlement.Entitlement;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for EntitlementCreate operation. Contains the response data from the
 * API.
 */
public final class EntitlementCreateResponse {

  private final Entitlement entitlement;

  private EntitlementCreateResponse(Builder builder) {

    this.entitlement = builder.entitlement;
  }

  /** Parse JSON response into EntitlementCreateResponse object. */
  public static EntitlementCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __entitlementJson = JsonUtil.getObject(json, "entitlement");
      if (__entitlementJson != null) {
        builder.entitlement(Entitlement.fromJson(__entitlementJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EntitlementCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for EntitlementCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EntitlementCreateResponse. */
  public static class Builder {

    private Entitlement entitlement;

    private Builder() {}

    public Builder entitlement(Entitlement entitlement) {
      this.entitlement = entitlement;
      return this;
    }

    public EntitlementCreateResponse build() {
      return new EntitlementCreateResponse(this);
    }
  }

  /** Get the entitlement from the response. */
  public Entitlement getEntitlement() {
    return entitlement;
  }
}
