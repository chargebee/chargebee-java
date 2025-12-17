package com.chargebee.v4.models.coupon.responses;

import com.chargebee.v4.models.coupon.Coupon;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponUpdate operation. Contains the response data from the API.
 */
public final class CouponUpdateResponse extends BaseResponse {
  private final Coupon coupon;

  private CouponUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponUpdateResponse object. */
  public static CouponUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponUpdateResponse object with HTTP response. */
  public static CouponUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponUpdateResponse. */
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

    public CouponUpdateResponse build() {
      return new CouponUpdateResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
