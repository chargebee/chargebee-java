package com.chargebee.v4.core.responses.coupon;

import com.chargebee.v4.core.models.coupon.Coupon;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CouponDelete operation. Contains the response data from the API.
 */
public final class CouponDeleteResponse {

  private final Coupon coupon;

  private CouponDeleteResponse(Builder builder) {

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponDeleteResponse object. */
  public static CouponDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponDeleteResponse. */
  public static class Builder {

    private Coupon coupon;

    private Builder() {}

    public Builder coupon(Coupon coupon) {
      this.coupon = coupon;
      return this;
    }

    public CouponDeleteResponse build() {
      return new CouponDeleteResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
