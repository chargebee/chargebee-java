package com.chargebee.v4.models.coupon.responses;

import java.util.List;

import com.chargebee.v4.models.coupon.Coupon;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.CouponService;
import com.chargebee.v4.models.coupon.params.CouponListParams;

/** Immutable response object for CouponList operation. Contains paginated list data. */
public final class CouponListResponse {

  private final List<CouponListItem> list;

  private final String nextOffset;

  private final CouponService service;
  private final CouponListParams originalParams;
  private final Response httpResponse;

  private CouponListResponse(
      List<CouponListItem> list,
      String nextOffset,
      CouponService service,
      CouponListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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

      return new CouponListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CouponListResponse object with service context for pagination (enables
   * nextPage()).
   */
  public static CouponListResponse fromJson(
      String json, CouponService service, CouponListParams originalParams, Response httpResponse) {
    try {

      List<CouponListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CouponListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CouponListResponse(list, nextOffset, service, originalParams, httpResponse);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public CouponListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    CouponListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : CouponListParams.builder())
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
    return "CouponListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CouponListResponse that = (CouponListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
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

    @Override
    public String toString() {
      return "CouponListItem{" + "coupon=" + coupon + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CouponListItem that = (CouponListItem) o;
      return java.util.Objects.equals(coupon, that.coupon);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(coupon);
    }
  }
}
