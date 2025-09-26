package com.chargebee.core.responses.coupon;

import com.chargebee.core.models.coupon.Coupon;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CouponCreate operation. Contains the response data from the API.
 */
public final class CouponCreateResponse {

  private final Coupon coupon;

  private CouponCreateResponse(Builder builder) {

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponCreateResponse object. */
  public static CouponCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponCreateResponse. */
  public static class Builder {

    private Coupon coupon;

    private Builder() {}

    public Builder coupon(Coupon coupon) {
      this.coupon = coupon;
      return this;
    }

    public CouponCreateResponse build() {
      return new CouponCreateResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
