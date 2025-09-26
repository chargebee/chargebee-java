package com.chargebee.core.responses.subscription;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.subscription.Subscription;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.SubscriptionService;
import com.chargebee.core.models.subscription.params.SubscriptionSubscriptionsForCustomerParams;

/**
 * Immutable response object for SubscriptionSubscriptionsForCustomer operation. Contains paginated
 * list data with auto-pagination support.
 */
public final class SubscriptionSubscriptionsForCustomerResponse
    implements Iterable<
        SubscriptionSubscriptionsForCustomerResponse.SubscriptionSubscriptionsForCustomerItem> {

  private final List<SubscriptionSubscriptionsForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final SubscriptionService service;
  private final SubscriptionSubscriptionsForCustomerParams originalParams;
  private final boolean isAutoPaginate;

  private SubscriptionSubscriptionsForCustomerResponse(
      List<SubscriptionSubscriptionsForCustomerItem> list,
      String nextOffset,
      String customerId,
      SubscriptionService service,
      SubscriptionSubscriptionsForCustomerParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private SubscriptionSubscriptionsForCustomerResponse(
      List<SubscriptionSubscriptionsForCustomerItem> list,
      String nextOffset,
      String customerId,
      SubscriptionService service,
      SubscriptionSubscriptionsForCustomerParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into SubscriptionSubscriptionsForCustomerResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static SubscriptionSubscriptionsForCustomerResponse fromJson(String json) {
    try {

      List<SubscriptionSubscriptionsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionSubscriptionsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionSubscriptionsForCustomerResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionSubscriptionsForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into SubscriptionSubscriptionsForCustomerResponse object with service
   * context for pagination (enables nextPage(), autoPaginate()).
   */
  public static SubscriptionSubscriptionsForCustomerResponse fromJson(
      String json,
      SubscriptionService service,
      SubscriptionSubscriptionsForCustomerParams originalParams,
      String customerId) {
    try {

      List<SubscriptionSubscriptionsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionSubscriptionsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionSubscriptionsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionSubscriptionsForCustomerResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionSubscriptionsForCustomerItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<SubscriptionSubscriptionsForCustomerItem> items() {
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
  public SubscriptionSubscriptionsForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    SubscriptionSubscriptionsForCustomerParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.subscriptionsForCustomer(customerId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public SubscriptionSubscriptionsForCustomerResponse autoPaginate() {
    return new SubscriptionSubscriptionsForCustomerResponse(
        list, nextOffset, customerId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<SubscriptionSubscriptionsForCustomerItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<SubscriptionSubscriptionsForCustomerItem> {
    private SubscriptionSubscriptionsForCustomerResponse currentPage =
        SubscriptionSubscriptionsForCustomerResponse.this;
    private Iterator<SubscriptionSubscriptionsForCustomerItem> currentIterator =
        currentPage.list.iterator();

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
    public SubscriptionSubscriptionsForCustomerItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class SubscriptionSubscriptionsForCustomerItem {

    private Subscription subscription;

    public Subscription getSubscription() {
      return subscription;
    }

    public static SubscriptionSubscriptionsForCustomerItem fromJson(String json) {
      SubscriptionSubscriptionsForCustomerItem item =
          new SubscriptionSubscriptionsForCustomerItem();

      String __subscriptionJson = JsonUtil.getObject(json, "subscription");
      if (__subscriptionJson != null) {
        item.subscription = Subscription.fromJson(__subscriptionJson);
      }

      return item;
    }
  }
}
