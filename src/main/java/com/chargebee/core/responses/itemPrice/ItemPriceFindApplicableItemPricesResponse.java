package com.chargebee.core.responses.itemPrice;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.itemPrice.ItemPrice;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.ItemPriceService;
import com.chargebee.core.models.itemPrice.params.ItemPriceFindApplicableItemPricesParams;

/**
 * Immutable response object for ItemPriceFindApplicableItemPrices operation. Contains paginated
 * list data with auto-pagination support.
 */
public final class ItemPriceFindApplicableItemPricesResponse
    implements Iterable<
        ItemPriceFindApplicableItemPricesResponse.ItemPriceFindApplicableItemPricesItem> {

  private final List<ItemPriceFindApplicableItemPricesItem> list;

  private final String nextOffset;

  private final String itemPriceId;

  private final ItemPriceService service;
  private final ItemPriceFindApplicableItemPricesParams originalParams;
  private final boolean isAutoPaginate;

  private ItemPriceFindApplicableItemPricesResponse(
      List<ItemPriceFindApplicableItemPricesItem> list,
      String nextOffset,
      String itemPriceId,
      ItemPriceService service,
      ItemPriceFindApplicableItemPricesParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemPriceId = itemPriceId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private ItemPriceFindApplicableItemPricesResponse(
      List<ItemPriceFindApplicableItemPricesItem> list,
      String nextOffset,
      String itemPriceId,
      ItemPriceService service,
      ItemPriceFindApplicableItemPricesParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemPriceId = itemPriceId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
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

      return new ItemPriceFindApplicableItemPricesResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemPriceFindApplicableItemPricesResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemPriceFindApplicableItemPricesResponse object with service context
   * for pagination (enables nextPage(), autoPaginate()).
   */
  public static ItemPriceFindApplicableItemPricesResponse fromJson(
      String json,
      ItemPriceService service,
      ItemPriceFindApplicableItemPricesParams originalParams,
      String itemPriceId) {
    try {

      List<ItemPriceFindApplicableItemPricesItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemPriceFindApplicableItemPricesItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemPriceFindApplicableItemPricesResponse(
          list, nextOffset, itemPriceId, service, originalParams);
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
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    ItemPriceFindApplicableItemPricesParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.findApplicableItemPrices(itemPriceId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public ItemPriceFindApplicableItemPricesResponse autoPaginate() {
    return new ItemPriceFindApplicableItemPricesResponse(
        list, nextOffset, itemPriceId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<ItemPriceFindApplicableItemPricesItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<ItemPriceFindApplicableItemPricesItem> {
    private ItemPriceFindApplicableItemPricesResponse currentPage =
        ItemPriceFindApplicableItemPricesResponse.this;
    private Iterator<ItemPriceFindApplicableItemPricesItem> currentIterator =
        currentPage.list.iterator();

    @Override
    public boolean hasNext() {
      if (currentIterator.hasNext()) {
        return true;
      }

      // Try to load next page if available
      if (currentPage.hasNextPage()) {
        try {
          currentPage = currentPage.nextPage();
          currentIterator = currentPage.list.iterator();
          return currentIterator.hasNext();
        } catch (Exception e) {
          throw new RuntimeException("Failed to fetch next page", e);
        }
      }

      return false;
    }

    @Override
    public ItemPriceFindApplicableItemPricesItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
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
