package com.chargebee.v4.core.responses.coupon;

import com.chargebee.v4.core.models.coupon.Coupon;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class CouponRetrieveResponse {

  private final Coupon coupon;

  private final Response httpResponse;

  private CouponRetrieveResponse(Coupon coupon, Response httpResponse) {

    this.coupon = coupon;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into CouponRetrieveResponse object. */
  public static CouponRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponRetrieveResponse object with HTTP response. */
  public static CouponRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Coupon coupon = Coupon.fromJson(JsonUtil.getObject(json, "coupon"));

      return new CouponRetrieveResponse(coupon, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponRetrieveResponse from JSON", e);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
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
}
