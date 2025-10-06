package com.chargebee.v4.core.responses.product;

import com.chargebee.v4.core.models.product.Product;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for ProductUpdateOptions operation. Contains the response data from the
 * API.
 */
public final class ProductUpdateOptionsResponse {

  private final Product product;

  private ProductUpdateOptionsResponse(Builder builder) {

    this.product = builder.product;
  }

  /** Parse JSON response into ProductUpdateOptionsResponse object. */
  public static ProductUpdateOptionsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __productJson = JsonUtil.getObject(json, "product");
      if (__productJson != null) {
        builder.product(Product.fromJson(__productJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ProductUpdateOptionsResponse from JSON", e);
    }
  }

  /** Create a new builder for ProductUpdateOptionsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ProductUpdateOptionsResponse. */
  public static class Builder {

    private Product product;

    private Builder() {}

    public Builder product(Product product) {
      this.product = product;
      return this;
    }

    public ProductUpdateOptionsResponse build() {
      return new ProductUpdateOptionsResponse(this);
    }
  }

  /** Get the product from the response. */
  public Product getProduct() {
    return product;
  }
}
