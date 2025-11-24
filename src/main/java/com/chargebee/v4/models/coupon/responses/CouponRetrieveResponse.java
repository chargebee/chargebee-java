package com.chargebee.v4.models.coupon.responses;

import com.chargebee.v4.models.coupon.Coupon;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class CouponRetrieveResponse extends BaseResponse {
  private final Coupon coupon;

  private CouponRetrieveResponse(Coupon coupon, Response httpResponse) {
    super(httpResponse);

    this.coupon = coupon;
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
}
