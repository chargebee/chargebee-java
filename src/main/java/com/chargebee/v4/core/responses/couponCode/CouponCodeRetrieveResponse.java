package com.chargebee.v4.core.responses.couponCode;

import com.chargebee.v4.core.models.couponCode.CouponCode;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponCodeRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CouponCodeRetrieveResponse {

  private final CouponCode couponCode;

  private final Response httpResponse;

  private CouponCodeRetrieveResponse(CouponCode couponCode, Response httpResponse) {

    this.couponCode = couponCode;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into CouponCodeRetrieveResponse object. */
  public static CouponCodeRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponCodeRetrieveResponse object with HTTP response. */
  public static CouponCodeRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      CouponCode couponCode = CouponCode.fromJson(JsonUtil.getObject(json, "coupon_code"));

      return new CouponCodeRetrieveResponse(couponCode, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCodeRetrieveResponse from JSON", e);
    }
  }

  /** Get the couponCode from the response. */
  public CouponCode getCouponCode() {
    return couponCode;
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
