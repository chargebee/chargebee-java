package com.chargebee.core.responses.itemPrice;

import java.util.List;

import com.chargebee.core.models.item.Item;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.ItemPriceService;
import com.chargebee.core.models.itemPrice.params.ItemPriceFindApplicableItemsParams;

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

  private ItemPriceFindApplicableItemsResponse(
      List<ItemPriceFindApplicableItemsItem> list,
      String nextOffset,
      String itemPriceId,
      ItemPriceService service,
      ItemPriceFindApplicableItemsParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemPriceId = itemPriceId;

    this.service = service;
    this.originalParams = originalParams;
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

      return new ItemPriceFindApplicableItemsResponse(list, nextOffset, null, null, null);
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
      String itemPriceId) {
    try {

      List<ItemPriceFindApplicableItemsItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceFindApplicableItemsItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemPriceFindApplicableItemsResponse(
          list, nextOffset, itemPriceId, service, originalParams);
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

  /** Get the list of items in this page (alias). */
  public List<ItemPriceFindApplicableItemsItem> items() {
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
  public ItemPriceFindApplicableItemsResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    ItemPriceFindApplicableItemsParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.findApplicableItems(itemPriceId, nextParams);
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
  }
}
