package com.chargebee.v4.models.couponSet.responses;

import com.chargebee.v4.models.couponSet.CouponSet;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponSetRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CouponSetRetrieveResponse extends BaseResponse {
  private final CouponSet couponSet;

  private CouponSetRetrieveResponse(CouponSet couponSet, Response httpResponse) {
    super(httpResponse);

    this.couponSet = couponSet;
  }

  /** Parse JSON response into CouponSetRetrieveResponse object. */
  public static CouponSetRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponSetRetrieveResponse object with HTTP response. */
  public static CouponSetRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      CouponSet couponSet = CouponSet.fromJson(JsonUtil.getObject(json, "coupon_set"));

      return new CouponSetRetrieveResponse(couponSet, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetRetrieveResponse from JSON", e);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }
}
