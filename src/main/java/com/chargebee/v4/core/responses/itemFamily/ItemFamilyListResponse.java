package com.chargebee.v4.core.responses.itemFamily;

import java.util.List;

import com.chargebee.v4.core.models.itemFamily.ItemFamily;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.ItemFamilyService;
import com.chargebee.v4.core.models.itemFamily.params.ItemFamilyListParams;

/** Immutable response object for ItemFamilyList operation. Contains paginated list data. */
public final class ItemFamilyListResponse {

  private final List<ItemFamilyListItem> list;

  private final String nextOffset;

  private final ItemFamilyService service;
  private final ItemFamilyListParams originalParams;

  private ItemFamilyListResponse(
      List<ItemFamilyListItem> list,
      String nextOffset,
      ItemFamilyService service,
      ItemFamilyListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
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
   * (enables nextPage()).
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
