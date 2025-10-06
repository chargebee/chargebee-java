package com.chargebee.core.responses.entitlement;

import java.util.List;

import com.chargebee.core.models.entitlement.Entitlement;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.EntitlementService;
import com.chargebee.core.models.entitlement.params.EntitlementListParams;

/** Immutable response object for EntitlementList operation. Contains paginated list data. */
public final class EntitlementListResponse {

  private final List<EntitlementListItem> list;

  private final String nextOffset;

  private final EntitlementService service;
  private final EntitlementListParams originalParams;

  private EntitlementListResponse(
      List<EntitlementListItem> list,
      String nextOffset,
      EntitlementService service,
      EntitlementListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into EntitlementListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static EntitlementListResponse fromJson(String json) {
    try {

      List<EntitlementListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new EntitlementListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EntitlementListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into EntitlementListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static EntitlementListResponse fromJson(
      String json, EntitlementService service, EntitlementListParams originalParams) {
    try {

      List<EntitlementListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new EntitlementListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EntitlementListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<EntitlementListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<EntitlementListItem> items() {
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
  public EntitlementListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    EntitlementListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class EntitlementListItem {

    private Entitlement entitlement;

    public Entitlement getEntitlement() {
      return entitlement;
    }

    public static EntitlementListItem fromJson(String json) {
      EntitlementListItem item = new EntitlementListItem();

      String __entitlementJson = JsonUtil.getObject(json, "entitlement");
      if (__entitlementJson != null) {
        item.entitlement = Entitlement.fromJson(__entitlementJson);
      }

      return item;
    }
  }
}
