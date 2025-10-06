package com.chargebee.v4.core.responses.couponCode;

import java.util.List;

import com.chargebee.v4.core.models.couponCode.CouponCode;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.CouponCodeService;
import com.chargebee.v4.core.models.couponCode.params.CouponCodeListParams;

/** Immutable response object for CouponCodeList operation. Contains paginated list data. */
public final class CouponCodeListResponse {

  private final List<CouponCodeListItem> list;

  private final String nextOffset;

  private final CouponCodeService service;
  private final CouponCodeListParams originalParams;

  private CouponCodeListResponse(
      List<CouponCodeListItem> list,
      String nextOffset,
      CouponCodeService service,
      CouponCodeListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
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
   * (enables nextPage()).
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
