package com.chargebee.v4.models.order.responses;

import com.chargebee.v4.models.order.Order;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for OrderCreateRefundableCreditNote operation. Contains the response
 * data from the API.
 */
public final class OrderCreateRefundableCreditNoteResponse extends BaseResponse {
  private final Order order;

  private OrderCreateRefundableCreditNoteResponse(Builder builder) {
    super(builder.httpResponse);

    this.order = builder.order;
  }

  /** Parse JSON response into OrderCreateRefundableCreditNoteResponse object. */
  public static OrderCreateRefundableCreditNoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into OrderCreateRefundableCreditNoteResponse object with HTTP response. */
  public static OrderCreateRefundableCreditNoteResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse OrderCreateRefundableCreditNoteResponse from JSON", e);
    }
  }

  /** Create a new builder for OrderCreateRefundableCreditNoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for OrderCreateRefundableCreditNoteResponse. */
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

    public OrderCreateRefundableCreditNoteResponse build() {
      return new OrderCreateRefundableCreditNoteResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }
}
