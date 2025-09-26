package com.chargebee.core.responses.itemFamily;

import com.chargebee.core.models.itemFamily.ItemFamily;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ItemFamilyCreate operation. Contains the response data from the
 * API.
 */
public final class ItemFamilyCreateResponse {

  private final ItemFamily itemFamily;

  private ItemFamilyCreateResponse(Builder builder) {

    this.itemFamily = builder.itemFamily;
  }

  /** Parse JSON response into ItemFamilyCreateResponse object. */
  public static ItemFamilyCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __itemFamilyJson = JsonUtil.getObject(json, "item_family");
      if (__itemFamilyJson != null) {
        builder.itemFamily(ItemFamily.fromJson(__itemFamilyJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemFamilyCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemFamilyCreateResponse. */
  public static class Builder {

    private ItemFamily itemFamily;

    private Builder() {}

    public Builder itemFamily(ItemFamily itemFamily) {
      this.itemFamily = itemFamily;
      return this;
    }

    public ItemFamilyCreateResponse build() {
      return new ItemFamilyCreateResponse(this);
    }
  }

  /** Get the itemFamily from the response. */
  public ItemFamily getItemFamily() {
    return itemFamily;
  }
}
