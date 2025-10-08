package com.chargebee.v4.core.responses.product;

import java.util.List;

import com.chargebee.v4.core.models.product.Product;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.ProductService;
import com.chargebee.v4.core.models.product.params.ProductListParams;

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

      List<ProductListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ProductListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

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

      List<ProductListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ProductListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

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

  /** Get the list of items in this page (alias). */
  public List<ProductListItem> items() {
    return list;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws Exception if unable to fetch next page
   */
  public ProductListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    // Create new params with the next offset
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

  public static class ProductListItem {

    private Product product;

    public Product getProduct() {
      return product;
    }

    public static ProductListItem fromJson(String json) {
      ProductListItem item = new ProductListItem();

      String __productJson = JsonUtil.getObject(json, "product");
      if (__productJson != null) {
        item.product = Product.fromJson(__productJson);
      }

      return item;
    }
  }
}
