package com.chargebee.v4.models.couponSet.responses;

import com.chargebee.v4.models.couponSet.CouponSet;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteUnusedCouponCodesForCouponSet operation. Contains the
 * response data from the API.
 */
public final class DeleteUnusedCouponCodesForCouponSetResponse extends BaseResponse {
  private final CouponSet couponSet;

  private DeleteUnusedCouponCodesForCouponSetResponse(Builder builder) {
    super(builder.httpResponse);

    this.couponSet = builder.couponSet;
  }

  /** Parse JSON response into DeleteUnusedCouponCodesForCouponSetResponse object. */
  public static DeleteUnusedCouponCodesForCouponSetResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into DeleteUnusedCouponCodesForCouponSetResponse object with HTTP response.
   */
  public static DeleteUnusedCouponCodesForCouponSetResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        builder.couponSet(CouponSet.fromJson(__couponSetJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse DeleteUnusedCouponCodesForCouponSetResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteUnusedCouponCodesForCouponSetResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteUnusedCouponCodesForCouponSetResponse. */
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

    public DeleteUnusedCouponCodesForCouponSetResponse build() {
      return new DeleteUnusedCouponCodesForCouponSetResponse(this);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }
}
