package com.chargebee.v4.core.responses.product;

import com.chargebee.v4.core.models.product.Product;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ProductRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class ProductRetrieveResponse {

  private final Product product;

  private ProductRetrieveResponse(Product product) {

    this.product = product;
  }

  /** Parse JSON response into ProductRetrieveResponse object. */
  public static ProductRetrieveResponse fromJson(String json) {
    try {

      Product product = Product.fromJson(JsonUtil.getObject(json, "product"));

      return new ProductRetrieveResponse(product);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ProductRetrieveResponse from JSON", e);
    }
  }

  /** Get the product from the response. */
  public Product getProduct() {
    return product;
  }
}
