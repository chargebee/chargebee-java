package com.chargebee.v4.core.responses.attachedItem;

import com.chargebee.v4.core.models.attachedItem.AttachedItem;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AttachedItemRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class AttachedItemRetrieveResponse extends BaseResponse {
  private final AttachedItem attachedItem;

  private AttachedItemRetrieveResponse(AttachedItem attachedItem, Response httpResponse) {
    super(httpResponse);

    this.attachedItem = attachedItem;
  }

  /** Parse JSON response into AttachedItemRetrieveResponse object. */
  public static AttachedItemRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AttachedItemRetrieveResponse object with HTTP response. */
  public static AttachedItemRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      AttachedItem attachedItem = AttachedItem.fromJson(JsonUtil.getObject(json, "attached_item"));

      return new AttachedItemRetrieveResponse(attachedItem, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemRetrieveResponse from JSON", e);
    }
  }

  /** Get the attachedItem from the response. */
  public AttachedItem getAttachedItem() {
    return attachedItem;
  }
}
