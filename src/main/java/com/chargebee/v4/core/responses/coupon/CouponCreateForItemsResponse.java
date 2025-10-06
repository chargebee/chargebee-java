package com.chargebee.v4.core.responses.coupon;

import com.chargebee.v4.core.models.coupon.Coupon;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CouponCreateForItems operation. Contains the response data from the
 * API.
 */
public final class CouponCreateForItemsResponse {

  private final Coupon coupon;

  private CouponCreateForItemsResponse(Builder builder) {

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponCreateForItemsResponse object. */
  public static CouponCreateForItemsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCreateForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponCreateForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponCreateForItemsResponse. */
  public static class Builder {

    private Coupon coupon;

    private Builder() {}

    public Builder coupon(Coupon coupon) {
      this.coupon = coupon;
      return this;
    }

    public CouponCreateForItemsResponse build() {
      return new CouponCreateForItemsResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
