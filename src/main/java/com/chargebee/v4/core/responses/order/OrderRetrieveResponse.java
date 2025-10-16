package com.chargebee.v4.core.responses.order;

import com.chargebee.v4.core.models.order.Order;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OrderRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class OrderRetrieveResponse extends BaseResponse {
  private final Order order;

  private OrderRetrieveResponse(Order order, Response httpResponse) {
    super(httpResponse);

    this.order = order;
  }

  /** Parse JSON response into OrderRetrieveResponse object. */
  public static OrderRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OrderRetrieveResponse object with HTTP response. */
  public static OrderRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Order order = Order.fromJson(JsonUtil.getObject(json, "order"));

      return new OrderRetrieveResponse(order, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderRetrieveResponse from JSON", e);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
