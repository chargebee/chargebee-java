package com.chargebee.v4.models.product.responses;

import com.chargebee.v4.models.product.Product;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ProductUpdate operation. Contains the response data from the API.
 */
public final class ProductUpdateResponse extends BaseResponse {
  private final Product product;

  private ProductUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.product = builder.product;
  }

  /** Parse JSON response into ProductUpdateResponse object. */
  public static ProductUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ProductUpdateResponse object with HTTP response. */
  public static ProductUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __productJson = JsonUtil.getObject(json, "product");
      if (__productJson != null) {
        builder.product(Product.fromJson(__productJson));
      }

      builder.httpResponse(httpResponse);
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

    public ProductUpdateResponse build() {
      return new ProductUpdateResponse(this);
    }
  }

  /** Get the product from the response. */
  public Product getProduct() {
    return product;
  }

  @Override
  public String toString() {
    return "ProductUpdateResponse{" + "product=" + product + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductUpdateResponse that = (ProductUpdateResponse) o;
    return java.util.Objects.equals(product, that.product);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(product);
  }
}
