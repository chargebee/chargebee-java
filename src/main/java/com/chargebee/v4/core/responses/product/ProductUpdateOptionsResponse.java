package com.chargebee.v4.core.responses.product;

import com.chargebee.v4.core.models.product.Product;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ProductUpdateOptions operation. Contains the response data from the
 * API.
 */
public final class ProductUpdateOptionsResponse extends BaseResponse {
  private final Product product;

  private ProductUpdateOptionsResponse(Builder builder) {
    super(builder.httpResponse);

    this.product = builder.product;
  }

  /** Parse JSON response into ProductUpdateOptionsResponse object. */
  public static ProductUpdateOptionsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ProductUpdateOptionsResponse object with HTTP response. */
  public static ProductUpdateOptionsResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __productJson = JsonUtil.getObject(json, "product");
      if (__productJson != null) {
        builder.product(Product.fromJson(__productJson));
      }

      builder.httpResponse(httpResponse);
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

    public ProductUpdateOptionsResponse build() {
      return new ProductUpdateOptionsResponse(this);
    }
  }

  /** Get the product from the response. */
  public Product getProduct() {
    return product;
  }
}
