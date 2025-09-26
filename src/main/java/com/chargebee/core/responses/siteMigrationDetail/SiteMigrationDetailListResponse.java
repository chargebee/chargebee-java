package com.chargebee.core.responses.siteMigrationDetail;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.siteMigrationDetail.SiteMigrationDetail;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.SiteMigrationDetailService;
import com.chargebee.core.models.siteMigrationDetail.params.SiteMigrationDetailListParams;

/**
 * Immutable response object for SiteMigrationDetailList operation. Contains paginated list data
 * with auto-pagination support.
 */
public final class SiteMigrationDetailListResponse
    implements Iterable<SiteMigrationDetailListResponse.SiteMigrationDetailListItem> {

  private final List<SiteMigrationDetailListItem> list;

  private final String nextOffset;

  private final SiteMigrationDetailService service;
  private final SiteMigrationDetailListParams originalParams;
  private final boolean isAutoPaginate;

  private SiteMigrationDetailListResponse(
      List<SiteMigrationDetailListItem> list,
      String nextOffset,
      SiteMigrationDetailService service,
      SiteMigrationDetailListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private SiteMigrationDetailListResponse(
      List<SiteMigrationDetailListItem> list,
      String nextOffset,
      SiteMigrationDetailService service,
      SiteMigrationDetailListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into SiteMigrationDetailListResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static SiteMigrationDetailListResponse fromJson(String json) {
    try {

      List<SiteMigrationDetailListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SiteMigrationDetailListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SiteMigrationDetailListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SiteMigrationDetailListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into SiteMigrationDetailListResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static SiteMigrationDetailListResponse fromJson(
      String json,
      SiteMigrationDetailService service,
      SiteMigrationDetailListParams originalParams) {
    try {

      List<SiteMigrationDetailListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SiteMigrationDetailListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SiteMigrationDetailListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SiteMigrationDetailListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<SiteMigrationDetailListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<SiteMigrationDetailListItem> items() {
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
  public SiteMigrationDetailListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    SiteMigrationDetailListParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public SiteMigrationDetailListResponse autoPaginate() {
    return new SiteMigrationDetailListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<SiteMigrationDetailListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<SiteMigrationDetailListItem> {
    private SiteMigrationDetailListResponse currentPage = SiteMigrationDetailListResponse.this;
    private Iterator<SiteMigrationDetailListItem> currentIterator = currentPage.list.iterator();

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
    public SiteMigrationDetailListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class SiteMigrationDetailListItem {

    private SiteMigrationDetail siteMigrationDetail;

    public SiteMigrationDetail getSiteMigrationDetail() {
      return siteMigrationDetail;
    }

    public static SiteMigrationDetailListItem fromJson(String json) {
      SiteMigrationDetailListItem item = new SiteMigrationDetailListItem();

      String __siteMigrationDetailJson = JsonUtil.getObject(json, "site_migration_detail");
      if (__siteMigrationDetailJson != null) {
        item.siteMigrationDetail = SiteMigrationDetail.fromJson(__siteMigrationDetailJson);
      }

      return item;
    }
  }
}
