package com.chargebee.v4.core.responses.itemFamily;

import com.chargebee.v4.core.models.itemFamily.ItemFamily;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ItemFamilyDelete operation. Contains the response data from the
 * API.
 */
public final class ItemFamilyDeleteResponse {

  private final ItemFamily itemFamily;

  private ItemFamilyDeleteResponse(Builder builder) {

    this.itemFamily = builder.itemFamily;
  }

  /** Parse JSON response into ItemFamilyDeleteResponse object. */
  public static ItemFamilyDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __itemFamilyJson = JsonUtil.getObject(json, "item_family");
      if (__itemFamilyJson != null) {
        builder.itemFamily(ItemFamily.fromJson(__itemFamilyJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemFamilyDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemFamilyDeleteResponse. */
  public static class Builder {

    private ItemFamily itemFamily;

    private Builder() {}

    public Builder itemFamily(ItemFamily itemFamily) {
      this.itemFamily = itemFamily;
      return this;
    }

    public ItemFamilyDeleteResponse build() {
      return new ItemFamilyDeleteResponse(this);
    }
  }

  /** Get the itemFamily from the response. */
  public ItemFamily getItemFamily() {
    return itemFamily;
  }
}
