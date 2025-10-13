package com.chargebee.v4.core.responses.itemPrice;

import java.util.List;

import com.chargebee.v4.core.models.itemPrice.ItemPrice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.ItemPriceService;
import com.chargebee.v4.core.models.itemPrice.params.ItemPriceFindApplicableItemPricesParams;

/**
 * Immutable response object for ItemPriceFindApplicableItemPrices operation. Contains paginated
 * list data.
 */
public final class ItemPriceFindApplicableItemPricesResponse {

  private final List<ItemPriceFindApplicableItemPricesItem> list;

  private final String nextOffset;

  private final String itemPriceId;

  private final ItemPriceService service;
  private final ItemPriceFindApplicableItemPricesParams originalParams;
  private final Response httpResponse;

  private ItemPriceFindApplicableItemPricesResponse(
      List<ItemPriceFindApplicableItemPricesItem> list,
      String nextOffset,
      String itemPriceId,
      ItemPriceService service,
      ItemPriceFindApplicableItemPricesParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemPriceId = itemPriceId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ItemPriceFindApplicableItemPricesResponse object (no service context).
   * Use this when you only need to read a single page (no nextPage()).
   */
  public static ItemPriceFindApplicableItemPricesResponse fromJson(String json) {
    try {

      List<ItemPriceFindApplicableItemPricesItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceFindApplicableItemPricesItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemPriceFindApplicableItemPricesResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemPriceFindApplicableItemPricesResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemPriceFindApplicableItemPricesResponse object with service context
   * for pagination (enables nextPage()).
   */
  public static ItemPriceFindApplicableItemPricesResponse fromJson(
      String json,
      ItemPriceService service,
      ItemPriceFindApplicableItemPricesParams originalParams,
      String itemPriceId,
      Response httpResponse) {
    try {

      List<ItemPriceFindApplicableItemPricesItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceFindApplicableItemPricesItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemPriceFindApplicableItemPricesResponse(
          list, nextOffset, itemPriceId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemPriceFindApplicableItemPricesResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ItemPriceFindApplicableItemPricesItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<ItemPriceFindApplicableItemPricesItem> items() {
    return list;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws Exception if unable to fetch next page
   */
  public ItemPriceFindApplicableItemPricesResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ItemPriceFindApplicableItemPricesParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ItemPriceFindApplicableItemPricesParams.builder())
            .offset(nextOffset)
            .build();

    return service.findApplicableItemPrices(itemPriceId, nextParams);
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

  public static class ItemPriceFindApplicableItemPricesItem {

    private ItemPrice itemPrice;

    public ItemPrice getItemPrice() {
      return itemPrice;
    }

    public static ItemPriceFindApplicableItemPricesItem fromJson(String json) {
      ItemPriceFindApplicableItemPricesItem item = new ItemPriceFindApplicableItemPricesItem();

      String __itemPriceJson = JsonUtil.getObject(json, "item_price");
      if (__itemPriceJson != null) {
        item.itemPrice = ItemPrice.fromJson(__itemPriceJson);
      }

      return item;
    }
  }
}
