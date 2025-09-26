package com.chargebee.core.responses.product;

import com.chargebee.core.models.product.Product;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ProductUpdate operation. Contains the response data from the API.
 */
public final class ProductUpdateResponse {

  private final Product product;

  private ProductUpdateResponse(Builder builder) {

    this.product = builder.product;
  }

  /** Parse JSON response into ProductUpdateResponse object. */
  public static ProductUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __productJson = JsonUtil.getObject(json, "product");
      if (__productJson != null) {
        builder.product(Product.fromJson(__productJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ProductUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for ProductUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ProductUpdateResponse. */
  public static class Builder {

    private Product product;

    private Builder() {}

    public Builder product(Product product) {
      this.product = product;
      return this;
    }

    public ProductUpdateResponse build() {
      return new ProductUpdateResponse(this);
    }
  }

  /** Get the product from the response. */
  public Product getProduct() {
    return product;
  }
}
