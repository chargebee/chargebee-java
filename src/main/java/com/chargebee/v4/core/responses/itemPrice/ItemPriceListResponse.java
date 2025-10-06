package com.chargebee.v4.core.responses.itemPrice;

import java.util.List;

import com.chargebee.v4.core.models.itemPrice.ItemPrice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.ItemPriceService;
import com.chargebee.v4.core.models.itemPrice.params.ItemPriceListParams;

/** Immutable response object for ItemPriceList operation. Contains paginated list data. */
public final class ItemPriceListResponse {

  private final List<ItemPriceListItem> list;

  private final String nextOffset;

  private final ItemPriceService service;
  private final ItemPriceListParams originalParams;

  private ItemPriceListResponse(
      List<ItemPriceListItem> list,
      String nextOffset,
      ItemPriceService service,
      ItemPriceListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
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

      return new ItemPriceListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemPriceListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemPriceListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static ItemPriceListResponse fromJson(
      String json, ItemPriceService service, ItemPriceListParams originalParams) {
    try {

      List<ItemPriceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemPriceListResponse(list, nextOffset, service, originalParams);
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

  /** Get the list of items in this page (alias). */
  public List<ItemPriceListItem> items() {
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
  public ItemPriceListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    ItemPriceListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
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
  }
}
