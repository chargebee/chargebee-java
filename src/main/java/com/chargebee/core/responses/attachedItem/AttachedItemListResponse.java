package com.chargebee.core.responses.attachedItem;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.attachedItem.AttachedItem;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.AttachedItemService;
import com.chargebee.core.models.attachedItem.params.AttachedItemListParams;

/**
 * Immutable response object for AttachedItemList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class AttachedItemListResponse
    implements Iterable<AttachedItemListResponse.AttachedItemListItem> {

  private final List<AttachedItemListItem> list;

  private final String nextOffset;

  private final String itemId;

  private final AttachedItemService service;
  private final AttachedItemListParams originalParams;
  private final boolean isAutoPaginate;

  private AttachedItemListResponse(
      List<AttachedItemListItem> list,
      String nextOffset,
      String itemId,
      AttachedItemService service,
      AttachedItemListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemId = itemId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private AttachedItemListResponse(
      List<AttachedItemListItem> list,
      String nextOffset,
      String itemId,
      AttachedItemService service,
      AttachedItemListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemId = itemId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into AttachedItemListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static AttachedItemListResponse fromJson(String json) {
    try {

      List<AttachedItemListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(AttachedItemListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new AttachedItemListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into AttachedItemListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static AttachedItemListResponse fromJson(
      String json,
      AttachedItemService service,
      AttachedItemListParams originalParams,
      String itemId) {
    try {

      List<AttachedItemListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(AttachedItemListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new AttachedItemListResponse(list, nextOffset, itemId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AttachedItemListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<AttachedItemListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<AttachedItemListItem> items() {
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
  public AttachedItemListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    AttachedItemListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(itemId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public AttachedItemListResponse autoPaginate() {
    return new AttachedItemListResponse(list, nextOffset, itemId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<AttachedItemListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<AttachedItemListItem> {
    private AttachedItemListResponse currentPage = AttachedItemListResponse.this;
    private Iterator<AttachedItemListItem> currentIterator = currentPage.list.iterator();

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
    public AttachedItemListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class AttachedItemListItem {

    private AttachedItem attachedItem;

    public AttachedItem getAttachedItem() {
      return attachedItem;
    }

    public static AttachedItemListItem fromJson(String json) {
      AttachedItemListItem item = new AttachedItemListItem();

      String __attachedItemJson = JsonUtil.getObject(json, "attached_item");
      if (__attachedItemJson != null) {
        item.attachedItem = AttachedItem.fromJson(__attachedItemJson);
      }

      return item;
    }
  }
}
