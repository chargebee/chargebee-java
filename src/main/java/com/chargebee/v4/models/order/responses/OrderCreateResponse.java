package com.chargebee.v4.models.order.responses;

import com.chargebee.v4.models.order.Order;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for OrderCreate operation. Contains the response data from the API. */
public final class OrderCreateResponse extends BaseResponse {
  private final Order order;

  private OrderCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.order = builder.order;
  }

  /** Parse JSON response into OrderCreateResponse object. */
  public static OrderCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OrderCreateResponse object with HTTP response. */
  public static OrderCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse OrderCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for OrderCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OrderCreateResponse. */
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

    public OrderCreateResponse build() {
      return new OrderCreateResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
