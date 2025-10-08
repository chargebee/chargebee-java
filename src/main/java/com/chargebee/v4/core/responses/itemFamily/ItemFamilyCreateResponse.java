package com.chargebee.v4.core.responses.itemFamily;

import com.chargebee.v4.core.models.itemFamily.ItemFamily;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemFamilyCreate operation. Contains the response data from the
 * API.
 */
public final class ItemFamilyCreateResponse {

  private final ItemFamily itemFamily;

  private final Response httpResponse;

  private ItemFamilyCreateResponse(Builder builder) {

    this.itemFamily = builder.itemFamily;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into ItemFamilyCreateResponse object. */
  public static ItemFamilyCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemFamilyCreateResponse object with HTTP response. */
  public static ItemFamilyCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __itemFamilyJson = JsonUtil.getObject(json, "item_family");
      if (__itemFamilyJson != null) {
        builder.itemFamily(ItemFamily.fromJson(__itemFamilyJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for ItemFamilyCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ItemFamilyCreateResponse. */
  public static class Builder {

    private ItemFamily itemFamily;

    private Response httpResponse;

    private Builder() {}

    public Builder itemFamily(ItemFamily itemFamily) {
      this.itemFamily = itemFamily;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ItemFamilyCreateResponse build() {
      return new ItemFamilyCreateResponse(this);
    }
  }

  /** Get the itemFamily from the response. */
  public ItemFamily getItemFamily() {
    return itemFamily;
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
