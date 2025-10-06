package com.chargebee.v4.core.responses.order;

import com.chargebee.v4.core.models.order.Order;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for OrderRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class OrderRetrieveResponse {

  private final Order order;

  private OrderRetrieveResponse(Order order) {

    this.order = order;
  }

  /** Parse JSON response into OrderRetrieveResponse object. */
  public static OrderRetrieveResponse fromJson(String json) {
    try {

      Order order = Order.fromJson(JsonUtil.getObject(json, "order"));

      return new OrderRetrieveResponse(order);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderRetrieveResponse from JSON", e);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
