package com.chargebee.core.responses.attachedItem;

import com.chargebee.core.models.attachedItem.AttachedItem;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for AttachedItemRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class AttachedItemRetrieveResponse {

  private final AttachedItem attachedItem;

  private AttachedItemRetrieveResponse(AttachedItem attachedItem) {

    this.attachedItem = attachedItem;
  }

  /** Parse JSON response into AttachedItemRetrieveResponse object. */
  public static AttachedItemRetrieveResponse fromJson(String json) {
    try {

      AttachedItem attachedItem = AttachedItem.fromJson(JsonUtil.getObject(json, "attached_item"));

      return new AttachedItemRetrieveResponse(attachedItem);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemRetrieveResponse from JSON", e);
    }
  }

  /** Get the attachedItem from the response. */
  public AttachedItem getAttachedItem() {
    return attachedItem;
  }
}
