package com.chargebee.v4.core.responses.product;

import com.chargebee.v4.core.models.product.Product;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ProductCreate operation. Contains the response data from the API.
 */
public final class ProductCreateResponse {

  private final Product product;

  private final Response httpResponse;

  private ProductCreateResponse(Builder builder) {

    this.product = builder.product;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into ProductCreateResponse object. */
  public static ProductCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ProductCreateResponse object with HTTP response. */
  public static ProductCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __productJson = JsonUtil.getObject(json, "product");
      if (__productJson != null) {
        builder.product(Product.fromJson(__productJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ProductCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for ProductCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ProductCreateResponse. */
  public static class Builder {

    private Product product;

    private Response httpResponse;

    private Builder() {}

    public Builder product(Product product) {
      this.product = product;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public ProductCreateResponse build() {
      return new ProductCreateResponse(this);
    }
  }

  /** Get the product from the response. */
  public Product getProduct() {
    return product;
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
