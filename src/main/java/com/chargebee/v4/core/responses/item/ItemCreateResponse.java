package com.chargebee.v4.core.responses.item;

import com.chargebee.v4.core.models.item.Item;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for ItemCreate operation. Contains the response data from the API. */
public final class ItemCreateResponse {

  private final Item item;

  private final Response httpResponse;

  private ItemCreateResponse(Builder builder) {

    this.item = builder.item;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into ItemCreateResponse object. */
  public static ItemCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemCreateResponse object with HTTP response. */
  public static ItemCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemJson = JsonUtil.getObject(json, "item");
      if (__itemJson != null) {
        builder.item(Item.fromJson(__itemJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemCreateResponse. */
  public static class Builder {

    private Item item;

    private Response httpResponse;

    private Builder() {}

    public Builder item(Item item) {
      this.item = item;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ItemCreateResponse build() {
      return new ItemCreateResponse(this);
    }
  }

  /** Get the item from the response. */
  public Item getItem() {
    return item;
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
