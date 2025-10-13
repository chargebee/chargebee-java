package com.chargebee.v4.core.responses.itemEntitlement;

import java.util.List;

import com.chargebee.v4.core.models.itemEntitlement.ItemEntitlement;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.ItemEntitlementService;
import com.chargebee.v4.core.models.itemEntitlement.params.ItemEntitlementItemEntitlementsForFeatureParams;

/**
 * Immutable response object for ItemEntitlementItemEntitlementsForFeature operation. Contains
 * paginated list data.
 */
public final class ItemEntitlementItemEntitlementsForFeatureResponse {

  private final List<ItemEntitlementItemEntitlementsForFeatureItem> list;

  private final String nextOffset;

  private final String featureId;

  private final ItemEntitlementService service;
  private final ItemEntitlementItemEntitlementsForFeatureParams originalParams;
  private final Response httpResponse;

  private ItemEntitlementItemEntitlementsForFeatureResponse(
      List<ItemEntitlementItemEntitlementsForFeatureItem> list,
      String nextOffset,
      String featureId,
      ItemEntitlementService service,
      ItemEntitlementItemEntitlementsForFeatureParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.featureId = featureId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ItemEntitlementItemEntitlementsForFeatureResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static ItemEntitlementItemEntitlementsForFeatureResponse fromJson(String json) {
    try {

      List<ItemEntitlementItemEntitlementsForFeatureItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemEntitlementItemEntitlementsForFeatureItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemEntitlementItemEntitlementsForFeatureResponse(
          list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemEntitlementItemEntitlementsForFeatureResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ItemEntitlementItemEntitlementsForFeatureResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static ItemEntitlementItemEntitlementsForFeatureResponse fromJson(
      String json,
      ItemEntitlementService service,
      ItemEntitlementItemEntitlementsForFeatureParams originalParams,
      String featureId,
      Response httpResponse) {
    try {

      List<ItemEntitlementItemEntitlementsForFeatureItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ItemEntitlementItemEntitlementsForFeatureItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ItemEntitlementItemEntitlementsForFeatureResponse(
          list, nextOffset, featureId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse ItemEntitlementItemEntitlementsForFeatureResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ItemEntitlementItemEntitlementsForFeatureItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<ItemEntitlementItemEntitlementsForFeatureItem> items() {
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
  public ItemEntitlementItemEntitlementsForFeatureResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ItemEntitlementItemEntitlementsForFeatureParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : ItemEntitlementItemEntitlementsForFeatureParams.builder())
            .offset(nextOffset)
            .build();

    return service.itemEntitlementsForFeature(featureId, nextParams);
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

  public static class ItemEntitlementItemEntitlementsForFeatureItem {

    private ItemEntitlement itemEntitlement;

    public ItemEntitlement getItemEntitlement() {
      return itemEntitlement;
    }

    public static ItemEntitlementItemEntitlementsForFeatureItem fromJson(String json) {
      ItemEntitlementItemEntitlementsForFeatureItem item =
          new ItemEntitlementItemEntitlementsForFeatureItem();

      String __itemEntitlementJson = JsonUtil.getObject(json, "item_entitlement");
      if (__itemEntitlementJson != null) {
        item.itemEntitlement = ItemEntitlement.fromJson(__itemEntitlementJson);
      }

      return item;
    }
  }
}
