package com.chargebee.core.responses.product;

import com.chargebee.core.models.product.Product;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for ProductCreate operation. Contains the response data from the API.
 */
public final class ProductCreateResponse {

  private final Product product;

  private ProductCreateResponse(Builder builder) {

    this.product = builder.product;
  }

  /** Parse JSON response into ProductCreateResponse object. */
  public static ProductCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __productJson = JsonUtil.getObject(json, "product");
      if (__productJson != null) {
        builder.product(Product.fromJson(__productJson));
      }

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

    private Builder() {}

    public Builder product(Product product) {
      this.product = product;
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
}
