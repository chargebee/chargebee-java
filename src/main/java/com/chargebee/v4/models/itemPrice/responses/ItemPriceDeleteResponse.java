package com.chargebee.v4.models.itemPrice.responses;

import com.chargebee.v4.models.itemPrice.ItemPrice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemPriceDelete operation. Contains the response data from the API.
 */
public final class ItemPriceDeleteResponse extends BaseResponse {
  private final ItemPrice itemPrice;

  private ItemPriceDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemPrice = builder.itemPrice;
  }

  /** Parse JSON response into ItemPriceDeleteResponse object. */
  public static ItemPriceDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemPriceDeleteResponse object with HTTP response. */
  public static ItemPriceDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemPriceJson = JsonUtil.getObject(json, "item_price");
      if (__itemPriceJson != null) {
        builder.itemPrice(ItemPrice.fromJson(__itemPriceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemPriceDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemPriceDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemPriceDeleteResponse. */
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

    public ItemPriceDeleteResponse build() {
      return new ItemPriceDeleteResponse(this);
    }
  }

  /** Get the itemPrice from the response. */
  public ItemPrice getItemPrice() {
    return itemPrice;
  }

  @Override
  public String toString() {
    return "ItemPriceDeleteResponse{" + "itemPrice=" + itemPrice + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemPriceDeleteResponse that = (ItemPriceDeleteResponse) o;
    return java.util.Objects.equals(itemPrice, that.itemPrice);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(itemPrice);
  }
}
