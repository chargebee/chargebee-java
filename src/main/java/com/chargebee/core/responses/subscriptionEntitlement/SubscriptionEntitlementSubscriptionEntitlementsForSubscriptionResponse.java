package com.chargebee.core.responses.subscriptionEntitlement;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.subscriptionEntitlement.SubscriptionEntitlement;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.SubscriptionEntitlementService;
import com.chargebee.core.models.subscriptionEntitlement.params.SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams;

/**
 * Immutable response object for SubscriptionEntitlementSubscriptionEntitlementsForSubscription
 * operation. Contains paginated list data with auto-pagination support.
 */
public final class SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse
    implements Iterable<
        SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse
            .SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> {

  private final List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final SubscriptionEntitlementService service;
  private final SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams originalParams;
  private final boolean isAutoPaginate;

  private SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse(
      List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      SubscriptionEntitlementService service,
      SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse(
      List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      SubscriptionEntitlementService service,
      SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse
   * object (no service context). Use this when you only need to read a single page (no nextPage()).
   */
  public static SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse fromJson(
      String json) {
    try {

      List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse(
          list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse from JSON",
          e);
    }
  }

  /**
   * Parse JSON response into SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse
   * object with service context for pagination (enables nextPage(), autoPaginate()).
   */
  public static SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse fromJson(
      String json,
      SubscriptionEntitlementService service,
      SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams originalParams,
      String subscriptionId) {
    try {

      List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse from JSON",
          e);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> items() {
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
  public SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse nextPage()
      throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.subscriptionEntitlementsForSubscription(subscriptionId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse autoPaginate() {
    return new SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse(
        list, nextOffset, subscriptionId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator
      implements Iterator<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem> {
    private SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse currentPage =
        SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionResponse.this;
    private Iterator<SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem>
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
    public SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem {

    private SubscriptionEntitlement subscriptionEntitlement;

    public SubscriptionEntitlement getSubscriptionEntitlement() {
      return subscriptionEntitlement;
    }

    public static SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem fromJson(
        String json) {
      SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem item =
          new SubscriptionEntitlementSubscriptionEntitlementsForSubscriptionItem();

      String __subscriptionEntitlementJson = JsonUtil.getObject(json, "subscription_entitlement");
      if (__subscriptionEntitlementJson != null) {
        item.subscriptionEntitlement =
            SubscriptionEntitlement.fromJson(__subscriptionEntitlementJson);
      }

      return item;
    }
  }
}
