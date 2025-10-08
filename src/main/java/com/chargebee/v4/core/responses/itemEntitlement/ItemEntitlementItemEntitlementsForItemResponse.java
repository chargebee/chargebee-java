package com.chargebee.v4.core.responses.itemEntitlement;

import java.util.List;

import com.chargebee.v4.core.models.itemEntitlement.ItemEntitlement;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.ItemEntitlementService;
import com.chargebee.v4.core.models.itemEntitlement.params.ItemEntitlementItemEntitlementsForItemParams;

/**
 * Immutable response object for ItemEntitlementItemEntitlementsForItem operation. Contains
 * paginated list data.
 */
public final class ItemEntitlementItemEntitlementsForItemResponse {

  private final List<ItemEntitlementItemEntitlementsForItemItem> list;

  private final String nextOffset;

  private final String itemId;

  private final ItemEntitlementService service;
  private final ItemEntitlementItemEntitlementsForItemParams originalParams;
  private final Response httpResponse;

  private ItemEntitlementItemEntitlementsForItemResponse(
      List<ItemEntitlementItemEntitlementsForItemItem> list,
      String nextOffset,
      String itemId,
      ItemEntitlementService service,
      ItemEntitlementItemEntitlementsForItemParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.itemId = itemId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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

      return new ItemEntitlementItemEntitlementsForItemResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemEntitlementItemEntitlementsForItemResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemEntitlementItemEntitlementsForItemResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static ItemEntitlementItemEntitlementsForItemResponse fromJson(
      String json,
      ItemEntitlementService service,
      ItemEntitlementItemEntitlementsForItemParams originalParams,
      String itemId,
      Response httpResponse) {
    try {

      List<ItemEntitlementItemEntitlementsForItemItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemEntitlementItemEntitlementsForItemItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemEntitlementItemEntitlementsForItemResponse(
          list, nextOffset, itemId, service, originalParams, httpResponse);
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
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    // Create new params with the next offset
    ItemEntitlementItemEntitlementsForItemParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ItemEntitlementItemEntitlementsForItemParams.builder())
            .offset(nextOffset)
            .build();

    return service.itemEntitlementsForItem(itemId, nextParams);
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
