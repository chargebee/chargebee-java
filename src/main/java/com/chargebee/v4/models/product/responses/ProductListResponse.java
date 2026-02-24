package com.chargebee.v4.models.product.responses;

import java.util.List;

import com.chargebee.v4.models.product.Product;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.ProductService;
import com.chargebee.v4.models.product.params.ProductListParams;

/** Immutable response object for ProductList operation. Contains paginated list data. */
public final class ProductListResponse {

  private final List<ProductListItem> list;

  private final String nextOffset;

  private final ProductService service;
  private final ProductListParams originalParams;
  private final Response httpResponse;

  private ProductListResponse(
      List<ProductListItem> list,
      String nextOffset,
      ProductService service,
      ProductListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into ProductListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static ProductListResponse fromJson(String json) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<ProductListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), ProductListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ProductListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ProductListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ProductListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static ProductListResponse fromJson(
      String json,
      ProductService service,
      ProductListParams originalParams,
      Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);

      List<ProductListItem> list =
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "list"), ProductListItem::fromJson);

      String nextOffset = JsonUtil.getString(jsonObj, "next_offset");

      return new ProductListResponse(list, nextOffset, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ProductListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<ProductListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public ProductListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    ProductListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : ProductListParams.builder())
            .offset(nextOffset)
            .build();

    return service.list(nextParams);
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }

  @Override
  public String toString() {
    return "ProductListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ProductListResponse that = (ProductListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class ProductListItem {

    private Product product;

    public Product getProduct() {
      return product;
    }

    public static ProductListItem fromJson(String json) {
      return fromJson(JsonUtil.parse(json));
    }

    public static ProductListItem fromJson(JsonObject jsonObj) {
      ProductListItem item = new ProductListItem();

      JsonObject __productObj = JsonUtil.getJsonObject(jsonObj, "product");
      if (__productObj != null) {
        item.product = Product.fromJson(__productObj);
      }

      return item;
    }

    @Override
    public String toString() {
      return "ProductListItem{" + "product=" + product + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ProductListItem that = (ProductListItem) o;
      return java.util.Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(product);
    }
  }
}
