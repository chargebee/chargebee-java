package com.chargebee.v4.core.responses.itemPrice;

import com.chargebee.v4.core.models.itemPrice.ItemPrice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ItemPriceCreate operation. Contains the response data from the API.
 */
public final class ItemPriceCreateResponse {

  private final ItemPrice itemPrice;

  private ItemPriceCreateResponse(Builder builder) {

    this.itemPrice = builder.itemPrice;
  }

  /** Parse JSON response into ItemPriceCreateResponse object. */
  public static ItemPriceCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __itemPriceJson = JsonUtil.getObject(json, "item_price");
      if (__itemPriceJson != null) {
        builder.itemPrice(ItemPrice.fromJson(__itemPriceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemPriceCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemPriceCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemPriceCreateResponse. */
  public static class Builder {

    private ItemPrice itemPrice;

    private Builder() {}

    public Builder itemPrice(ItemPrice itemPrice) {
      this.itemPrice = itemPrice;
      return this;
    }

    public ItemPriceCreateResponse build() {
      return new ItemPriceCreateResponse(this);
    }
  }

  /** Get the itemPrice from the response. */
  public ItemPrice getItemPrice() {
    return itemPrice;
  }
}
