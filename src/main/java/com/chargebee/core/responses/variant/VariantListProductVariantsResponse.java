package com.chargebee.core.responses.variant;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.variant.Variant;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.VariantService;
import com.chargebee.core.models.variant.params.VariantListProductVariantsParams;

/**
 * Immutable response object for VariantListProductVariants operation. Contains paginated list data
 * with auto-pagination support.
 */
public final class VariantListProductVariantsResponse
    implements Iterable<VariantListProductVariantsResponse.VariantListProductVariantsItem> {

  private final List<VariantListProductVariantsItem> list;

  private final String nextOffset;

  private final String productId;

  private final VariantService service;
  private final VariantListProductVariantsParams originalParams;
  private final boolean isAutoPaginate;

  private VariantListProductVariantsResponse(
      List<VariantListProductVariantsItem> list,
      String nextOffset,
      String productId,
      VariantService service,
      VariantListProductVariantsParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.productId = productId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private VariantListProductVariantsResponse(
      List<VariantListProductVariantsItem> list,
      String nextOffset,
      String productId,
      VariantService service,
      VariantListProductVariantsParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.productId = productId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into VariantListProductVariantsResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static VariantListProductVariantsResponse fromJson(String json) {
    try {

      List<VariantListProductVariantsItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(VariantListProductVariantsItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new VariantListProductVariantsResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VariantListProductVariantsResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into VariantListProductVariantsResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static VariantListProductVariantsResponse fromJson(
      String json,
      VariantService service,
      VariantListProductVariantsParams originalParams,
      String productId) {
    try {

      List<VariantListProductVariantsItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(VariantListProductVariantsItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new VariantListProductVariantsResponse(
          list, nextOffset, productId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VariantListProductVariantsResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<VariantListProductVariantsItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<VariantListProductVariantsItem> items() {
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
  public VariantListProductVariantsResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    VariantListProductVariantsParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.listProductVariants(productId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public VariantListProductVariantsResponse autoPaginate() {
    return new VariantListProductVariantsResponse(
        list, nextOffset, productId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<VariantListProductVariantsItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<VariantListProductVariantsItem> {
    private VariantListProductVariantsResponse currentPage =
        VariantListProductVariantsResponse.this;
    private Iterator<VariantListProductVariantsItem> currentIterator = currentPage.list.iterator();

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
    public VariantListProductVariantsItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class VariantListProductVariantsItem {

    private Variant variant;

    public Variant getVariant() {
      return variant;
    }

    public static VariantListProductVariantsItem fromJson(String json) {
      VariantListProductVariantsItem item = new VariantListProductVariantsItem();

      String __variantJson = JsonUtil.getObject(json, "variant");
      if (__variantJson != null) {
        item.variant = Variant.fromJson(__variantJson);
      }

      return item;
    }
  }
}
