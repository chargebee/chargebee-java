package com.chargebee.v4.core.responses.couponSet;

import com.chargebee.v4.core.models.couponSet.CouponSet;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponSetUpdate operation. Contains the response data from the API.
 */
public final class CouponSetUpdateResponse extends BaseResponse {
  private final CouponSet couponSet;

  private CouponSetUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.couponSet = builder.couponSet;
  }

  /** Parse JSON response into CouponSetUpdateResponse object. */
  public static CouponSetUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponSetUpdateResponse object with HTTP response. */
  public static CouponSetUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        builder.couponSet(CouponSet.fromJson(__couponSetJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponSetUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponSetUpdateResponse. */
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

    public CouponSetUpdateResponse build() {
      return new CouponSetUpdateResponse(this);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }
}
