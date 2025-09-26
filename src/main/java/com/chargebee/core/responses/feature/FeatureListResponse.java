package com.chargebee.core.responses.feature;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.feature.Feature;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.FeatureService;
import com.chargebee.core.models.feature.params.FeatureListParams;

/**
 * Immutable response object for FeatureList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class FeatureListResponse implements Iterable<FeatureListResponse.FeatureListItem> {

  private final List<FeatureListItem> list;

  private final String nextOffset;

  private final FeatureService service;
  private final FeatureListParams originalParams;
  private final boolean isAutoPaginate;

  private FeatureListResponse(
      List<FeatureListItem> list,
      String nextOffset,
      FeatureService service,
      FeatureListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private FeatureListResponse(
      List<FeatureListItem> list,
      String nextOffset,
      FeatureService service,
      FeatureListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into FeatureListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static FeatureListResponse fromJson(String json) {
    try {

      List<FeatureListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(FeatureListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new FeatureListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into FeatureListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static FeatureListResponse fromJson(
      String json, FeatureService service, FeatureListParams originalParams) {
    try {

      List<FeatureListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(FeatureListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new FeatureListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FeatureListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<FeatureListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<FeatureListItem> items() {
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
  public FeatureListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    FeatureListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public FeatureListResponse autoPaginate() {
    return new FeatureListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<FeatureListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<FeatureListItem> {
    private FeatureListResponse currentPage = FeatureListResponse.this;
    private Iterator<FeatureListItem> currentIterator = currentPage.list.iterator();

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
    public FeatureListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class FeatureListItem {

    private Feature feature;

    public Feature getFeature() {
      return feature;
    }

    public static FeatureListItem fromJson(String json) {
      FeatureListItem item = new FeatureListItem();

      String __featureJson = JsonUtil.getObject(json, "feature");
      if (__featureJson != null) {
        item.feature = Feature.fromJson(__featureJson);
      }

      return item;
    }
  }
}
