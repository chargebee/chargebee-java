package com.chargebee.v4.core.responses.order;

import java.util.List;

import com.chargebee.v4.core.models.order.Order;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.OrderService;
import com.chargebee.v4.core.models.order.params.OrderOrdersForInvoiceParams;

/** Immutable response object for OrderOrdersForInvoice operation. Contains paginated list data. */
public final class OrderOrdersForInvoiceResponse {

  private final List<OrderOrdersForInvoiceItem> list;

  private final String nextOffset;

  private final String invoiceId;

  private final OrderService service;
  private final OrderOrdersForInvoiceParams originalParams;
  private final Response httpResponse;

  private OrderOrdersForInvoiceResponse(
      List<OrderOrdersForInvoiceItem> list,
      String nextOffset,
      String invoiceId,
      OrderService service,
      OrderOrdersForInvoiceParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.invoiceId = invoiceId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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

      return new OrderOrdersForInvoiceResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderOrdersForInvoiceResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into OrderOrdersForInvoiceResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static OrderOrdersForInvoiceResponse fromJson(
      String json,
      OrderService service,
      OrderOrdersForInvoiceParams originalParams,
      String invoiceId,
      Response httpResponse) {
    try {

      List<OrderOrdersForInvoiceItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OrderOrdersForInvoiceItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OrderOrdersForInvoiceResponse(
          list, nextOffset, invoiceId, service, originalParams, httpResponse);
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
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    OrderOrdersForInvoiceParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : OrderOrdersForInvoiceParams.builder())
            .offset(nextOffset)
            .build();

    return service.ordersForInvoice(invoiceId, nextParams);
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
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
