package com.chargebee.v4.core.responses.itemPrice;

import com.chargebee.v4.core.models.itemPrice.ItemPrice;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemPriceCreate operation. Contains the response data from the API.
 */
public final class ItemPriceCreateResponse extends BaseResponse {
  private final ItemPrice itemPrice;

  private ItemPriceCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemPrice = builder.itemPrice;
  }

  /** Parse JSON response into ItemPriceCreateResponse object. */
  public static ItemPriceCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemPriceCreateResponse object with HTTP response. */
  public static ItemPriceCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemPriceJson = JsonUtil.getObject(json, "item_price");
      if (__itemPriceJson != null) {
        builder.itemPrice(ItemPrice.fromJson(__itemPriceJson));
      }

      builder.httpResponse(httpResponse);
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

    public ItemPriceCreateResponse build() {
      return new ItemPriceCreateResponse(this);
    }
  }

  /** Get the itemPrice from the response. */
  public ItemPrice getItemPrice() {
    return itemPrice;
  }
}
