package com.chargebee.v4.core.responses.order;

import com.chargebee.v4.core.models.order.Order;

import com.chargebee.v4.internal.JsonUtil;

/** Immutable response object for OrderDelete operation. Contains the response data from the API. */
public final class OrderDeleteResponse {

  private final Order order;

  private OrderDeleteResponse(Builder builder) {

    this.order = builder.order;
  }

  /** Parse JSON response into OrderDeleteResponse object. */
  public static OrderDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for OrderDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OrderDeleteResponse. */
  public static class Builder {

    private Order order;

    private Builder() {}

    public Builder order(Order order) {
      this.order = order;
      return this;
    }

    public OrderDeleteResponse build() {
      return new OrderDeleteResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
