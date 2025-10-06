package com.chargebee.v4.core.responses.order;

import com.chargebee.v4.core.models.order.Order;

import com.chargebee.v4.internal.JsonUtil;

/** Immutable response object for OrderResend operation. Contains the response data from the API. */
public final class OrderResendResponse {

  private final Order order;

  private OrderResendResponse(Builder builder) {

    this.order = builder.order;
  }

  /** Parse JSON response into OrderResendResponse object. */
  public static OrderResendResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderResendResponse from JSON", e);
    }
  }

  /** Create a new builder for OrderResendResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OrderResendResponse. */
  public static class Builder {

    private Order order;

    private Builder() {}

    public Builder order(Order order) {
      this.order = order;
      return this;
    }

    public OrderResendResponse build() {
      return new OrderResendResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
