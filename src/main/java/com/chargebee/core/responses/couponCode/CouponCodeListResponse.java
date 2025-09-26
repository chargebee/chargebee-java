package com.chargebee.core.responses.couponCode;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.couponCode.CouponCode;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.CouponCodeService;
import com.chargebee.core.models.couponCode.params.CouponCodeListParams;

/**
 * Immutable response object for CouponCodeList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class CouponCodeListResponse
    implements Iterable<CouponCodeListResponse.CouponCodeListItem> {

  private final List<CouponCodeListItem> list;

  private final String nextOffset;

  private final CouponCodeService service;
  private final CouponCodeListParams originalParams;
  private final boolean isAutoPaginate;

  private CouponCodeListResponse(
      List<CouponCodeListItem> list,
      String nextOffset,
      CouponCodeService service,
      CouponCodeListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private CouponCodeListResponse(
      List<CouponCodeListItem> list,
      String nextOffset,
      CouponCodeService service,
      CouponCodeListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into CouponCodeListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static CouponCodeListResponse fromJson(String json) {
    try {

      List<CouponCodeListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CouponCodeListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CouponCodeListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCodeListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CouponCodeListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static CouponCodeListResponse fromJson(
      String json, CouponCodeService service, CouponCodeListParams originalParams) {
    try {

      List<CouponCodeListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CouponCodeListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CouponCodeListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCodeListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CouponCodeListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<CouponCodeListItem> items() {
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
  public CouponCodeListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    CouponCodeListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public CouponCodeListResponse autoPaginate() {
    return new CouponCodeListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<CouponCodeListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<CouponCodeListItem> {
    private CouponCodeListResponse currentPage = CouponCodeListResponse.this;
    private Iterator<CouponCodeListItem> currentIterator = currentPage.list.iterator();

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
    public CouponCodeListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class CouponCodeListItem {

    private CouponCode couponCode;

    public CouponCode getCouponCode() {
      return couponCode;
    }

    public static CouponCodeListItem fromJson(String json) {
      CouponCodeListItem item = new CouponCodeListItem();

      String __couponCodeJson = JsonUtil.getObject(json, "coupon_code");
      if (__couponCodeJson != null) {
        item.couponCode = CouponCode.fromJson(__couponCodeJson);
      }

      return item;
    }
  }
}
