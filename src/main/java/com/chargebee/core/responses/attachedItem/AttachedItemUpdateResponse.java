package com.chargebee.core.responses.attachedItem;

import com.chargebee.core.models.attachedItem.AttachedItem;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for AttachedItemUpdate operation. Contains the response data from the
 * API.
 */
public final class AttachedItemUpdateResponse {

  private final AttachedItem attachedItem;

  private AttachedItemUpdateResponse(Builder builder) {

    this.attachedItem = builder.attachedItem;
  }

  /** Parse JSON response into AttachedItemUpdateResponse object. */
  public static AttachedItemUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __attachedItemJson = JsonUtil.getObject(json, "attached_item");
      if (__attachedItemJson != null) {
        builder.attachedItem(AttachedItem.fromJson(__attachedItemJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for AttachedItemUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AttachedItemUpdateResponse. */
  public static class Builder {

    private AttachedItem attachedItem;

    private Builder() {}

    public Builder attachedItem(AttachedItem attachedItem) {
      this.attachedItem = attachedItem;
      return this;
    }

    public AttachedItemUpdateResponse build() {
      return new AttachedItemUpdateResponse(this);
    }
  }

  /** Get the attachedItem from the response. */
  public AttachedItem getAttachedItem() {
    return attachedItem;
  }
}
