package com.chargebee.v4.core.responses.attachedItem;

import com.chargebee.v4.core.models.attachedItem.AttachedItem;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for AttachedItemUpdate operation. Contains the response data from the
 * API.
 */
public final class AttachedItemUpdateResponse {

  private final AttachedItem attachedItem;

  private final Response httpResponse;

  private AttachedItemUpdateResponse(Builder builder) {

    this.attachedItem = builder.attachedItem;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into AttachedItemUpdateResponse object. */
  public static AttachedItemUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into AttachedItemUpdateResponse object with HTTP response. */
  public static AttachedItemUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __attachedItemJson = JsonUtil.getObject(json, "attached_item");
      if (__attachedItemJson != null) {
        builder.attachedItem(AttachedItem.fromJson(__attachedItemJson));
      }

      builder.httpResponse(httpResponse);
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

    public AttachedItemUpdateResponse build() {
      return new AttachedItemUpdateResponse(this);
    }
  }

  /** Get the attachedItem from the response. */
  public AttachedItem getAttachedItem() {
    return attachedItem;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
