package com.chargebee.v4.models.attachedItem.responses;

import com.chargebee.v4.models.attachedItem.AttachedItem;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AttachedItemCreate operation. Contains the response data from the
 * API.
 */
public final class AttachedItemCreateResponse extends BaseResponse {
  private final AttachedItem attachedItem;

  private AttachedItemCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.attachedItem = builder.attachedItem;
  }

  /** Parse JSON response into AttachedItemCreateResponse object. */
  public static AttachedItemCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AttachedItemCreateResponse object with HTTP response. */
  public static AttachedItemCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __attachedItemJson = JsonUtil.getObject(json, "attached_item");
      if (__attachedItemJson != null) {
        builder.attachedItem(AttachedItem.fromJson(__attachedItemJson));
      }

      builder.httpResponse(httpResponse);
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

    public AttachedItemCreateResponse build() {
      return new AttachedItemCreateResponse(this);
    }
  }

  /** Get the attachedItem from the response. */
  public AttachedItem getAttachedItem() {
    return attachedItem;
  }
}
