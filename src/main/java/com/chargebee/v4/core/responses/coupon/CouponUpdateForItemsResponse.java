package com.chargebee.v4.core.responses.coupon;

import com.chargebee.v4.core.models.coupon.Coupon;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CouponUpdateForItems operation. Contains the response data from the
 * API.
 */
public final class CouponUpdateForItemsResponse {

  private final Coupon coupon;

  private CouponUpdateForItemsResponse(Builder builder) {

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponUpdateForItemsResponse object. */
  public static CouponUpdateForItemsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponUpdateForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponUpdateForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponUpdateForItemsResponse. */
  public static class Builder {

    private Coupon coupon;

    private Builder() {}

    public Builder coupon(Coupon coupon) {
      this.coupon = coupon;
      return this;
    }

    public CouponUpdateForItemsResponse build() {
      return new CouponUpdateForItemsResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
