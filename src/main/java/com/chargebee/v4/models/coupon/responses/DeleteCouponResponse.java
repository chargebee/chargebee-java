package com.chargebee.v4.models.coupon.responses;

import com.chargebee.v4.models.coupon.Coupon;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteCoupon operation. Contains the response data from the API.
 */
public final class DeleteCouponResponse extends BaseResponse {
  private final Coupon coupon;

  private DeleteCouponResponse(Builder builder) {
    super(builder.httpResponse);

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into DeleteCouponResponse object. */
  public static DeleteCouponResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteCouponResponse object with HTTP response. */
  public static DeleteCouponResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteCouponResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteCouponResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteCouponResponse. */
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

    public DeleteCouponResponse build() {
      return new DeleteCouponResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
