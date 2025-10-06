package com.chargebee.v4.core.responses.attachedItem;

import com.chargebee.v4.core.models.attachedItem.AttachedItem;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for AttachedItemDelete operation. Contains the response data from the
 * API.
 */
public final class AttachedItemDeleteResponse {

  private final AttachedItem attachedItem;

  private AttachedItemDeleteResponse(Builder builder) {

    this.attachedItem = builder.attachedItem;
  }

  /** Parse JSON response into AttachedItemDeleteResponse object. */
  public static AttachedItemDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __attachedItemJson = JsonUtil.getObject(json, "attached_item");
      if (__attachedItemJson != null) {
        builder.attachedItem(AttachedItem.fromJson(__attachedItemJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for AttachedItemDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AttachedItemDeleteResponse. */
  public static class Builder {

    private AttachedItem attachedItem;

    private Builder() {}

    public Builder attachedItem(AttachedItem attachedItem) {
      this.attachedItem = attachedItem;
      return this;
    }

    public AttachedItemDeleteResponse build() {
      return new AttachedItemDeleteResponse(this);
    }
  }

  /** Get the attachedItem from the response. */
  public AttachedItem getAttachedItem() {
    return attachedItem;
  }
}
