package com.chargebee.v4.core.responses.addon;

import java.util.List;

import com.chargebee.v4.core.models.addon.Addon;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.AddonService;
import com.chargebee.v4.core.models.addon.params.AddonListParams;

/** Immutable response object for AddonList operation. Contains paginated list data. */
public final class AddonListResponse {

  private final List<AddonListItem> list;

  private final String nextOffset;

  private final AddonService service;
  private final AddonListParams originalParams;

  private AddonListResponse(
      List<AddonListItem> list,
      String nextOffset,
      AddonService service,
      AddonListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into AddonListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static AddonListResponse fromJson(String json) {
    try {

      List<AddonListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(AddonListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new AddonListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into AddonListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static AddonListResponse fromJson(
      String json, AddonService service, AddonListParams originalParams) {
    try {

      List<AddonListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(AddonListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new AddonListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse AddonListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<AddonListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<AddonListItem> items() {
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
  public AddonListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    AddonListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class AddonListItem {

    private Addon addon;

    public Addon getAddon() {
      return addon;
    }

    public static AddonListItem fromJson(String json) {
      AddonListItem item = new AddonListItem();

      String __addonJson = JsonUtil.getObject(json, "addon");
      if (__addonJson != null) {
        item.addon = Addon.fromJson(__addonJson);
      }

      return item;
    }
  }
}
