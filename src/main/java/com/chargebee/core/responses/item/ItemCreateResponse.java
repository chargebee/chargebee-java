package com.chargebee.core.responses.item;

import com.chargebee.core.models.item.Item;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for ItemCreate operation. Contains the response data from the API. */
public final class ItemCreateResponse {

  private final Item item;

  private ItemCreateResponse(Builder builder) {

    this.item = builder.item;
  }

  /** Parse JSON response into ItemCreateResponse object. */
  public static ItemCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __itemJson = JsonUtil.getObject(json, "item");
      if (__itemJson != null) {
        builder.item(Item.fromJson(__itemJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemCreateResponse. */
  public static class Builder {

    private Item item;

    private Builder() {}

    public Builder item(Item item) {
      this.item = item;
      return this;
    }

    public ItemCreateResponse build() {
      return new ItemCreateResponse(this);
    }
  }

  /** Get the item from the response. */
  public Item getItem() {
    return item;
  }
}
