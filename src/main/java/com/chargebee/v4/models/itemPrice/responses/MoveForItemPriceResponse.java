package com.chargebee.v4.models.itemPrice.responses;

import com.chargebee.v4.models.itemPrice.ItemPrice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for MoveForItemPrice operation. Contains the response data from the
 * API.
 */
public final class MoveForItemPriceResponse extends BaseResponse {
  private final ItemPrice itemPrice;

  private MoveForItemPriceResponse(Builder builder) {
    super(builder.httpResponse);

    this.itemPrice = builder.itemPrice;
  }

  /** Parse JSON response into MoveForItemPriceResponse object. */
  public static MoveForItemPriceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into MoveForItemPriceResponse object with HTTP response. */
  public static MoveForItemPriceResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemPriceJson = JsonUtil.getObject(json, "item_price");
      if (__itemPriceJson != null) {
        builder.itemPrice(ItemPrice.fromJson(__itemPriceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse MoveForItemPriceResponse from JSON", e);
    }
  }

  /** Create a new builder for MoveForItemPriceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for MoveForItemPriceResponse. */
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

    public MoveForItemPriceResponse build() {
      return new MoveForItemPriceResponse(this);
    }
  }

  /** Get the itemPrice from the response. */
  public ItemPrice getItemPrice() {
    return itemPrice;
  }
}
