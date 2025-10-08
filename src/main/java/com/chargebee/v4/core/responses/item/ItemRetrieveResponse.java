package com.chargebee.v4.core.responses.item;

import com.chargebee.v4.core.models.item.Item;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class ItemRetrieveResponse {

  private final Item item;

  private final Response httpResponse;

  private ItemRetrieveResponse(Item item, Response httpResponse) {

    this.item = item;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into ItemRetrieveResponse object. */
  public static ItemRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemRetrieveResponse object with HTTP response. */
  public static ItemRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Item item = Item.fromJson(JsonUtil.getObject(json, "item"));

      return new ItemRetrieveResponse(item, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemRetrieveResponse from JSON", e);
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
