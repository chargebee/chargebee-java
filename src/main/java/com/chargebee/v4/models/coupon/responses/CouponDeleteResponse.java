package com.chargebee.v4.models.coupon.responses;

import com.chargebee.v4.models.coupon.Coupon;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponDelete operation. Contains the response data from the API.
 */
public final class CouponDeleteResponse extends BaseResponse {
  private final Coupon coupon;

  private CouponDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponDeleteResponse object. */
  public static CouponDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponDeleteResponse object with HTTP response. */
  public static CouponDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponDeleteResponse. */
  public static class Builder {

    private Coupon coupon;

    private Response httpResponse;

    private Builder() {}

    public Builder coupon(Coupon coupon) {
      this.coupon = coupon;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CouponDeleteResponse build() {
      return new CouponDeleteResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }

  @Override
  public String toString() {
    return "CouponDeleteResponse{" + "coupon=" + coupon + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CouponDeleteResponse that = (CouponDeleteResponse) o;
    return java.util.Objects.equals(coupon, that.coupon);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(coupon);
  }
}
