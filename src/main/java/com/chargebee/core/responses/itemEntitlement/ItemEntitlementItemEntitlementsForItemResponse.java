package com.chargebee.core.responses.itemEntitlement;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.itemEntitlement.ItemEntitlement;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.ItemEntitlementService;
import com.chargebee.core.models.itemEntitlement.params.ItemEntitlementItemEntitlementsForItemParams;

/**
 * Immutable response object for ItemEntitlementItemEntitlementsForItem operation. Contains
 * paginated list data with auto-pagination support.
 */
public final class ItemEntitlementItemEntitlementsForItemResponse
    implements Iterable<
        ItemEntitlementItemEntitlementsForItemResponse.ItemEntitlementItemEntitlementsForItemItem> {

  private final List<ItemEntitlementItemEntitlementsForItemItem> list;

  private final String nextOffset;

  private final String itemId;

  private final ItemEntitlementService service;
  private final ItemEntitlementItemEntitlementsForItemParams originalParams;
  private final boolean isAutoPaginate;

  private ItemEntitlementItemEntitlementsForItemResponse(
      List<ItemEntitlementItemEntitlementsForItemItem> list,
      String nextOffset,
      String itemId,
      ItemEntitlementService service,
      ItemEntitlementItemEntitlementsForItemParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemId = itemId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private ItemEntitlementItemEntitlementsForItemResponse(
      List<ItemEntitlementItemEntitlementsForItemItem> list,
      String nextOffset,
      String itemId,
      ItemEntitlementService service,
      ItemEntitlementItemEntitlementsForItemParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemId = itemId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into ItemEntitlementItemEntitlementsForItemResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static ItemEntitlementItemEntitlementsForItemResponse fromJson(String json) {
    try {

      List<ItemEntitlementItemEntitlementsForItemItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemEntitlementItemEntitlementsForItemItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemEntitlementItemEntitlementsForItemResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemEntitlementItemEntitlementsForItemResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemEntitlementItemEntitlementsForItemResponse object with service
   * context for pagination (enables nextPage(), autoPaginate()).
   */
  public static ItemEntitlementItemEntitlementsForItemResponse fromJson(
      String json,
      ItemEntitlementService service,
      ItemEntitlementItemEntitlementsForItemParams originalParams,
      String itemId) {
    try {

      List<ItemEntitlementItemEntitlementsForItemItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemEntitlementItemEntitlementsForItemItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemEntitlementItemEntitlementsForItemResponse(
          list, nextOffset, itemId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemEntitlementItemEntitlementsForItemResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ItemEntitlementItemEntitlementsForItemItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<ItemEntitlementItemEntitlementsForItemItem> items() {
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
  public ItemEntitlementItemEntitlementsForItemResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    ItemEntitlementItemEntitlementsForItemParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.itemEntitlementsForItem(itemId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public ItemEntitlementItemEntitlementsForItemResponse autoPaginate() {
    return new ItemEntitlementItemEntitlementsForItemResponse(
        list, nextOffset, itemId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<ItemEntitlementItemEntitlementsForItemItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator
      implements Iterator<ItemEntitlementItemEntitlementsForItemItem> {
    private ItemEntitlementItemEntitlementsForItemResponse currentPage =
        ItemEntitlementItemEntitlementsForItemResponse.this;
    private Iterator<ItemEntitlementItemEntitlementsForItemItem> currentIterator =
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
    public ItemEntitlementItemEntitlementsForItemItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class ItemEntitlementItemEntitlementsForItemItem {

    private ItemEntitlement itemEntitlement;

    public ItemEntitlement getItemEntitlement() {
      return itemEntitlement;
    }

    public static ItemEntitlementItemEntitlementsForItemItem fromJson(String json) {
      ItemEntitlementItemEntitlementsForItemItem item =
          new ItemEntitlementItemEntitlementsForItemItem();

      String __itemEntitlementJson = JsonUtil.getObject(json, "item_entitlement");
      if (__itemEntitlementJson != null) {
        item.itemEntitlement = ItemEntitlement.fromJson(__itemEntitlementJson);
      }

      return item;
    }
  }
}
