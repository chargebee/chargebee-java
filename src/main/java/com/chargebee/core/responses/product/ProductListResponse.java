package com.chargebee.core.responses.product;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.product.Product;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.ProductService;
import com.chargebee.core.models.product.params.ProductListParams;

/**
 * Immutable response object for ProductList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class ProductListResponse implements Iterable<ProductListResponse.ProductListItem> {

  private final List<ProductListItem> list;

  private final String nextOffset;

  private final ProductService service;
  private final ProductListParams originalParams;
  private final boolean isAutoPaginate;

  private ProductListResponse(
      List<ProductListItem> list,
      String nextOffset,
      ProductService service,
      ProductListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private ProductListResponse(
      List<ProductListItem> list,
      String nextOffset,
      ProductService service,
      ProductListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
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

      return new ProductListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ProductListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into ProductListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static ProductListResponse fromJson(
      String json, ProductService service, ProductListParams originalParams) {
    try {

      List<ProductListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(ProductListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new ProductListResponse(list, nextOffset, service, originalParams);
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
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    ProductListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public ProductListResponse autoPaginate() {
    return new ProductListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<ProductListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<ProductListItem> {
    private ProductListResponse currentPage = ProductListResponse.this;
    private Iterator<ProductListItem> currentIterator = currentPage.list.iterator();

    @Override
    public boolean hasNext() {
      if (currentIterator.hasNext()) {
        return true;
      }

      // Try to load next page if available
      if (currentPage.hasNextPage()) {
        try {
          currentPage = currentPage.nextPage();
          currentIterator = currentPage.list.iterator();
          return currentIterator.hasNext();
        } catch (Exception e) {
          throw new RuntimeException("Failed to fetch next page", e);
        }
      }

      return false;
    }

    @Override
    public ProductListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
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
