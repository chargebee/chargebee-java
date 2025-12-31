package com.chargebee.v4.models.couponCode.responses;

import java.util.List;

import com.chargebee.v4.models.couponCode.CouponCode;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.CouponCodeService;
import com.chargebee.v4.models.couponCode.params.CouponCodeListParams;

/** Immutable response object for CouponCodeList operation. Contains paginated list data. */
public final class CouponCodeListResponse {

  private final List<CouponCodeListItem> list;

  private final String nextOffset;

  private final CouponCodeService service;
  private final CouponCodeListParams originalParams;
  private final Response httpResponse;

  private CouponCodeListResponse(
      List<CouponCodeListItem> list,
      String nextOffset,
      CouponCodeService service,
      CouponCodeListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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

      return new CouponCodeListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCodeListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into CouponCodeListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static CouponCodeListResponse fromJson(
      String json,
      CouponCodeService service,
      CouponCodeListParams originalParams,
      Response httpResponse) {
    try {

      List<CouponCodeListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(CouponCodeListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new CouponCodeListResponse(list, nextOffset, service, originalParams, httpResponse);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public CouponCodeListResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    CouponCodeListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : CouponCodeListParams.builder())
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
    return "CouponCodeListResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CouponCodeListResponse that = (CouponCodeListResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
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

    @Override
    public String toString() {
      return "CouponCodeListItem{" + "couponCode=" + couponCode + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CouponCodeListItem that = (CouponCodeListItem) o;
      return java.util.Objects.equals(couponCode, that.couponCode);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(couponCode);
    }
  }
}
