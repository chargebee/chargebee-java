package com.chargebee.core.responses.couponSet;

import com.chargebee.core.models.couponSet.CouponSet;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CouponSetDelete operation. Contains the response data from the API.
 */
public final class CouponSetDeleteResponse {

  private final CouponSet couponSet;

  private CouponSetDeleteResponse(Builder builder) {

    this.couponSet = builder.couponSet;
  }

  /** Parse JSON response into CouponSetDeleteResponse object. */
  public static CouponSetDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        builder.couponSet(CouponSet.fromJson(__couponSetJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponSetDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponSetDeleteResponse. */
  public static class Builder {

    private CouponSet couponSet;

    private Builder() {}

    public Builder couponSet(CouponSet couponSet) {
      this.couponSet = couponSet;
      return this;
    }

    public CouponSetDeleteResponse build() {
      return new CouponSetDeleteResponse(this);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }
}
