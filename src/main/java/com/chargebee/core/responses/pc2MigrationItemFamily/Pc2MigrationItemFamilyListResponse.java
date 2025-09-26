package com.chargebee.core.responses.pc2MigrationItemFamily;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.Pc2MigrationItemFamilyService;
import com.chargebee.core.models.pc2MigrationItemFamily.params.Pc2MigrationItemFamilyListParams;

/**
 * Immutable response object for Pc2MigrationItemFamilyList operation. Contains paginated list data
 * with auto-pagination support.
 */
public final class Pc2MigrationItemFamilyListResponse implements Iterable<Object> {

  private final List<Object> list;

  private final String nextOffset;

  private final Pc2MigrationItemFamilyService service;
  private final Pc2MigrationItemFamilyListParams originalParams;
  private final boolean isAutoPaginate;

  private Pc2MigrationItemFamilyListResponse(
      List<Object> list,
      String nextOffset,
      Pc2MigrationItemFamilyService service,
      Pc2MigrationItemFamilyListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private Pc2MigrationItemFamilyListResponse(
      List<Object> list,
      String nextOffset,
      Pc2MigrationItemFamilyService service,
      Pc2MigrationItemFamilyListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into Pc2MigrationItemFamilyListResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static Pc2MigrationItemFamilyListResponse fromJson(String json) {
    try {

      List<Object> list =
          new ArrayList<>(JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")));

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new Pc2MigrationItemFamilyListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemFamilyListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into Pc2MigrationItemFamilyListResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static Pc2MigrationItemFamilyListResponse fromJson(
      String json,
      Pc2MigrationItemFamilyService service,
      Pc2MigrationItemFamilyListParams originalParams) {
    try {

      List<Object> list =
          new ArrayList<>(JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")));

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new Pc2MigrationItemFamilyListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemFamilyListResponse from JSON", e);
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
  public Pc2MigrationItemFamilyListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    Pc2MigrationItemFamilyListParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public Pc2MigrationItemFamilyListResponse autoPaginate() {
    return new Pc2MigrationItemFamilyListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<Object> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<Object> {
    private Pc2MigrationItemFamilyListResponse currentPage =
        Pc2MigrationItemFamilyListResponse.this;
    private Iterator<Object> currentIterator = currentPage.list.iterator();

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
    public Object next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }
}
