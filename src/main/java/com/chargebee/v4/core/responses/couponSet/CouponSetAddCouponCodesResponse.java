package com.chargebee.v4.core.responses.couponSet;

import com.chargebee.v4.core.models.couponSet.CouponSet;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CouponSetAddCouponCodes operation. Contains the response data from
 * the API.
 */
public final class CouponSetAddCouponCodesResponse {

  private final CouponSet couponSet;

  private CouponSetAddCouponCodesResponse(Builder builder) {

    this.couponSet = builder.couponSet;
  }

  /** Parse JSON response into CouponSetAddCouponCodesResponse object. */
  public static CouponSetAddCouponCodesResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        builder.couponSet(CouponSet.fromJson(__couponSetJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetAddCouponCodesResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponSetAddCouponCodesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponSetAddCouponCodesResponse. */
  public static class Builder {

    private CouponSet couponSet;

    private Builder() {}

    public Builder couponSet(CouponSet couponSet) {
      this.couponSet = couponSet;
      return this;
    }

    public CouponSetAddCouponCodesResponse build() {
      return new CouponSetAddCouponCodesResponse(this);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }
}
