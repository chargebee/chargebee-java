package com.chargebee.core.responses.entitlementOverride;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.entitlementOverride.EntitlementOverride;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.EntitlementOverrideService;
import com.chargebee.core.models.entitlementOverride.params.EntitlementOverrideListEntitlementOverrideForSubscriptionParams;

/**
 * Immutable response object for EntitlementOverrideListEntitlementOverrideForSubscription
 * operation. Contains paginated list data with auto-pagination support.
 */
public final class EntitlementOverrideListEntitlementOverrideForSubscriptionResponse
    implements Iterable<
        EntitlementOverrideListEntitlementOverrideForSubscriptionResponse
            .EntitlementOverrideListEntitlementOverrideForSubscriptionItem> {

  private final List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final EntitlementOverrideService service;
  private final EntitlementOverrideListEntitlementOverrideForSubscriptionParams originalParams;
  private final boolean isAutoPaginate;

  private EntitlementOverrideListEntitlementOverrideForSubscriptionResponse(
      List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      EntitlementOverrideService service,
      EntitlementOverrideListEntitlementOverrideForSubscriptionParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private EntitlementOverrideListEntitlementOverrideForSubscriptionResponse(
      List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      EntitlementOverrideService service,
      EntitlementOverrideListEntitlementOverrideForSubscriptionParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into EntitlementOverrideListEntitlementOverrideForSubscriptionResponse
   * object (no service context). Use this when you only need to read a single page (no nextPage()).
   */
  public static EntitlementOverrideListEntitlementOverrideForSubscriptionResponse fromJson(
      String json) {
    try {

      List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementOverrideListEntitlementOverrideForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new EntitlementOverrideListEntitlementOverrideForSubscriptionResponse(
          list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EntitlementOverrideListEntitlementOverrideForSubscriptionResponse from JSON",
          e);
    }
  }

  /**
   * Parse JSON response into EntitlementOverrideListEntitlementOverrideForSubscriptionResponse
   * object with service context for pagination (enables nextPage(), autoPaginate()).
   */
  public static EntitlementOverrideListEntitlementOverrideForSubscriptionResponse fromJson(
      String json,
      EntitlementOverrideService service,
      EntitlementOverrideListEntitlementOverrideForSubscriptionParams originalParams,
      String subscriptionId) {
    try {

      List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(EntitlementOverrideListEntitlementOverrideForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new EntitlementOverrideListEntitlementOverrideForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EntitlementOverrideListEntitlementOverrideForSubscriptionResponse from JSON",
          e);
    }
  }

  /** Get the list from the response. */
  public List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> items() {
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
  public EntitlementOverrideListEntitlementOverrideForSubscriptionResponse nextPage()
      throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    EntitlementOverrideListEntitlementOverrideForSubscriptionParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.listEntitlementOverrideForSubscription(subscriptionId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public EntitlementOverrideListEntitlementOverrideForSubscriptionResponse autoPaginate() {
    return new EntitlementOverrideListEntitlementOverrideForSubscriptionResponse(
        list, nextOffset, subscriptionId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator
      implements Iterator<EntitlementOverrideListEntitlementOverrideForSubscriptionItem> {
    private EntitlementOverrideListEntitlementOverrideForSubscriptionResponse currentPage =
        EntitlementOverrideListEntitlementOverrideForSubscriptionResponse.this;
    private Iterator<EntitlementOverrideListEntitlementOverrideForSubscriptionItem>
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
    public EntitlementOverrideListEntitlementOverrideForSubscriptionItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class EntitlementOverrideListEntitlementOverrideForSubscriptionItem {

    private EntitlementOverride entitlementOverride;

    public EntitlementOverride getEntitlementOverride() {
      return entitlementOverride;
    }

    public static EntitlementOverrideListEntitlementOverrideForSubscriptionItem fromJson(
        String json) {
      EntitlementOverrideListEntitlementOverrideForSubscriptionItem item =
          new EntitlementOverrideListEntitlementOverrideForSubscriptionItem();

      String __entitlementOverrideJson = JsonUtil.getObject(json, "entitlement_override");
      if (__entitlementOverrideJson != null) {
        item.entitlementOverride = EntitlementOverride.fromJson(__entitlementOverrideJson);
      }

      return item;
    }
  }
}
