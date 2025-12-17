package com.chargebee.v4.models.couponCode.responses;

import com.chargebee.v4.models.couponCode.CouponCode;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponCodeRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CouponCodeRetrieveResponse extends BaseResponse {
  private final CouponCode couponCode;

  private CouponCodeRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.couponCode = builder.couponCode;
  }

  /** Parse JSON response into CouponCodeRetrieveResponse object. */
  public static CouponCodeRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponCodeRetrieveResponse object with HTTP response. */
  public static CouponCodeRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponCodeJson = JsonUtil.getObject(json, "coupon_code");
      if (__couponCodeJson != null) {
        builder.couponCode(CouponCode.fromJson(__couponCodeJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCodeRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponCodeRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponCodeRetrieveResponse. */
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

    public CouponCodeRetrieveResponse build() {
      return new CouponCodeRetrieveResponse(this);
    }
  }

  /** Get the couponCode from the response. */
  public CouponCode getCouponCode() {
    return couponCode;
  }
}
