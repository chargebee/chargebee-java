package com.chargebee.core.responses.couponSet;

import com.chargebee.core.models.couponSet.CouponSet;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CouponSetCreate operation. Contains the response data from the API.
 */
public final class CouponSetCreateResponse {

  private final CouponSet couponSet;

  private CouponSetCreateResponse(Builder builder) {

    this.couponSet = builder.couponSet;
  }

  /** Parse JSON response into CouponSetCreateResponse object. */
  public static CouponSetCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        builder.couponSet(CouponSet.fromJson(__couponSetJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponSetCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponSetCreateResponse. */
  public static class Builder {

    private CouponSet couponSet;

    private Builder() {}

    public Builder couponSet(CouponSet couponSet) {
      this.couponSet = couponSet;
      return this;
    }

    public CouponSetCreateResponse build() {
      return new CouponSetCreateResponse(this);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }
}
