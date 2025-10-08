package com.chargebee.v4.core.responses.order;

import com.chargebee.v4.core.models.order.Order;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for OrderDelete operation. Contains the response data from the API. */
public final class OrderDeleteResponse {

  private final Order order;

  private final Response httpResponse;

  private OrderDeleteResponse(Builder builder) {

    this.order = builder.order;

    this.httpResponse = builder.httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
