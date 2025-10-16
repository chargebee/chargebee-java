package com.chargebee.v4.core.responses.couponCode;

import com.chargebee.v4.core.models.couponCode.CouponCode;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponCodeCreate operation. Contains the response data from the
 * API.
 */
public final class CouponCodeCreateResponse extends BaseResponse {
  private final CouponCode couponCode;

  private CouponCodeCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.couponCode = builder.couponCode;
  }

  /** Parse JSON response into CouponCodeCreateResponse object. */
  public static CouponCodeCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponCodeCreateResponse object with HTTP response. */
  public static CouponCodeCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponCodeJson = JsonUtil.getObject(json, "coupon_code");
      if (__couponCodeJson != null) {
        builder.couponCode(CouponCode.fromJson(__couponCodeJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCodeCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponCodeCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponCodeCreateResponse. */
  public static class Builder {

    private CouponCode couponCode;

    private Response httpResponse;

    private Builder() {}

    public Builder couponCode(CouponCode couponCode) {
      this.couponCode = couponCode;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CouponCodeCreateResponse build() {
      return new CouponCodeCreateResponse(this);
    }
  }

  /** Get the couponCode from the response. */
  public CouponCode getCouponCode() {
    return couponCode;
  }
}
