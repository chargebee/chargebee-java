package com.chargebee.v4.core.responses.couponSet;

import com.chargebee.v4.core.models.couponSet.CouponSet;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CouponSetRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CouponSetRetrieveResponse {

  private final CouponSet couponSet;

  private CouponSetRetrieveResponse(CouponSet couponSet) {

    this.couponSet = couponSet;
  }

  /** Parse JSON response into CouponSetRetrieveResponse object. */
  public static CouponSetRetrieveResponse fromJson(String json) {
    try {

      CouponSet couponSet = CouponSet.fromJson(JsonUtil.getObject(json, "coupon_set"));

      return new CouponSetRetrieveResponse(couponSet);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetRetrieveResponse from JSON", e);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }
}
