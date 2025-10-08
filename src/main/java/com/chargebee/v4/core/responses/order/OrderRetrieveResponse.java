package com.chargebee.v4.core.responses.order;

import com.chargebee.v4.core.models.order.Order;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OrderRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class OrderRetrieveResponse {

  private final Order order;

  private final Response httpResponse;

  private OrderRetrieveResponse(Order order, Response httpResponse) {

    this.order = order;

    this.httpResponse = httpResponse;
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
}
