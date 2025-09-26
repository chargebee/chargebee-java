package com.chargebee.core.responses.hostedPage;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.hostedPage.HostedPage;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.HostedPageService;
import com.chargebee.core.models.hostedPage.params.HostedPageListParams;

/**
 * Immutable response object for HostedPageList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class HostedPageListResponse
    implements Iterable<HostedPageListResponse.HostedPageListItem> {

  private final List<HostedPageListItem> list;

  private final String nextOffset;

  private final HostedPageService service;
  private final HostedPageListParams originalParams;
  private final boolean isAutoPaginate;

  private HostedPageListResponse(
      List<HostedPageListItem> list,
      String nextOffset,
      HostedPageService service,
      HostedPageListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private HostedPageListResponse(
      List<HostedPageListItem> list,
      String nextOffset,
      HostedPageService service,
      HostedPageListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into HostedPageListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static HostedPageListResponse fromJson(String json) {
    try {

      List<HostedPageListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(HostedPageListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new HostedPageListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into HostedPageListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static HostedPageListResponse fromJson(
      String json, HostedPageService service, HostedPageListParams originalParams) {
    try {

      List<HostedPageListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(HostedPageListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new HostedPageListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse HostedPageListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<HostedPageListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<HostedPageListItem> items() {
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
  public HostedPageListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    HostedPageListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public HostedPageListResponse autoPaginate() {
    return new HostedPageListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<HostedPageListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<HostedPageListItem> {
    private HostedPageListResponse currentPage = HostedPageListResponse.this;
    private Iterator<HostedPageListItem> currentIterator = currentPage.list.iterator();

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
    public HostedPageListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class HostedPageListItem {

    private HostedPage hostedPage;

    public HostedPage getHostedPage() {
      return hostedPage;
    }

    public static HostedPageListItem fromJson(String json) {
      HostedPageListItem item = new HostedPageListItem();

      String __hostedPageJson = JsonUtil.getObject(json, "hosted_page");
      if (__hostedPageJson != null) {
        item.hostedPage = HostedPage.fromJson(__hostedPageJson);
      }

      return item;
    }
  }
}
