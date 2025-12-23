package com.chargebee.v4.models.couponCode.responses;

import com.chargebee.v4.models.couponCode.CouponCode;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponCodeArchive operation. Contains the response data from the
 * API.
 */
public final class CouponCodeArchiveResponse extends BaseResponse {
  private final CouponCode couponCode;

  private CouponCodeArchiveResponse(Builder builder) {
    super(builder.httpResponse);

    this.couponCode = builder.couponCode;
  }

  /** Parse JSON response into CouponCodeArchiveResponse object. */
  public static CouponCodeArchiveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponCodeArchiveResponse object with HTTP response. */
  public static CouponCodeArchiveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponCodeJson = JsonUtil.getObject(json, "coupon_code");
      if (__couponCodeJson != null) {
        builder.couponCode(CouponCode.fromJson(__couponCodeJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCodeArchiveResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponCodeArchiveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponCodeArchiveResponse. */
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

    public CouponCodeArchiveResponse build() {
      return new CouponCodeArchiveResponse(this);
    }
  }

  /** Get the couponCode from the response. */
  public CouponCode getCouponCode() {
    return couponCode;
  }
}
