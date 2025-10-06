package com.chargebee.core.responses.order;

import java.util.List;

import com.chargebee.core.models.order.Order;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.OrderService;
import com.chargebee.core.models.order.params.OrderListParams;

/** Immutable response object for OrderList operation. Contains paginated list data. */
public final class OrderListResponse {

  private final List<OrderListItem> list;

  private final String nextOffset;

  private final OrderService service;
  private final OrderListParams originalParams;

  private OrderListResponse(
      List<OrderListItem> list,
      String nextOffset,
      OrderService service,
      OrderListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into OrderListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static OrderListResponse fromJson(String json) {
    try {

      List<OrderListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OrderListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OrderListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into OrderListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static OrderListResponse fromJson(
      String json, OrderService service, OrderListParams originalParams) {
    try {

      List<OrderListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(OrderListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new OrderListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<OrderListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<OrderListItem> items() {
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
  public OrderListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    OrderListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class OrderListItem {

    private Order order;

    public Order getOrder() {
      return order;
    }

    public static OrderListItem fromJson(String json) {
      OrderListItem item = new OrderListItem();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        item.order = Order.fromJson(__orderJson);
      }

      return item;
    }
  }
}
