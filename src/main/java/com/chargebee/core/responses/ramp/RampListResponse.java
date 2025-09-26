package com.chargebee.core.responses.ramp;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.ramp.Ramp;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.RampService;
import com.chargebee.core.models.ramp.params.RampListParams;

/**
 * Immutable response object for RampList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class RampListResponse implements Iterable<RampListResponse.RampListItem> {

  private final List<RampListItem> list;

  private final String nextOffset;

  private final RampService service;
  private final RampListParams originalParams;
  private final boolean isAutoPaginate;

  private RampListResponse(
      List<RampListItem> list,
      String nextOffset,
      RampService service,
      RampListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private RampListResponse(
      List<RampListItem> list,
      String nextOffset,
      RampService service,
      RampListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into RampListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static RampListResponse fromJson(String json) {
    try {

      List<RampListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(RampListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new RampListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into RampListResponse object with service context for pagination (enables
   * nextPage(), autoPaginate()).
   */
  public static RampListResponse fromJson(
      String json, RampService service, RampListParams originalParams) {
    try {

      List<RampListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(RampListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new RampListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RampListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<RampListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<RampListItem> items() {
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
  public RampListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    RampListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public RampListResponse autoPaginate() {
    return new RampListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<RampListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<RampListItem> {
    private RampListResponse currentPage = RampListResponse.this;
    private Iterator<RampListItem> currentIterator = currentPage.list.iterator();

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
    public RampListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class RampListItem {

    private Ramp ramp;

    public Ramp getRamp() {
      return ramp;
    }

    public static RampListItem fromJson(String json) {
      RampListItem item = new RampListItem();

      String __rampJson = JsonUtil.getObject(json, "ramp");
      if (__rampJson != null) {
        item.ramp = Ramp.fromJson(__rampJson);
      }

      return item;
    }
  }
}
