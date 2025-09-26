package com.chargebee.core.responses.subscription;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.discount.Discount;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.SubscriptionService;
import com.chargebee.core.models.subscription.params.SubscriptionListDiscountsParams;

/**
 * Immutable response object for SubscriptionListDiscounts operation. Contains paginated list data
 * with auto-pagination support.
 */
public final class SubscriptionListDiscountsResponse
    implements Iterable<SubscriptionListDiscountsResponse.SubscriptionListDiscountsItem> {

  private final List<SubscriptionListDiscountsItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final SubscriptionService service;
  private final SubscriptionListDiscountsParams originalParams;
  private final boolean isAutoPaginate;

  private SubscriptionListDiscountsResponse(
      List<SubscriptionListDiscountsItem> list,
      String nextOffset,
      String subscriptionId,
      SubscriptionService service,
      SubscriptionListDiscountsParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private SubscriptionListDiscountsResponse(
      List<SubscriptionListDiscountsItem> list,
      String nextOffset,
      String subscriptionId,
      SubscriptionService service,
      SubscriptionListDiscountsParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into SubscriptionListDiscountsResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static SubscriptionListDiscountsResponse fromJson(String json) {
    try {

      List<SubscriptionListDiscountsItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionListDiscountsItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionListDiscountsResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SubscriptionListDiscountsResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into SubscriptionListDiscountsResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static SubscriptionListDiscountsResponse fromJson(
      String json,
      SubscriptionService service,
      SubscriptionListDiscountsParams originalParams,
      String subscriptionId) {
    try {

      List<SubscriptionListDiscountsItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(SubscriptionListDiscountsItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new SubscriptionListDiscountsResponse(
          list, nextOffset, subscriptionId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse SubscriptionListDiscountsResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<SubscriptionListDiscountsItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<SubscriptionListDiscountsItem> items() {
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
  public SubscriptionListDiscountsResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    SubscriptionListDiscountsParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.listDiscounts(subscriptionId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public SubscriptionListDiscountsResponse autoPaginate() {
    return new SubscriptionListDiscountsResponse(
        list, nextOffset, subscriptionId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<SubscriptionListDiscountsItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<SubscriptionListDiscountsItem> {
    private SubscriptionListDiscountsResponse currentPage = SubscriptionListDiscountsResponse.this;
    private Iterator<SubscriptionListDiscountsItem> currentIterator = currentPage.list.iterator();

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
    public SubscriptionListDiscountsItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class SubscriptionListDiscountsItem {

    private Discount discount;

    public Discount getDiscount() {
      return discount;
    }

    public static SubscriptionListDiscountsItem fromJson(String json) {
      SubscriptionListDiscountsItem item = new SubscriptionListDiscountsItem();

      String __discountJson = JsonUtil.getObject(json, "discount");
      if (__discountJson != null) {
        item.discount = Discount.fromJson(__discountJson);
      }

      return item;
    }
  }
}
