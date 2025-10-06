package com.chargebee.v4.core.responses.product;

import com.chargebee.v4.core.models.product.Product;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ProductDelete operation. Contains the response data from the API.
 */
public final class ProductDeleteResponse {

  private final Product product;

  private ProductDeleteResponse(Builder builder) {

    this.product = builder.product;
  }

  /** Parse JSON response into ProductDeleteResponse object. */
  public static ProductDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __productJson = JsonUtil.getObject(json, "product");
      if (__productJson != null) {
        builder.product(Product.fromJson(__productJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ProductDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for ProductDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ProductDeleteResponse. */
  public static class Builder {

    private Product product;

    private Builder() {}

    public Builder product(Product product) {
      this.product = product;
      return this;
    }

    public ProductDeleteResponse build() {
      return new ProductDeleteResponse(this);
    }
  }

  /** Get the product from the response. */
  public Product getProduct() {
    return product;
  }
}
