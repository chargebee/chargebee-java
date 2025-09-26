package com.chargebee.core.responses.couponCode;

import com.chargebee.core.models.couponCode.CouponCode;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CouponCodeArchive operation. Contains the response data from the
 * API.
 */
public final class CouponCodeArchiveResponse {

  private final CouponCode couponCode;

  private CouponCodeArchiveResponse(Builder builder) {

    this.couponCode = builder.couponCode;
  }

  /** Parse JSON response into CouponCodeArchiveResponse object. */
  public static CouponCodeArchiveResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __couponCodeJson = JsonUtil.getObject(json, "coupon_code");
      if (__couponCodeJson != null) {
        builder.couponCode(CouponCode.fromJson(__couponCodeJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCodeArchiveResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponCodeArchiveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponCodeArchiveResponse. */
  public static class Builder {

    private CouponCode couponCode;

    private Builder() {}

    public Builder couponCode(CouponCode couponCode) {
      this.couponCode = couponCode;
      return this;
    }

    public CouponCodeArchiveResponse build() {
      return new CouponCodeArchiveResponse(this);
    }
  }

  /** Get the couponCode from the response. */
  public CouponCode getCouponCode() {
    return couponCode;
  }
}
