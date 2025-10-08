package com.chargebee.v4.core.responses.itemPrice;

import com.chargebee.v4.core.models.itemPrice.ItemPrice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ItemPriceRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class ItemPriceRetrieveResponse {

  private final ItemPrice itemPrice;

  private final Response httpResponse;

  private ItemPriceRetrieveResponse(ItemPrice itemPrice, Response httpResponse) {

    this.itemPrice = itemPrice;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into ItemPriceRetrieveResponse object. */
  public static ItemPriceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ItemPriceRetrieveResponse object with HTTP response. */
  public static ItemPriceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      ItemPrice itemPrice = ItemPrice.fromJson(JsonUtil.getObject(json, "item_price"));

      return new ItemPriceRetrieveResponse(itemPrice, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemPriceRetrieveResponse from JSON", e);
    }
  }

  /** Get the itemPrice from the response. */
  public ItemPrice getItemPrice() {
    return itemPrice;
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
