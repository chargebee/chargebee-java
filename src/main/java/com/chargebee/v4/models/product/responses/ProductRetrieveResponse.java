package com.chargebee.v4.models.product.responses;

import com.chargebee.v4.models.product.Product;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ProductRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class ProductRetrieveResponse extends BaseResponse {
  private final Product product;

  private ProductRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.product = builder.product;
  }

  /** Parse JSON response into ProductRetrieveResponse object. */
  public static ProductRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ProductRetrieveResponse object with HTTP response. */
  public static ProductRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __productJson = JsonUtil.getObject(json, "product");
      if (__productJson != null) {
        builder.product(Product.fromJson(__productJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ProductRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for ProductRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ProductRetrieveResponse. */
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

    public ProductRetrieveResponse build() {
      return new ProductRetrieveResponse(this);
    }
  }

  /** Get the product from the response. */
  public Product getProduct() {
    return product;
  }

  @Override
  public String toString() {
    return "ProductRetrieveResponse{" + "product=" + product + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ProductRetrieveResponse that = (ProductRetrieveResponse) o;
    return java.util.Objects.equals(product, that.product);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(product);
  }
}
