package com.chargebee.core.responses.couponCode;

import com.chargebee.core.models.couponCode.CouponCode;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CouponCodeCreate operation. Contains the response data from the
 * API.
 */
public final class CouponCodeCreateResponse {

  private final CouponCode couponCode;

  private CouponCodeCreateResponse(Builder builder) {

    this.couponCode = builder.couponCode;
  }

  /** Parse JSON response into CouponCodeCreateResponse object. */
  public static CouponCodeCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponCodeJson = JsonUtil.getObject(json, "coupon_code");
      if (__couponCodeJson != null) {
        builder.couponCode(CouponCode.fromJson(__couponCodeJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCodeCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponCodeCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponCodeCreateResponse. */
  public static class Builder {

    private CouponCode couponCode;

    private Builder() {}

    public Builder couponCode(CouponCode couponCode) {
      this.couponCode = couponCode;
      return this;
    }

    public CouponCodeCreateResponse build() {
      return new CouponCodeCreateResponse(this);
    }
  }

  /** Get the couponCode from the response. */
  public CouponCode getCouponCode() {
    return couponCode;
  }
}
