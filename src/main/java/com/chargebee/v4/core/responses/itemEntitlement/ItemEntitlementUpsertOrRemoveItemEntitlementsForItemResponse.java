package com.chargebee.v4.core.responses.itemEntitlement;

import com.chargebee.v4.core.models.itemEntitlement.ItemEntitlement;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ItemEntitlementUpsertOrRemoveItemEntitlementsForItem operation.
 * Contains the response data from the API.
 */
public final class ItemEntitlementUpsertOrRemoveItemEntitlementsForItemResponse {

  private final ItemEntitlement itemEntitlement;

  private ItemEntitlementUpsertOrRemoveItemEntitlementsForItemResponse(Builder builder) {

    this.itemEntitlement = builder.itemEntitlement;
  }

  /**
   * Parse JSON response into ItemEntitlementUpsertOrRemoveItemEntitlementsForItemResponse object.
   */
  public static ItemEntitlementUpsertOrRemoveItemEntitlementsForItemResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __itemEntitlementJson = JsonUtil.getObject(json, "item_entitlement");
      if (__itemEntitlementJson != null) {
        builder.itemEntitlement(ItemEntitlement.fromJson(__itemEntitlementJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemEntitlementUpsertOrRemoveItemEntitlementsForItemResponse from JSON",
          e);
    }
  }

  /** Create a new builder for ItemEntitlementUpsertOrRemoveItemEntitlementsForItemResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemEntitlementUpsertOrRemoveItemEntitlementsForItemResponse. */
  public static class Builder {

    private ItemEntitlement itemEntitlement;

    private Builder() {}

    public Builder itemEntitlement(ItemEntitlement itemEntitlement) {
      this.itemEntitlement = itemEntitlement;
      return this;
    }

    public ItemEntitlementUpsertOrRemoveItemEntitlementsForItemResponse build() {
      return new ItemEntitlementUpsertOrRemoveItemEntitlementsForItemResponse(this);
    }
  }

  /** Get the itemEntitlement from the response. */
  public ItemEntitlement getItemEntitlement() {
    return itemEntitlement;
  }
}
