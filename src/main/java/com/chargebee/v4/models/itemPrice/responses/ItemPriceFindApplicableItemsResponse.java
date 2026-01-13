package com.chargebee.v4.models.itemPrice.responses;

import java.util.List;

import com.chargebee.v4.models.item.Item;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.ItemPriceService;
import com.chargebee.v4.models.itemPrice.params.ItemPriceFindApplicableItemsParams;

/**
 * Immutable response object for ItemPriceFindApplicableItems operation. Contains paginated list
 * data.
 */
public final class ItemPriceFindApplicableItemsResponse {

  private final List<ItemPriceFindApplicableItemsItem> list;

  private final String nextOffset;

  private final String itemPriceId;

  private final ItemPriceService service;
  private final ItemPriceFindApplicableItemsParams originalParams;
  private final Response httpResponse;

  private ItemPriceFindApplicableItemsResponse(
      List<ItemPriceFindApplicableItemsItem> list,
      String nextOffset,
      String itemPriceId,
      ItemPriceService service,
      ItemPriceFindApplicableItemsParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemPriceId = itemPriceId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ItemPriceFindApplicableItemsResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static ItemPriceFindApplicableItemsResponse fromJson(String json) {
    try {

      List<ItemPriceFindApplicableItemsItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceFindApplicableItemsItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemPriceFindApplicableItemsResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemPriceFindApplicableItemsResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemPriceFindApplicableItemsResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static ItemPriceFindApplicableItemsResponse fromJson(
      String json,
      ItemPriceService service,
      ItemPriceFindApplicableItemsParams originalParams,
      String itemPriceId,
      Response httpResponse) {
    try {

      List<ItemPriceFindApplicableItemsItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceFindApplicableItemsItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemPriceFindApplicableItemsResponse(
          list, nextOffset, itemPriceId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemPriceFindApplicableItemsResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ItemPriceFindApplicableItemsItem> getList() {
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
  public ItemPriceFindApplicableItemsResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ItemPriceFindApplicableItemsParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ItemPriceFindApplicableItemsParams.builder())
            .offset(nextOffset)
            .build();

    return service.findApplicableItems(itemPriceId, nextParams);
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
    return "ItemPriceFindApplicableItemsResponse{"
        + "list="
        + list
        + ", nextOffset="
        + nextOffset
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ItemPriceFindApplicableItemsResponse that = (ItemPriceFindApplicableItemsResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class ItemPriceFindApplicableItemsItem {

    private Item item;

    public Item getItem() {
      return item;
    }

    public static ItemPriceFindApplicableItemsItem fromJson(String json) {
      ItemPriceFindApplicableItemsItem item = new ItemPriceFindApplicableItemsItem();

      String __itemJson = JsonUtil.getObject(json, "item");
      if (__itemJson != null) {
        item.item = Item.fromJson(__itemJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "ItemPriceFindApplicableItemsItem{" + "item=" + item + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ItemPriceFindApplicableItemsItem that = (ItemPriceFindApplicableItemsItem) o;
      return java.util.Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(item);
    }
  }
}
