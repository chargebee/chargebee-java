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

  private ProductRetrieveResponse(Product product, Response httpResponse) {
    super(httpResponse);

    this.product = product;
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
}
