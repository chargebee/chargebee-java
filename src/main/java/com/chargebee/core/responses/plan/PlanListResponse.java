package com.chargebee.core.responses.plan;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.plan.Plan;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.PlanService;
import com.chargebee.core.models.plan.params.PlanListParams;

/**
 * Immutable response object for PlanList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class PlanListResponse implements Iterable<PlanListResponse.PlanListItem> {

  private final List<PlanListItem> list;

  private final String nextOffset;

  private final PlanService service;
  private final PlanListParams originalParams;
  private final boolean isAutoPaginate;

  private PlanListResponse(
      List<PlanListItem> list,
      String nextOffset,
      PlanService service,
      PlanListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private PlanListResponse(
      List<PlanListItem> list,
      String nextOffset,
      PlanService service,
      PlanListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into PlanListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static PlanListResponse fromJson(String json) {
    try {

      List<PlanListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PlanListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PlanListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PlanListResponse object with service context for pagination (enables
   * nextPage(), autoPaginate()).
   */
  public static PlanListResponse fromJson(
      String json, PlanService service, PlanListParams originalParams) {
    try {

      List<PlanListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PlanListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PlanListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PlanListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PlanListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<PlanListItem> items() {
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
  public PlanListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    PlanListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public PlanListResponse autoPaginate() {
    return new PlanListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<PlanListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<PlanListItem> {
    private PlanListResponse currentPage = PlanListResponse.this;
    private Iterator<PlanListItem> currentIterator = currentPage.list.iterator();

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
    public PlanListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class PlanListItem {

    private Plan plan;

    public Plan getPlan() {
      return plan;
    }

    public static PlanListItem fromJson(String json) {
      PlanListItem item = new PlanListItem();

      String __planJson = JsonUtil.getObject(json, "plan");
      if (__planJson != null) {
        item.plan = Plan.fromJson(__planJson);
      }

      return item;
    }
  }
}
