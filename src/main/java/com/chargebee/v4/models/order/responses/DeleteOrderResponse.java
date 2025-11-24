package com.chargebee.v4.models.order.responses;

import com.chargebee.v4.models.order.Order;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for DeleteOrder operation. Contains the response data from the API. */
public final class DeleteOrderResponse extends BaseResponse {
  private final Order order;

  private DeleteOrderResponse(Builder builder) {
    super(builder.httpResponse);

    this.order = builder.order;
  }

  /** Parse JSON response into DeleteOrderResponse object. */
  public static DeleteOrderResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteOrderResponse object with HTTP response. */
  public static DeleteOrderResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteOrderResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteOrderResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteOrderResponse. */
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

    public DeleteOrderResponse build() {
      return new DeleteOrderResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
