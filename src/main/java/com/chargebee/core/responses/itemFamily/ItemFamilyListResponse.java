package com.chargebee.core.responses.itemFamily;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.itemFamily.ItemFamily;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.ItemFamilyService;
import com.chargebee.core.models.itemFamily.params.ItemFamilyListParams;

/**
 * Immutable response object for ItemFamilyList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class ItemFamilyListResponse
    implements Iterable<ItemFamilyListResponse.ItemFamilyListItem> {

  private final List<ItemFamilyListItem> list;

  private final String nextOffset;

  private final ItemFamilyService service;
  private final ItemFamilyListParams originalParams;
  private final boolean isAutoPaginate;

  private ItemFamilyListResponse(
      List<ItemFamilyListItem> list,
      String nextOffset,
      ItemFamilyService service,
      ItemFamilyListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private ItemFamilyListResponse(
      List<ItemFamilyListItem> list,
      String nextOffset,
      ItemFamilyService service,
      ItemFamilyListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into ItemFamilyListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static ItemFamilyListResponse fromJson(String json) {
    try {

      List<ItemFamilyListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemFamilyListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemFamilyListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemFamilyListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static ItemFamilyListResponse fromJson(
      String json, ItemFamilyService service, ItemFamilyListParams originalParams) {
    try {

      List<ItemFamilyListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemFamilyListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemFamilyListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ItemFamilyListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ItemFamilyListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<ItemFamilyListItem> items() {
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
  public ItemFamilyListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    ItemFamilyListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public ItemFamilyListResponse autoPaginate() {
    return new ItemFamilyListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<ItemFamilyListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<ItemFamilyListItem> {
    private ItemFamilyListResponse currentPage = ItemFamilyListResponse.this;
    private Iterator<ItemFamilyListItem> currentIterator = currentPage.list.iterator();

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
    public ItemFamilyListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class ItemFamilyListItem {

    private ItemFamily itemFamily;

    public ItemFamily getItemFamily() {
      return itemFamily;
    }

    public static ItemFamilyListItem fromJson(String json) {
      ItemFamilyListItem item = new ItemFamilyListItem();

      String __itemFamilyJson = JsonUtil.getObject(json, "item_family");
      if (__itemFamilyJson != null) {
        item.itemFamily = ItemFamily.fromJson(__itemFamilyJson);
      }

      return item;
    }
  }
}
