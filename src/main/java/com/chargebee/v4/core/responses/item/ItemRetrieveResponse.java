package com.chargebee.v4.core.responses.item;

import com.chargebee.v4.core.models.item.Item;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ItemRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class ItemRetrieveResponse {

  private final Item item;

  private ItemRetrieveResponse(Item item) {

    this.item = item;
  }

  /** Parse JSON response into ItemRetrieveResponse object. */
  public static ItemRetrieveResponse fromJson(String json) {
    try {

      Item item = Item.fromJson(JsonUtil.getObject(json, "item"));

      return new ItemRetrieveResponse(item);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemRetrieveResponse from JSON", e);
    }
  }

  /** Get the item from the response. */
  public Item getItem() {
    return item;
  }
}
