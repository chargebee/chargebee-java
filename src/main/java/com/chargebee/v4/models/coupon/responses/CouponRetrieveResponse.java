package com.chargebee.v4.models.coupon.responses;

import com.chargebee.v4.models.coupon.Coupon;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class CouponRetrieveResponse extends BaseResponse {
  private final Coupon coupon;

  private CouponRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponRetrieveResponse object. */
  public static CouponRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponRetrieveResponse object with HTTP response. */
  public static CouponRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponRetrieveResponse. */
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

    public CouponRetrieveResponse build() {
      return new CouponRetrieveResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }

  @Override
  public String toString() {
    return "CouponRetrieveResponse{" + "coupon=" + coupon + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CouponRetrieveResponse that = (CouponRetrieveResponse) o;
    return java.util.Objects.equals(coupon, that.coupon);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(coupon);
  }
}
