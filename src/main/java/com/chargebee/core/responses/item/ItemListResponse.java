package com.chargebee.core.responses.item;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.item.Item;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.ItemService;
import com.chargebee.core.models.item.params.ItemListParams;

/**
 * Immutable response object for ItemList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class ItemListResponse implements Iterable<ItemListResponse.ItemListItem> {

  private final List<ItemListItem> list;

  private final String nextOffset;

  private final ItemService service;
  private final ItemListParams originalParams;
  private final boolean isAutoPaginate;

  private ItemListResponse(
      List<ItemListItem> list,
      String nextOffset,
      ItemService service,
      ItemListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private ItemListResponse(
      List<ItemListItem> list,
      String nextOffset,
      ItemService service,
      ItemListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into ItemListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static ItemListResponse fromJson(String json) {
    try {

      List<ItemListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemListResponse object with service context for pagination (enables
   * nextPage(), autoPaginate()).
   */
  public static ItemListResponse fromJson(
      String json, ItemService service, ItemListParams originalParams) {
    try {

      List<ItemListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ItemListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<ItemListItem> items() {
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
  public ItemListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    ItemListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public ItemListResponse autoPaginate() {
    return new ItemListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<ItemListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<ItemListItem> {
    private ItemListResponse currentPage = ItemListResponse.this;
    private Iterator<ItemListItem> currentIterator = currentPage.list.iterator();

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
    public ItemListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class ItemListItem {

    private Item item;

    public Item getItem() {
      return item;
    }

    public static ItemListItem fromJson(String json) {
      ItemListItem item = new ItemListItem();

      String __itemJson = JsonUtil.getObject(json, "item");
      if (__itemJson != null) {
        item.item = Item.fromJson(__itemJson);
      }

      return item;
    }
  }
}
