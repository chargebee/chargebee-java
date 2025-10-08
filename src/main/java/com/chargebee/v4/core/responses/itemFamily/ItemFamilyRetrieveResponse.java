package com.chargebee.v4.core.responses.itemFamily;

import com.chargebee.v4.core.models.itemFamily.ItemFamily;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemFamilyRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class ItemFamilyRetrieveResponse {

  private final ItemFamily itemFamily;

  private final Response httpResponse;

  private ItemFamilyRetrieveResponse(ItemFamily itemFamily, Response httpResponse) {

    this.itemFamily = itemFamily;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into ItemFamilyRetrieveResponse object. */
  public static ItemFamilyRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemFamilyRetrieveResponse object with HTTP response. */
  public static ItemFamilyRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      ItemFamily itemFamily = ItemFamily.fromJson(JsonUtil.getObject(json, "item_family"));

      return new ItemFamilyRetrieveResponse(itemFamily, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyRetrieveResponse from JSON", e);
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
