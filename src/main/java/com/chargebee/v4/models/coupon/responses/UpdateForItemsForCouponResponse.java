package com.chargebee.v4.models.coupon.responses;

import com.chargebee.v4.models.coupon.Coupon;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for UpdateForItemsForCoupon operation. Contains the response data from
 * the API.
 */
public final class UpdateForItemsForCouponResponse extends BaseResponse {
  private final Coupon coupon;

  private UpdateForItemsForCouponResponse(Builder builder) {
    super(builder.httpResponse);

    this.coupon = builder.coupon;
  }

  /** Parse JSON response into UpdateForItemsForCouponResponse object. */
  public static UpdateForItemsForCouponResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into UpdateForItemsForCouponResponse object with HTTP response. */
  public static UpdateForItemsForCouponResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse UpdateForItemsForCouponResponse from JSON", e);
    }
  }

  /** Create a new builder for UpdateForItemsForCouponResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for UpdateForItemsForCouponResponse. */
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

    public UpdateForItemsForCouponResponse build() {
      return new UpdateForItemsForCouponResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }
}
