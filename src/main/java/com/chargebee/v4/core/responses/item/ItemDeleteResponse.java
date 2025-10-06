package com.chargebee.v4.core.responses.item;

import com.chargebee.v4.core.models.item.Item;

import com.chargebee.v4.internal.JsonUtil;

/** Immutable response object for ItemDelete operation. Contains the response data from the API. */
public final class ItemDeleteResponse {

  private final Item item;

  private ItemDeleteResponse(Builder builder) {

    this.item = builder.item;
  }

  /** Parse JSON response into ItemDeleteResponse object. */
  public static ItemDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __itemJson = JsonUtil.getObject(json, "item");
      if (__itemJson != null) {
        builder.item(Item.fromJson(__itemJson));
      }

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

    private Builder() {}

    public Builder item(Item item) {
      this.item = item;
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
}
