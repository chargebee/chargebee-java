package com.chargebee.core.responses.coupon;

import com.chargebee.core.models.coupon.Coupon;

import com.chargebee.internal.JsonUtil;

/** Immutable response object for CouponCopy operation. Contains the response data from the API. */
public final class CouponCopyResponse {

  private final Coupon coupon;

  private CouponCopyResponse(Builder builder) {

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponCopyResponse object. */
  public static CouponCopyResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCopyResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponCopyResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponCopyResponse. */
  public static class Builder {

    private Coupon coupon;

    private Builder() {}

    public Builder coupon(Coupon coupon) {
      this.coupon = coupon;
      return this;
    }

    public CouponCopyResponse build() {
      return new CouponCopyResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
