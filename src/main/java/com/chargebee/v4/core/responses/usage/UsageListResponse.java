package com.chargebee.v4.core.responses.usage;

import java.util.List;

import com.chargebee.v4.core.models.usage.Usage;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.UsageService;
import com.chargebee.v4.core.models.usage.params.UsageListParams;

/** Immutable response object for UsageList operation. Contains paginated list data. */
public final class UsageListResponse {

  private final List<UsageListItem> list;

  private final String nextOffset;

  private final UsageService service;
  private final UsageListParams originalParams;

  private UsageListResponse(
      List<UsageListItem> list,
      String nextOffset,
      UsageService service,
      UsageListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into UsageListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static UsageListResponse fromJson(String json) {
    try {

      List<UsageListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(UsageListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new UsageListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into UsageListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static UsageListResponse fromJson(
      String json, UsageService service, UsageListParams originalParams) {
    try {

      List<UsageListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(UsageListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new UsageListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UsageListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<UsageListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<UsageListItem> items() {
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
  public UsageListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    UsageListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class UsageListItem {

    private Usage usage;

    public Usage getUsage() {
      return usage;
    }

    public static UsageListItem fromJson(String json) {
      UsageListItem item = new UsageListItem();

      String __usageJson = JsonUtil.getObject(json, "usage");
      if (__usageJson != null) {
        item.usage = Usage.fromJson(__usageJson);
      }

      return item;
    }
  }
}
