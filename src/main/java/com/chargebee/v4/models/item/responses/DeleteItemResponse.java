package com.chargebee.v4.models.item.responses;

import com.chargebee.v4.models.item.Item;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for DeleteItem operation. Contains the response data from the API. */
public final class DeleteItemResponse extends BaseResponse {
  private final Item item;

  private DeleteItemResponse(Builder builder) {
    super(builder.httpResponse);

    this.item = builder.item;
  }

  /** Parse JSON response into DeleteItemResponse object. */
  public static DeleteItemResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteItemResponse object with HTTP response. */
  public static DeleteItemResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemJson = JsonUtil.getObject(json, "item");
      if (__itemJson != null) {
        builder.item(Item.fromJson(__itemJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteItemResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteItemResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteItemResponse. */
  public static class Builder {

    private Item item;

    private Response httpResponse;

    private Builder() {}

    public Builder item(Item item) {
      this.item = item;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DeleteItemResponse build() {
      return new DeleteItemResponse(this);
    }
  }

  /** Get the item from the response. */
  public Item getItem() {
    return item;
  }
}
