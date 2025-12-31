package com.chargebee.v4.models.itemPrice.responses;

import java.util.List;

import com.chargebee.v4.models.itemPrice.ItemPrice;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.ItemPriceService;
import com.chargebee.v4.models.itemPrice.params.ItemPriceListParams;

/** Immutable response object for ItemPriceList operation. Contains paginated list data. */
public final class ItemPriceListResponse {

  private final List<ItemPriceListItem> list;

  private final String nextOffset;

  private final ItemPriceService service;
  private final ItemPriceListParams originalParams;
  private final Response httpResponse;

  private ItemPriceListResponse(
      List<ItemPriceListItem> list,
      String nextOffset,
      ItemPriceService service,
      ItemPriceListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ItemPriceListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static ItemPriceListResponse fromJson(String json) {
    try {

      List<ItemPriceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemPriceListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemPriceListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemPriceListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static ItemPriceListResponse fromJson(
      String json,
      ItemPriceService service,
      ItemPriceListParams originalParams,
      Response httpResponse) {
    try {

      List<ItemPriceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemPriceListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemPriceListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ItemPriceListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public ItemPriceListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ItemPriceListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : ItemPriceListParams.builder())
            .offset(nextOffset)
            .build();

    return service.list(nextParams);
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

  @Override
  public String toString() {
    return "ItemPriceListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemPriceListResponse that = (ItemPriceListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
  }

  public static class ItemPriceListItem {

    private ItemPrice itemPrice;

    public ItemPrice getItemPrice() {
      return itemPrice;
    }

    public static ItemPriceListItem fromJson(String json) {
      ItemPriceListItem item = new ItemPriceListItem();

      String __itemPriceJson = JsonUtil.getObject(json, "item_price");
      if (__itemPriceJson != null) {
        item.itemPrice = ItemPrice.fromJson(__itemPriceJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "ItemPriceListItem{" + "itemPrice=" + itemPrice + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ItemPriceListItem that = (ItemPriceListItem) o;
      return java.util.Objects.equals(itemPrice, that.itemPrice);
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(itemPrice);
    }
  }
}
