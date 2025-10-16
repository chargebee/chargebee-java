package com.chargebee.v4.core.responses.coupon;

import com.chargebee.v4.core.models.coupon.Coupon;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponCreate operation. Contains the response data from the API.
 */
public final class CouponCreateResponse extends BaseResponse {
  private final Coupon coupon;

  private CouponCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into CouponCreateResponse object. */
  public static CouponCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponCreateResponse object with HTTP response. */
  public static CouponCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponCreateResponse. */
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

    public CouponCreateResponse build() {
      return new CouponCreateResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
