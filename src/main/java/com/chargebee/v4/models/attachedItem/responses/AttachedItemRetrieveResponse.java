package com.chargebee.v4.models.attachedItem.responses;

import com.chargebee.v4.models.attachedItem.AttachedItem;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AttachedItemRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class AttachedItemRetrieveResponse extends BaseResponse {
  private final AttachedItem attachedItem;

  private AttachedItemRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.attachedItem = builder.attachedItem;
  }

  /** Parse JSON response into AttachedItemRetrieveResponse object. */
  public static AttachedItemRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AttachedItemRetrieveResponse object with HTTP response. */
  public static AttachedItemRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __attachedItemJson = JsonUtil.getObject(json, "attached_item");
      if (__attachedItemJson != null) {
        builder.attachedItem(AttachedItem.fromJson(__attachedItemJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for AttachedItemRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for AttachedItemRetrieveResponse. */
  public static class Builder {

    private AttachedItem attachedItem;

    private Response httpResponse;

    private Builder() {}

    public Builder attachedItem(AttachedItem attachedItem) {
      this.attachedItem = attachedItem;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public AttachedItemRetrieveResponse build() {
      return new AttachedItemRetrieveResponse(this);
    }
  }

  /** Get the attachedItem from the response. */
  public AttachedItem getAttachedItem() {
    return attachedItem;
  }

  @Override
  public String toString() {
    return "AttachedItemRetrieveResponse{" + "attachedItem=" + attachedItem + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AttachedItemRetrieveResponse that = (AttachedItemRetrieveResponse) o;
    return java.util.Objects.equals(attachedItem, that.attachedItem);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(attachedItem);
  }
}
