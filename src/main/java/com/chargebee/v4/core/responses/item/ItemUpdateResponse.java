package com.chargebee.v4.core.responses.item;

import com.chargebee.v4.core.models.item.Item;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for ItemUpdate operation. Contains the response data from the API. */
public final class ItemUpdateResponse extends BaseResponse {
  private final Item item;

  private ItemUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.item = builder.item;
  }

  /** Parse JSON response into ItemUpdateResponse object. */
  public static ItemUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemUpdateResponse object with HTTP response. */
  public static ItemUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemJson = JsonUtil.getObject(json, "item");
      if (__itemJson != null) {
        builder.item(Item.fromJson(__itemJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemUpdateResponse. */
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

    public ItemUpdateResponse build() {
      return new ItemUpdateResponse(this);
    }
  }

  /** Get the item from the response. */
  public Item getItem() {
    return item;
  }
}
