package com.chargebee.core.responses.itemFamily;

import com.chargebee.core.models.itemFamily.ItemFamily;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ItemFamilyRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class ItemFamilyRetrieveResponse {

  private final ItemFamily itemFamily;

  private ItemFamilyRetrieveResponse(ItemFamily itemFamily) {

    this.itemFamily = itemFamily;
  }

  /** Parse JSON response into ItemFamilyRetrieveResponse object. */
  public static ItemFamilyRetrieveResponse fromJson(String json) {
    try {

      ItemFamily itemFamily = ItemFamily.fromJson(JsonUtil.getObject(json, "item_family"));

      return new ItemFamilyRetrieveResponse(itemFamily);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyRetrieveResponse from JSON", e);
    }
  }

  /** Get the itemFamily from the response. */
  public ItemFamily getItemFamily() {
    return itemFamily;
  }
}
