package com.chargebee.core.responses.couponSet;

import com.chargebee.core.models.couponSet.CouponSet;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CouponSetDeleteUnusedCouponCodes operation. Contains the response
 * data from the API.
 */
public final class CouponSetDeleteUnusedCouponCodesResponse {

  private final CouponSet couponSet;

  private CouponSetDeleteUnusedCouponCodesResponse(Builder builder) {

    this.couponSet = builder.couponSet;
  }

  /** Parse JSON response into CouponSetDeleteUnusedCouponCodesResponse object. */
  public static CouponSetDeleteUnusedCouponCodesResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        builder.couponSet(CouponSet.fromJson(__couponSetJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CouponSetDeleteUnusedCouponCodesResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponSetDeleteUnusedCouponCodesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponSetDeleteUnusedCouponCodesResponse. */
  public static class Builder {

    private CouponSet couponSet;

    private Builder() {}

    public Builder couponSet(CouponSet couponSet) {
      this.couponSet = couponSet;
      return this;
    }

    public CouponSetDeleteUnusedCouponCodesResponse build() {
      return new CouponSetDeleteUnusedCouponCodesResponse(this);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }
}
