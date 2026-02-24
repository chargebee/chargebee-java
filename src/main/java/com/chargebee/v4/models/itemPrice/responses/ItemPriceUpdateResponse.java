package com.chargebee.v4.models.itemPrice.responses;

import com.chargebee.v4.models.itemPrice.ItemPrice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemPriceUpdate operation. Contains the response data from the API.
 */
public final class ItemPriceUpdateResponse extends BaseResponse {
  private final ItemPrice itemPrice;

  private ItemPriceUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemPrice = builder.itemPrice;
  }

  /** Parse JSON response into ItemPriceUpdateResponse object. */
  public static ItemPriceUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemPriceUpdateResponse object with HTTP response. */
  public static ItemPriceUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __itemPriceObj = JsonUtil.getJsonObject(jsonObj, "item_price");
      if (__itemPriceObj != null) {
        builder.itemPrice(ItemPrice.fromJson(__itemPriceObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemPriceUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemPriceUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemPriceUpdateResponse. */
  public static class Builder {

    private ItemPrice itemPrice;

    private Response httpResponse;

    private Builder() {}

    public Builder itemPrice(ItemPrice itemPrice) {
      this.itemPrice = itemPrice;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ItemPriceUpdateResponse build() {
      return new ItemPriceUpdateResponse(this);
    }
  }

  /** Get the itemPrice from the response. */
  public ItemPrice getItemPrice() {
    return itemPrice;
  }

  @Override
  public String toString() {
    return "ItemPriceUpdateResponse{" + "itemPrice=" + itemPrice + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ItemPriceUpdateResponse that = (ItemPriceUpdateResponse) o;
    return java.util.Objects.equals(itemPrice, that.itemPrice);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(itemPrice);
  }
}
