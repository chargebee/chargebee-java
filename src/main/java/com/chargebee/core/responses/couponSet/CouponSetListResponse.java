package com.chargebee.core.responses.couponSet;

import java.util.List;

import com.chargebee.core.models.couponSet.CouponSet;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.CouponSetService;
import com.chargebee.core.models.couponSet.params.CouponSetListParams;

/** Immutable response object for CouponSetList operation. Contains paginated list data. */
public final class CouponSetListResponse {

  private final List<CouponSetListItem> list;

  private final String nextOffset;

  private final CouponSetService service;
  private final CouponSetListParams originalParams;

  private CouponSetListResponse(
      List<CouponSetListItem> list,
      String nextOffset,
      CouponSetService service,
      CouponSetListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into CouponSetListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static CouponSetListResponse fromJson(String json) {
    try {

      List<CouponSetListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CouponSetListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CouponSetListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CouponSetListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static CouponSetListResponse fromJson(
      String json, CouponSetService service, CouponSetListParams originalParams) {
    try {

      List<CouponSetListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CouponSetListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CouponSetListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<CouponSetListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<CouponSetListItem> items() {
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
  public CouponSetListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    CouponSetListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class CouponSetListItem {

    private CouponSet couponSet;

    public CouponSet getCouponSet() {
      return couponSet;
    }

    public static CouponSetListItem fromJson(String json) {
      CouponSetListItem item = new CouponSetListItem();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        item.couponSet = CouponSet.fromJson(__couponSetJson);
      }

      return item;
    }
  }
}
