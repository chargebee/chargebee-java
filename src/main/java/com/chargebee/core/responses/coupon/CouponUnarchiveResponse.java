package com.chargebee.core.responses.coupon;

import com.chargebee.core.models.coupon.Coupon;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CouponUnarchive operation. Contains the response data from the API.
 */
public final class CouponUnarchiveResponse {

  private final Coupon coupon;

  private CouponUnarchiveResponse(Builder builder) {

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponUnarchiveResponse object. */
  public static CouponUnarchiveResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponUnarchiveResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponUnarchiveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponUnarchiveResponse. */
  public static class Builder {

    private Coupon coupon;

    private Builder() {}

    public Builder coupon(Coupon coupon) {
      this.coupon = coupon;
      return this;
    }

    public CouponUnarchiveResponse build() {
      return new CouponUnarchiveResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
