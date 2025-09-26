package com.chargebee.core.responses.omnichannelSubscription;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.omnichannelSubscription.OmnichannelSubscription;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.OmnichannelSubscriptionService;
import com.chargebee.core.models.omnichannelSubscription.params.OmnichannelSubscriptionListParams;

/**
 * Immutable response object for OmnichannelSubscriptionList operation. Contains paginated list data
 * with auto-pagination support.
 */
public final class OmnichannelSubscriptionListResponse
    implements Iterable<OmnichannelSubscriptionListResponse.OmnichannelSubscriptionListItem> {

  private final List<OmnichannelSubscriptionListItem> list;

  private final String nextOffset;

  private final OmnichannelSubscriptionService service;
  private final OmnichannelSubscriptionListParams originalParams;
  private final boolean isAutoPaginate;

  private OmnichannelSubscriptionListResponse(
      List<OmnichannelSubscriptionListItem> list,
      String nextOffset,
      OmnichannelSubscriptionService service,
      OmnichannelSubscriptionListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private OmnichannelSubscriptionListResponse(
      List<OmnichannelSubscriptionListItem> list,
      String nextOffset,
      OmnichannelSubscriptionService service,
      OmnichannelSubscriptionListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into OmnichannelSubscriptionListResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static OmnichannelSubscriptionListResponse fromJson(String json) {
    try {

      List<OmnichannelSubscriptionListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelSubscriptionListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into OmnichannelSubscriptionListResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static OmnichannelSubscriptionListResponse fromJson(
      String json,
      OmnichannelSubscriptionService service,
      OmnichannelSubscriptionListParams originalParams) {
    try {

      List<OmnichannelSubscriptionListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelSubscriptionListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<OmnichannelSubscriptionListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<OmnichannelSubscriptionListItem> items() {
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
  public OmnichannelSubscriptionListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    OmnichannelSubscriptionListParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public OmnichannelSubscriptionListResponse autoPaginate() {
    return new OmnichannelSubscriptionListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<OmnichannelSubscriptionListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<OmnichannelSubscriptionListItem> {
    private OmnichannelSubscriptionListResponse currentPage =
        OmnichannelSubscriptionListResponse.this;
    private Iterator<OmnichannelSubscriptionListItem> currentIterator = currentPage.list.iterator();

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
    public OmnichannelSubscriptionListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class OmnichannelSubscriptionListItem {

    private OmnichannelSubscription omnichannelSubscription;

    public OmnichannelSubscription getOmnichannelSubscription() {
      return omnichannelSubscription;
    }

    public static OmnichannelSubscriptionListItem fromJson(String json) {
      OmnichannelSubscriptionListItem item = new OmnichannelSubscriptionListItem();

      String __omnichannelSubscriptionJson = JsonUtil.getObject(json, "omnichannel_subscription");
      if (__omnichannelSubscriptionJson != null) {
        item.omnichannelSubscription =
            OmnichannelSubscription.fromJson(__omnichannelSubscriptionJson);
      }

      return item;
    }
  }
}
