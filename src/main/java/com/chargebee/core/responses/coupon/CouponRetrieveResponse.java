package com.chargebee.core.responses.coupon;

import com.chargebee.core.models.coupon.Coupon;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CouponRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class CouponRetrieveResponse {

  private final Coupon coupon;

  private CouponRetrieveResponse(Coupon coupon) {

    this.coupon = coupon;
  }

  /** Parse JSON response into CouponRetrieveResponse object. */
  public static CouponRetrieveResponse fromJson(String json) {
    try {

      Coupon coupon = Coupon.fromJson(JsonUtil.getObject(json, "coupon"));

      return new CouponRetrieveResponse(coupon);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponRetrieveResponse from JSON", e);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
