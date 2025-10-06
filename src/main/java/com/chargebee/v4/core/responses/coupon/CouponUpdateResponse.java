package com.chargebee.v4.core.responses.coupon;

import com.chargebee.v4.core.models.coupon.Coupon;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CouponUpdate operation. Contains the response data from the API.
 */
public final class CouponUpdateResponse {

  private final Coupon coupon;

  private CouponUpdateResponse(Builder builder) {

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponUpdateResponse object. */
  public static CouponUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponUpdateResponse. */
  public static class Builder {

    private Coupon coupon;

    private Builder() {}

    public Builder coupon(Coupon coupon) {
      this.coupon = coupon;
      return this;
    }

    public CouponUpdateResponse build() {
      return new CouponUpdateResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
