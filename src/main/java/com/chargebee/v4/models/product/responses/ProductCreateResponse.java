package com.chargebee.v4.models.product.responses;

import com.chargebee.v4.models.product.Product;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ProductCreate operation. Contains the response data from the API.
 */
public final class ProductCreateResponse extends BaseResponse {
  private final Product product;

  private ProductCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.product = builder.product;
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
}
