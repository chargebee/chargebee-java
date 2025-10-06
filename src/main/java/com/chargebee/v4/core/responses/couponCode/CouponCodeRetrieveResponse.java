package com.chargebee.v4.core.responses.couponCode;

import com.chargebee.v4.core.models.couponCode.CouponCode;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CouponCodeRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CouponCodeRetrieveResponse {

  private final CouponCode couponCode;

  private CouponCodeRetrieveResponse(CouponCode couponCode) {

    this.couponCode = couponCode;
  }

  /** Parse JSON response into CouponCodeRetrieveResponse object. */
  public static CouponCodeRetrieveResponse fromJson(String json) {
    try {

      CouponCode couponCode = CouponCode.fromJson(JsonUtil.getObject(json, "coupon_code"));

      return new CouponCodeRetrieveResponse(couponCode);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCodeRetrieveResponse from JSON", e);
    }
  }

  /** Get the couponCode from the response. */
  public CouponCode getCouponCode() {
    return couponCode;
  }
}
