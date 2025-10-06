package com.chargebee.v4.core.responses.itemPrice;

import com.chargebee.v4.core.models.itemPrice.ItemPrice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ItemPriceUpdate operation. Contains the response data from the API.
 */
public final class ItemPriceUpdateResponse {

  private final ItemPrice itemPrice;

  private ItemPriceUpdateResponse(Builder builder) {

    this.itemPrice = builder.itemPrice;
  }

  /** Parse JSON response into ItemPriceUpdateResponse object. */
  public static ItemPriceUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __itemPriceJson = JsonUtil.getObject(json, "item_price");
      if (__itemPriceJson != null) {
        builder.itemPrice(ItemPrice.fromJson(__itemPriceJson));
      }

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

    private Builder() {}

    public Builder itemPrice(ItemPrice itemPrice) {
      this.itemPrice = itemPrice;
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
}
