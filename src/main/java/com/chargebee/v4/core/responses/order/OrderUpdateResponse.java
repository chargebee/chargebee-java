package com.chargebee.v4.core.responses.order;

import com.chargebee.v4.core.models.order.Order;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for OrderUpdate operation. Contains the response data from the API. */
public final class OrderUpdateResponse extends BaseResponse {
  private final Order order;

  private OrderUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.order = builder.order;
  }

  /** Parse JSON response into OrderUpdateResponse object. */
  public static OrderUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OrderUpdateResponse object with HTTP response. */
  public static OrderUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      builder.httpResponse(httpResponse);
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

    public OrderUpdateResponse build() {
      return new OrderUpdateResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
