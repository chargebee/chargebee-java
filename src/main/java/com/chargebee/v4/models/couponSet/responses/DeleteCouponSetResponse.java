package com.chargebee.v4.models.couponSet.responses;

import com.chargebee.v4.models.couponSet.CouponSet;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteCouponSet operation. Contains the response data from the API.
 */
public final class DeleteCouponSetResponse extends BaseResponse {
  private final CouponSet couponSet;

  private DeleteCouponSetResponse(Builder builder) {
    super(builder.httpResponse);

    this.couponSet = builder.couponSet;
  }

  /** Parse JSON response into DeleteCouponSetResponse object. */
  public static DeleteCouponSetResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteCouponSetResponse object with HTTP response. */
  public static DeleteCouponSetResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        builder.couponSet(CouponSet.fromJson(__couponSetJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteCouponSetResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteCouponSetResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteCouponSetResponse. */
  public static class Builder {

    private CouponSet couponSet;

    private Response httpResponse;

    private Builder() {}

    public Builder couponSet(CouponSet couponSet) {
      this.couponSet = couponSet;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DeleteCouponSetResponse build() {
      return new DeleteCouponSetResponse(this);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }
}
