package com.chargebee.v4.core.responses.order;

import com.chargebee.v4.core.models.order.Order;

import com.chargebee.v4.internal.JsonUtil;

/** Immutable response object for OrderUpdate operation. Contains the response data from the API. */
public final class OrderUpdateResponse {

  private final Order order;

  private OrderUpdateResponse(Builder builder) {

    this.order = builder.order;
  }

  /** Parse JSON response into OrderUpdateResponse object. */
  public static OrderUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for OrderUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OrderUpdateResponse. */
  public static class Builder {

    private Order order;

    private Builder() {}

    public Builder order(Order order) {
      this.order = order;
      return this;
    }

    public OrderUpdateResponse build() {
      return new OrderUpdateResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
