package com.chargebee.core.responses.order;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.order.Order;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.OrderService;
import com.chargebee.core.models.order.params.OrderOrdersForInvoiceParams;

/**
 * Immutable response object for OrderOrdersForInvoice operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class OrderOrdersForInvoiceResponse
    implements Iterable<OrderOrdersForInvoiceResponse.OrderOrdersForInvoiceItem> {

  private final List<OrderOrdersForInvoiceItem> list;

  private final String nextOffset;

  private final String invoiceId;

  private final OrderService service;
  private final OrderOrdersForInvoiceParams originalParams;
  private final boolean isAutoPaginate;

  private OrderOrdersForInvoiceResponse(
      List<OrderOrdersForInvoiceItem> list,
      String nextOffset,
      String invoiceId,
      OrderService service,
      OrderOrdersForInvoiceParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.invoiceId = invoiceId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private OrderOrdersForInvoiceResponse(
      List<OrderOrdersForInvoiceItem> list,
      String nextOffset,
      String invoiceId,
      OrderService service,
      OrderOrdersForInvoiceParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.invoiceId = invoiceId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into OrderOrdersForInvoiceResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static OrderOrdersForInvoiceResponse fromJson(String json) {
    try {

      List<OrderOrdersForInvoiceItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OrderOrdersForInvoiceItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OrderOrdersForInvoiceResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderOrdersForInvoiceResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into OrderOrdersForInvoiceResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static OrderOrdersForInvoiceResponse fromJson(
      String json,
      OrderService service,
      OrderOrdersForInvoiceParams originalParams,
      String invoiceId) {
    try {

      List<OrderOrdersForInvoiceItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OrderOrdersForInvoiceItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OrderOrdersForInvoiceResponse(
          list, nextOffset, invoiceId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderOrdersForInvoiceResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<OrderOrdersForInvoiceItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<OrderOrdersForInvoiceItem> items() {
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
  public OrderOrdersForInvoiceResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    OrderOrdersForInvoiceParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.ordersForInvoice(invoiceId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public OrderOrdersForInvoiceResponse autoPaginate() {
    return new OrderOrdersForInvoiceResponse(
        list, nextOffset, invoiceId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<OrderOrdersForInvoiceItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<OrderOrdersForInvoiceItem> {
    private OrderOrdersForInvoiceResponse currentPage = OrderOrdersForInvoiceResponse.this;
    private Iterator<OrderOrdersForInvoiceItem> currentIterator = currentPage.list.iterator();

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
    public OrderOrdersForInvoiceItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class OrderOrdersForInvoiceItem {

    private Order order;

    public Order getOrder() {
      return order;
    }

    public static OrderOrdersForInvoiceItem fromJson(String json) {
      OrderOrdersForInvoiceItem item = new OrderOrdersForInvoiceItem();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        item.order = Order.fromJson(__orderJson);
      }

      return item;
    }
  }
}
