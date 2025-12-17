package com.chargebee.v4.models.itemPrice.responses;

import com.chargebee.v4.models.itemPrice.ItemPrice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteItemPrice operation. Contains the response data from the API.
 */
public final class DeleteItemPriceResponse extends BaseResponse {
  private final ItemPrice itemPrice;

  private DeleteItemPriceResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemPrice = builder.itemPrice;
  }

  /** Parse JSON response into DeleteItemPriceResponse object. */
  public static DeleteItemPriceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteItemPriceResponse object with HTTP response. */
  public static DeleteItemPriceResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemPriceJson = JsonUtil.getObject(json, "item_price");
      if (__itemPriceJson != null) {
        builder.itemPrice(ItemPrice.fromJson(__itemPriceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteItemPriceResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteItemPriceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteItemPriceResponse. */
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

    public DeleteItemPriceResponse build() {
      return new DeleteItemPriceResponse(this);
    }
  }

  /** Get the itemPrice from the response. */
  public ItemPrice getItemPrice() {
    return itemPrice;
  }
}
