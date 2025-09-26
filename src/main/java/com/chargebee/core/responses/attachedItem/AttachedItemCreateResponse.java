package com.chargebee.core.responses.attachedItem;

import com.chargebee.core.models.attachedItem.AttachedItem;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for AttachedItemCreate operation. Contains the response data from the
 * API.
 */
public final class AttachedItemCreateResponse {

  private final AttachedItem attachedItem;

  private AttachedItemCreateResponse(Builder builder) {

    this.attachedItem = builder.attachedItem;
  }

  /** Parse JSON response into AttachedItemCreateResponse object. */
  public static AttachedItemCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __attachedItemJson = JsonUtil.getObject(json, "attached_item");
      if (__attachedItemJson != null) {
        builder.attachedItem(AttachedItem.fromJson(__attachedItemJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for AttachedItemCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AttachedItemCreateResponse. */
  public static class Builder {

    private AttachedItem attachedItem;

    private Builder() {}

    public Builder attachedItem(AttachedItem attachedItem) {
      this.attachedItem = attachedItem;
      return this;
    }

    public AttachedItemCreateResponse build() {
      return new AttachedItemCreateResponse(this);
    }
  }

  /** Get the attachedItem from the response. */
  public AttachedItem getAttachedItem() {
    return attachedItem;
  }
}
