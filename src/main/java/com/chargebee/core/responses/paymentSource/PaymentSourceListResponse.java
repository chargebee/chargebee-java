package com.chargebee.core.responses.paymentSource;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.paymentSource.PaymentSource;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.PaymentSourceService;
import com.chargebee.core.models.paymentSource.params.PaymentSourceListParams;

/**
 * Immutable response object for PaymentSourceList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class PaymentSourceListResponse
    implements Iterable<PaymentSourceListResponse.PaymentSourceListItem> {

  private final List<PaymentSourceListItem> list;

  private final String nextOffset;

  private final PaymentSourceService service;
  private final PaymentSourceListParams originalParams;
  private final boolean isAutoPaginate;

  private PaymentSourceListResponse(
      List<PaymentSourceListItem> list,
      String nextOffset,
      PaymentSourceService service,
      PaymentSourceListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private PaymentSourceListResponse(
      List<PaymentSourceListItem> list,
      String nextOffset,
      PaymentSourceService service,
      PaymentSourceListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into PaymentSourceListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static PaymentSourceListResponse fromJson(String json) {
    try {

      List<PaymentSourceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PaymentSourceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PaymentSourceListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSourceListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PaymentSourceListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static PaymentSourceListResponse fromJson(
      String json, PaymentSourceService service, PaymentSourceListParams originalParams) {
    try {

      List<PaymentSourceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PaymentSourceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PaymentSourceListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PaymentSourceListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PaymentSourceListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<PaymentSourceListItem> items() {
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
  public PaymentSourceListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    PaymentSourceListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public PaymentSourceListResponse autoPaginate() {
    return new PaymentSourceListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<PaymentSourceListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<PaymentSourceListItem> {
    private PaymentSourceListResponse currentPage = PaymentSourceListResponse.this;
    private Iterator<PaymentSourceListItem> currentIterator = currentPage.list.iterator();

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
    public PaymentSourceListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class PaymentSourceListItem {

    private PaymentSource paymentSource;

    public PaymentSource getPaymentSource() {
      return paymentSource;
    }

    public static PaymentSourceListItem fromJson(String json) {
      PaymentSourceListItem item = new PaymentSourceListItem();

      String __paymentSourceJson = JsonUtil.getObject(json, "payment_source");
      if (__paymentSourceJson != null) {
        item.paymentSource = PaymentSource.fromJson(__paymentSourceJson);
      }

      return item;
    }
  }
}
