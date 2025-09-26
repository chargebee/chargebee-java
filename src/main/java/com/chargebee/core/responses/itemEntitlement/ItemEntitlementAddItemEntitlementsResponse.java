package com.chargebee.core.responses.itemEntitlement;

import com.chargebee.core.models.itemEntitlement.ItemEntitlement;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ItemEntitlementAddItemEntitlements operation. Contains the response
 * data from the API.
 */
public final class ItemEntitlementAddItemEntitlementsResponse {

  private final ItemEntitlement itemEntitlement;

  private ItemEntitlementAddItemEntitlementsResponse(Builder builder) {

    this.itemEntitlement = builder.itemEntitlement;
  }

  /** Parse JSON response into ItemEntitlementAddItemEntitlementsResponse object. */
  public static ItemEntitlementAddItemEntitlementsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __itemEntitlementJson = JsonUtil.getObject(json, "item_entitlement");
      if (__itemEntitlementJson != null) {
        builder.itemEntitlement(ItemEntitlement.fromJson(__itemEntitlementJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemEntitlementAddItemEntitlementsResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemEntitlementAddItemEntitlementsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemEntitlementAddItemEntitlementsResponse. */
  public static class Builder {

    private ItemEntitlement itemEntitlement;

    private Builder() {}

    public Builder itemEntitlement(ItemEntitlement itemEntitlement) {
      this.itemEntitlement = itemEntitlement;
      return this;
    }

    public ItemEntitlementAddItemEntitlementsResponse build() {
      return new ItemEntitlementAddItemEntitlementsResponse(this);
    }
  }

  /** Get the itemEntitlement from the response. */
  public ItemEntitlement getItemEntitlement() {
    return itemEntitlement;
  }
}
