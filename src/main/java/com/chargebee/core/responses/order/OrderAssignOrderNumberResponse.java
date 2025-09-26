package com.chargebee.core.responses.order;

import com.chargebee.core.models.order.Order;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for OrderAssignOrderNumber operation. Contains the response data from
 * the API.
 */
public final class OrderAssignOrderNumberResponse {

  private final Order order;

  private OrderAssignOrderNumberResponse(Builder builder) {

    this.order = builder.order;
  }

  /** Parse JSON response into OrderAssignOrderNumberResponse object. */
  public static OrderAssignOrderNumberResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderAssignOrderNumberResponse from JSON", e);
    }
  }

  /** Create a new builder for OrderAssignOrderNumberResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OrderAssignOrderNumberResponse. */
  public static class Builder {

    private Order order;

    private Builder() {}

    public Builder order(Order order) {
      this.order = order;
      return this;
    }

    public OrderAssignOrderNumberResponse build() {
      return new OrderAssignOrderNumberResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
