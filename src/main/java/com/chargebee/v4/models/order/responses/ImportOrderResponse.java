package com.chargebee.v4.models.order.responses;

import com.chargebee.v4.models.order.Order;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for ImportOrder operation. Contains the response data from the API. */
public final class ImportOrderResponse extends BaseResponse {
  private final Order order;

  private ImportOrderResponse(Builder builder) {
    super(builder.httpResponse);

    this.order = builder.order;
  }

  /** Parse JSON response into ImportOrderResponse object. */
  public static ImportOrderResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ImportOrderResponse object with HTTP response. */
  public static ImportOrderResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __orderJson = JsonUtil.getObject(json, "order");
      if (__orderJson != null) {
        builder.order(Order.fromJson(__orderJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ImportOrderResponse from JSON", e);
    }
  }

  /** Create a new builder for ImportOrderResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ImportOrderResponse. */
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

    public ImportOrderResponse build() {
      return new ImportOrderResponse(this);
    }
  }

  /** Get the order from the response. */
  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "ImportOrderResponse{" + "order=" + order + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ImportOrderResponse that = (ImportOrderResponse) o;
    return java.util.Objects.equals(order, that.order);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(order);
  }
}
