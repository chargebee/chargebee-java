package com.chargebee.v4.models.couponCode.responses;

import com.chargebee.v4.models.couponCode.CouponCode;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponCodeRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CouponCodeRetrieveResponse extends BaseResponse {
  private final CouponCode couponCode;

  private CouponCodeRetrieveResponse(CouponCode couponCode, Response httpResponse) {
    super(httpResponse);

    this.couponCode = couponCode;
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
}
