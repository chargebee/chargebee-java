package com.chargebee.v4.core.responses.itemFamily;

import com.chargebee.v4.core.models.itemFamily.ItemFamily;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ItemFamilyUpdate operation. Contains the response data from the
 * API.
 */
public final class ItemFamilyUpdateResponse {

  private final ItemFamily itemFamily;

  private ItemFamilyUpdateResponse(Builder builder) {

    this.itemFamily = builder.itemFamily;
  }

  /** Parse JSON response into ItemFamilyUpdateResponse object. */
  public static ItemFamilyUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __itemFamilyJson = JsonUtil.getObject(json, "item_family");
      if (__itemFamilyJson != null) {
        builder.itemFamily(ItemFamily.fromJson(__itemFamilyJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemFamilyUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemFamilyUpdateResponse. */
  public static class Builder {

    private ItemFamily itemFamily;

    private Builder() {}

    public Builder itemFamily(ItemFamily itemFamily) {
      this.itemFamily = itemFamily;
      return this;
    }

    public ItemFamilyUpdateResponse build() {
      return new ItemFamilyUpdateResponse(this);
    }
  }

  /** Get the itemFamily from the response. */
  public ItemFamily getItemFamily() {
    return itemFamily;
  }
}
