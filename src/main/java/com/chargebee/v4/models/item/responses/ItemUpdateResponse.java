package com.chargebee.v4.models.item.responses;

import com.chargebee.v4.models.item.Item;

import com.chargebee.v4.models.BaseResponse;
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

  @Override
  public String toString() {
    return "ItemUpdateResponse{" + "item=" + item + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemUpdateResponse that = (ItemUpdateResponse) o;
    return java.util.Objects.equals(item, that.item);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(item);
  }
}
