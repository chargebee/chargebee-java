package com.chargebee.v4.core.responses.itemPrice;

import com.chargebee.v4.core.models.itemPrice.ItemPrice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ItemPriceDelete operation. Contains the response data from the API.
 */
public final class ItemPriceDeleteResponse {

  private final ItemPrice itemPrice;

  private ItemPriceDeleteResponse(Builder builder) {

    this.itemPrice = builder.itemPrice;
  }

  /** Parse JSON response into ItemPriceDeleteResponse object. */
  public static ItemPriceDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __itemPriceJson = JsonUtil.getObject(json, "item_price");
      if (__itemPriceJson != null) {
        builder.itemPrice(ItemPrice.fromJson(__itemPriceJson));
      }

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

    private Builder() {}

    public Builder itemPrice(ItemPrice itemPrice) {
      this.itemPrice = itemPrice;
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
}
