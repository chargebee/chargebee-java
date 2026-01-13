package com.chargebee.v4.models.order.responses;

import com.chargebee.v4.models.order.Order;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for OrderCancel operation. Contains the response data from the API. */
public final class OrderCancelResponse extends BaseResponse {
  private final Order order;

  private OrderCancelResponse(Builder builder) {
    super(builder.httpResponse);

    this.order = builder.order;
  }

  /** Parse JSON response into OrderCancelResponse object. */
  public static OrderCancelResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OrderCancelResponse object with HTTP response. */
  public static OrderCancelResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderCancelResponse from JSON", e);
    }
  }

  /** Create a new builder for OrderCancelResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OrderCancelResponse. */
  public static class Builder {

    private Order order;

    private Response httpResponse;

    private Builder() {}

    public Builder order(Order order) {
      this.order = order;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public OrderCancelResponse build() {
      return new OrderCancelResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "OrderCancelResponse{" + "order=" + order + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OrderCancelResponse that = (OrderCancelResponse) o;
    return java.util.Objects.equals(order, that.order);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(order);
  }
}
