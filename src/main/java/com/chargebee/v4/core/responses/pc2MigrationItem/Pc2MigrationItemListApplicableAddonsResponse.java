package com.chargebee.v4.core.responses.pc2MigrationItem;

import java.util.List;
import java.util.ArrayList;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.Pc2MigrationItemService;
import com.chargebee.v4.core.models.pc2MigrationItem.params.Pc2MigrationItemListApplicableAddonsParams;

/**
 * Immutable response object for Pc2MigrationItemListApplicableAddons operation. Contains paginated
 * list data.
 */
public final class Pc2MigrationItemListApplicableAddonsResponse {

  private final List<Object> list;

  private final String nextOffset;

  private final Pc2MigrationItemService service;
  private final Pc2MigrationItemListApplicableAddonsParams originalParams;

  private Pc2MigrationItemListApplicableAddonsResponse(
      List<Object> list,
      String nextOffset,
      Pc2MigrationItemService service,
      Pc2MigrationItemListApplicableAddonsParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into Pc2MigrationItemListApplicableAddonsResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static Pc2MigrationItemListApplicableAddonsResponse fromJson(String json) {
    try {

      List<Object> list =
          new ArrayList<>(JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")));

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new Pc2MigrationItemListApplicableAddonsResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemListApplicableAddonsResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into Pc2MigrationItemListApplicableAddonsResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static Pc2MigrationItemListApplicableAddonsResponse fromJson(
      String json,
      Pc2MigrationItemService service,
      Pc2MigrationItemListApplicableAddonsParams originalParams) {
    try {

      List<Object> list =
          new ArrayList<>(JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")));

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new Pc2MigrationItemListApplicableAddonsResponse(
          list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemListApplicableAddonsResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<Object> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<Object> items() {
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
  public Pc2MigrationItemListApplicableAddonsResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    Pc2MigrationItemListApplicableAddonsParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.listApplicableAddons(nextParams);
  }
}
