package com.chargebee.v4.models.item.responses;

import com.chargebee.v4.models.item.Item;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for ItemDelete operation. Contains the response data from the API. */
public final class ItemDeleteResponse extends BaseResponse {
  private final Item item;

  private ItemDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.item = builder.item;
  }

  /** Parse JSON response into ItemDeleteResponse object. */
  public static ItemDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemDeleteResponse object with HTTP response. */
  public static ItemDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemJson = JsonUtil.getObject(json, "item");
      if (__itemJson != null) {
        builder.item(Item.fromJson(__itemJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemDeleteResponse. */
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

    public ItemDeleteResponse build() {
      return new ItemDeleteResponse(this);
    }
  }

  /** Get the item from the response. */
  public Item getItem() {
    return item;
  }

  @Override
  public String toString() {
    return "ItemDeleteResponse{" + "item=" + item + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemDeleteResponse that = (ItemDeleteResponse) o;
    return java.util.Objects.equals(item, that.item);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(item);
  }
}
