package com.chargebee.v4.core.responses.product;

import com.chargebee.v4.core.models.product.Product;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ProductRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class ProductRetrieveResponse {

  private final Product product;

  private final Response httpResponse;

  private ProductRetrieveResponse(Product product, Response httpResponse) {

    this.product = product;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into ProductRetrieveResponse object. */
  public static ProductRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ProductRetrieveResponse object with HTTP response. */
  public static ProductRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Product product = Product.fromJson(JsonUtil.getObject(json, "product"));

      return new ProductRetrieveResponse(product, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ProductRetrieveResponse from JSON", e);
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
