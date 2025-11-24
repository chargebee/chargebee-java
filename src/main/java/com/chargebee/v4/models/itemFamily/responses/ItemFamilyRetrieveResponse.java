package com.chargebee.v4.models.itemFamily.responses;

import com.chargebee.v4.models.itemFamily.ItemFamily;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemFamilyRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class ItemFamilyRetrieveResponse extends BaseResponse {
  private final ItemFamily itemFamily;

  private ItemFamilyRetrieveResponse(ItemFamily itemFamily, Response httpResponse) {
    super(httpResponse);

    this.itemFamily = itemFamily;
  }

  /** Parse JSON response into ItemFamilyRetrieveResponse object. */
  public static ItemFamilyRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemFamilyRetrieveResponse object with HTTP response. */
  public static ItemFamilyRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      ItemFamily itemFamily = ItemFamily.fromJson(JsonUtil.getObject(json, "item_family"));

      return new ItemFamilyRetrieveResponse(itemFamily, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyRetrieveResponse from JSON", e);
    }
  }

  /** Get the itemFamily from the response. */
  public ItemFamily getItemFamily() {
    return itemFamily;
  }
}
