package com.chargebee.core.responses.coupon;

import java.util.List;

import com.chargebee.core.models.coupon.Coupon;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.CouponService;
import com.chargebee.core.models.coupon.params.CouponListParams;

/** Immutable response object for CouponList operation. Contains paginated list data. */
public final class CouponListResponse {

  private final List<CouponListItem> list;

  private final String nextOffset;

  private final CouponService service;
  private final CouponListParams originalParams;

  private CouponListResponse(
      List<CouponListItem> list,
      String nextOffset,
      CouponService service,
      CouponListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into CouponListResponse object (no service context). Use this when you only
   * need to read a single page (no nextPage()).
   */
  public static CouponListResponse fromJson(String json) {
    try {

      List<CouponListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CouponListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CouponListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CouponListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static CouponListResponse fromJson(
      String json, CouponService service, CouponListParams originalParams) {
    try {

      List<CouponListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CouponListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CouponListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CouponListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<CouponListItem> items() {
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
  public CouponListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    CouponListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class CouponListItem {

    private Coupon coupon;

    public Coupon getCoupon() {
      return coupon;
    }

    public static CouponListItem fromJson(String json) {
      CouponListItem item = new CouponListItem();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        item.coupon = Coupon.fromJson(__couponJson);
      }

      return item;
    }
  }
}
