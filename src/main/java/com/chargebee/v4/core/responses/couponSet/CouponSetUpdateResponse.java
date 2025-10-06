package com.chargebee.v4.core.responses.couponSet;

import com.chargebee.v4.core.models.couponSet.CouponSet;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CouponSetUpdate operation. Contains the response data from the API.
 */
public final class CouponSetUpdateResponse {

  private final CouponSet couponSet;

  private CouponSetUpdateResponse(Builder builder) {

    this.couponSet = builder.couponSet;
  }

  /** Parse JSON response into CouponSetUpdateResponse object. */
  public static CouponSetUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        builder.couponSet(CouponSet.fromJson(__couponSetJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponSetUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponSetUpdateResponse. */
  public static class Builder {

    private CouponSet couponSet;

    private Builder() {}

    public Builder couponSet(CouponSet couponSet) {
      this.couponSet = couponSet;
      return this;
    }

    public CouponSetUpdateResponse build() {
      return new CouponSetUpdateResponse(this);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }
}
