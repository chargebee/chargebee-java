package com.chargebee.v4.models.item.responses;

import com.chargebee.v4.models.item.Item;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class ItemRetrieveResponse extends BaseResponse {
  private final Item item;

  private ItemRetrieveResponse(Item item, Response httpResponse) {
    super(httpResponse);

    this.item = item;
  }

  /** Parse JSON response into ItemRetrieveResponse object. */
  public static ItemRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemRetrieveResponse object with HTTP response. */
  public static ItemRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Item item = Item.fromJson(JsonUtil.getObject(json, "item"));

      return new ItemRetrieveResponse(item, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemRetrieveResponse from JSON", e);
    }
  }

  /** Get the item from the response. */
  public Item getItem() {
    return item;
  }
}
