package com.chargebee.v4.models.itemPrice.responses;

import com.chargebee.v4.models.itemPrice.ItemPrice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemPriceRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class ItemPriceRetrieveResponse extends BaseResponse {
  private final ItemPrice itemPrice;

  private ItemPriceRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemPrice = builder.itemPrice;
  }

  /** Parse JSON response into ItemPriceRetrieveResponse object. */
  public static ItemPriceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemPriceRetrieveResponse object with HTTP response. */
  public static ItemPriceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemPriceJson = JsonUtil.getObject(json, "item_price");
      if (__itemPriceJson != null) {
        builder.itemPrice(ItemPrice.fromJson(__itemPriceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemPriceRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemPriceRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemPriceRetrieveResponse. */
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

    public ItemPriceRetrieveResponse build() {
      return new ItemPriceRetrieveResponse(this);
    }
  }

  /** Get the itemPrice from the response. */
  public ItemPrice getItemPrice() {
    return itemPrice;
  }

  @Override
  public String toString() {
    return "ItemPriceRetrieveResponse{" + "itemPrice=" + itemPrice + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemPriceRetrieveResponse that = (ItemPriceRetrieveResponse) o;
    return java.util.Objects.equals(itemPrice, that.itemPrice);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(itemPrice);
  }
}
