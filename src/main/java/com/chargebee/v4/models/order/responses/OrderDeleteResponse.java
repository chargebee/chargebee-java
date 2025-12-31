package com.chargebee.v4.models.order.responses;

import com.chargebee.v4.models.order.Order;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for OrderDelete operation. Contains the response data from the API. */
public final class OrderDeleteResponse extends BaseResponse {
  private final Order order;

  private OrderDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.order = builder.order;
  }

  /** Parse JSON response into OrderDeleteResponse object. */
  public static OrderDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OrderDeleteResponse object with HTTP response. */
  public static OrderDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      builder.httpResponse(httpResponse);
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

    public OrderDeleteResponse build() {
      return new OrderDeleteResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "OrderDeleteResponse{" + "order=" + order + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderDeleteResponse that = (OrderDeleteResponse) o;
    return java.util.Objects.equals(order, that.order);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(order);
  }
}
