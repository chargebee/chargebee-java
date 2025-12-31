package com.chargebee.v4.models.couponSet.responses;

import com.chargebee.v4.models.couponSet.CouponSet;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponSetRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CouponSetRetrieveResponse extends BaseResponse {
  private final CouponSet couponSet;

  private CouponSetRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.couponSet = builder.couponSet;
  }

  /** Parse JSON response into CouponSetRetrieveResponse object. */
  public static CouponSetRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponSetRetrieveResponse object with HTTP response. */
  public static CouponSetRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        builder.couponSet(CouponSet.fromJson(__couponSetJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponSetRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponSetRetrieveResponse. */
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

    public CouponSetRetrieveResponse build() {
      return new CouponSetRetrieveResponse(this);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
  }

  @Override
  public String toString() {
    return "CouponSetRetrieveResponse{" + "couponSet=" + couponSet + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CouponSetRetrieveResponse that = (CouponSetRetrieveResponse) o;
    return java.util.Objects.equals(couponSet, that.couponSet);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(couponSet);
  }
}
