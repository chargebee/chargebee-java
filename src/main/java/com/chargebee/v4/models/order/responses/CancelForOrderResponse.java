package com.chargebee.v4.models.order.responses;

import com.chargebee.v4.models.order.Order;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CancelForOrder operation. Contains the response data from the API.
 */
public final class CancelForOrderResponse extends BaseResponse {
  private final Order order;

  private CancelForOrderResponse(Builder builder) {
    super(builder.httpResponse);

    this.order = builder.order;
  }

  /** Parse JSON response into CancelForOrderResponse object. */
  public static CancelForOrderResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CancelForOrderResponse object with HTTP response. */
  public static CancelForOrderResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CancelForOrderResponse from JSON", e);
    }
  }

  /** Create a new builder for CancelForOrderResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CancelForOrderResponse. */
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

    public CancelForOrderResponse build() {
      return new CancelForOrderResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
