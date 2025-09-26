package com.chargebee.core.responses.omnichannelSubscriptionItem;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.omnichannelSubscriptionItemScheduledChange.OmnichannelSubscriptionItemScheduledChange;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.OmnichannelSubscriptionItemService;
import com.chargebee.core.models.omnichannelSubscriptionItem.params.OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams;

/**
 * Immutable response object for OmnichannelSubscriptionItemListOmniSubItemScheduleChanges
 * operation. Contains paginated list data with auto-pagination support.
 */
public final class OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse
    implements Iterable<
        OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse
            .OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> {

  private final List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> list;

  private final String nextOffset;

  private final String omnichannelSubscriptionItemId;

  private final OmnichannelSubscriptionItemService service;
  private final OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams originalParams;
  private final boolean isAutoPaginate;

  private OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse(
      List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> list,
      String nextOffset,
      String omnichannelSubscriptionItemId,
      OmnichannelSubscriptionItemService service,
      OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.omnichannelSubscriptionItemId = omnichannelSubscriptionItemId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse(
      List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> list,
      String nextOffset,
      String omnichannelSubscriptionItemId,
      OmnichannelSubscriptionItemService service,
      OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.omnichannelSubscriptionItemId = omnichannelSubscriptionItemId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse
   * object (no service context). Use this when you only need to read a single page (no nextPage()).
   */
  public static OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse fromJson(
      String json) {
    try {

      List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse(
          list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse from JSON",
          e);
    }
  }

  /**
   * Parse JSON response into OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse
   * object with service context for pagination (enables nextPage(), autoPaginate()).
   */
  public static OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse fromJson(
      String json,
      OmnichannelSubscriptionItemService service,
      OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams originalParams,
      String omnichannelSubscriptionItemId) {
    try {

      List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse(
          list, nextOffset, omnichannelSubscriptionItemId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse from JSON",
          e);
    }
  }

  /** Get the list from the response. */
  public List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> items() {
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
  public OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse nextPage()
      throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    OmnichannelSubscriptionItemListOmniSubItemScheduleChangesParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.listOmniSubItemScheduleChanges(omnichannelSubscriptionItemId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse autoPaginate() {
    return new OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse(
        list, nextOffset, omnichannelSubscriptionItemId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator
      implements Iterator<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem> {
    private OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse currentPage =
        OmnichannelSubscriptionItemListOmniSubItemScheduleChangesResponse.this;
    private Iterator<OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem>
        currentIterator = currentPage.list.iterator();

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
    public OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem {

    private OmnichannelSubscriptionItemScheduledChange omnichannelSubscriptionItemScheduledChange;

    public OmnichannelSubscriptionItemScheduledChange
        getOmnichannelSubscriptionItemScheduledChange() {
      return omnichannelSubscriptionItemScheduledChange;
    }

    public static OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem fromJson(
        String json) {
      OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem item =
          new OmnichannelSubscriptionItemListOmniSubItemScheduleChangesItem();

      String __omnichannelSubscriptionItemScheduledChangeJson =
          JsonUtil.getObject(json, "omnichannel_subscription_item_scheduled_change");
      if (__omnichannelSubscriptionItemScheduledChangeJson != null) {
        item.omnichannelSubscriptionItemScheduledChange =
            OmnichannelSubscriptionItemScheduledChange.fromJson(
                __omnichannelSubscriptionItemScheduledChangeJson);
      }

      return item;
    }
  }
}
