package com.chargebee.v4.core.responses.itemPrice;

import com.chargebee.v4.core.models.itemPrice.ItemPrice;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemPriceRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class ItemPriceRetrieveResponse extends BaseResponse {
  private final ItemPrice itemPrice;

  private ItemPriceRetrieveResponse(ItemPrice itemPrice, Response httpResponse) {
    super(httpResponse);

    this.itemPrice = itemPrice;
  }

  /** Parse JSON response into ItemPriceRetrieveResponse object. */
  public static ItemPriceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemPriceRetrieveResponse object with HTTP response. */
  public static ItemPriceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      ItemPrice itemPrice = ItemPrice.fromJson(JsonUtil.getObject(json, "item_price"));

      return new ItemPriceRetrieveResponse(itemPrice, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemPriceRetrieveResponse from JSON", e);
    }
  }

  /** Get the itemPrice from the response. */
  public ItemPrice getItemPrice() {
    return itemPrice;
  }
}
