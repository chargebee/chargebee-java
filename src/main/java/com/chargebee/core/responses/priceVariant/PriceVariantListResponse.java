package com.chargebee.core.responses.priceVariant;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.priceVariant.PriceVariant;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.PriceVariantService;
import com.chargebee.core.models.priceVariant.params.PriceVariantListParams;

/**
 * Immutable response object for PriceVariantList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class PriceVariantListResponse
    implements Iterable<PriceVariantListResponse.PriceVariantListItem> {

  private final List<PriceVariantListItem> list;

  private final String nextOffset;

  private final PriceVariantService service;
  private final PriceVariantListParams originalParams;
  private final boolean isAutoPaginate;

  private PriceVariantListResponse(
      List<PriceVariantListItem> list,
      String nextOffset,
      PriceVariantService service,
      PriceVariantListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private PriceVariantListResponse(
      List<PriceVariantListItem> list,
      String nextOffset,
      PriceVariantService service,
      PriceVariantListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into PriceVariantListResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static PriceVariantListResponse fromJson(String json) {
    try {

      List<PriceVariantListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PriceVariantListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PriceVariantListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceVariantListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into PriceVariantListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static PriceVariantListResponse fromJson(
      String json, PriceVariantService service, PriceVariantListParams originalParams) {
    try {

      List<PriceVariantListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(PriceVariantListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new PriceVariantListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceVariantListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<PriceVariantListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<PriceVariantListItem> items() {
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
  public PriceVariantListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    PriceVariantListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public PriceVariantListResponse autoPaginate() {
    return new PriceVariantListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<PriceVariantListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<PriceVariantListItem> {
    private PriceVariantListResponse currentPage = PriceVariantListResponse.this;
    private Iterator<PriceVariantListItem> currentIterator = currentPage.list.iterator();

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
    public PriceVariantListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class PriceVariantListItem {

    private PriceVariant priceVariant;

    public PriceVariant getPriceVariant() {
      return priceVariant;
    }

    public static PriceVariantListItem fromJson(String json) {
      PriceVariantListItem item = new PriceVariantListItem();

      String __priceVariantJson = JsonUtil.getObject(json, "price_variant");
      if (__priceVariantJson != null) {
        item.priceVariant = PriceVariant.fromJson(__priceVariantJson);
      }

      return item;
    }
  }
}
