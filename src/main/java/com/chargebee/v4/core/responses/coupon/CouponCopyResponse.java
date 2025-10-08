package com.chargebee.v4.core.responses.coupon;

import com.chargebee.v4.core.models.coupon.Coupon;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for CouponCopy operation. Contains the response data from the API. */
public final class CouponCopyResponse {

  private final Coupon coupon;

  private final Response httpResponse;

  private CouponCopyResponse(Builder builder) {

    this.coupon = builder.coupon;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CouponCopyResponse object. */
  public static CouponCopyResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponCopyResponse object with HTTP response. */
  public static CouponCopyResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponJson = JsonUtil.getObject(json, "coupon");
      if (__couponJson != null) {
        builder.coupon(Coupon.fromJson(__couponJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponCopyResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponCopyResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponCopyResponse. */
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

    public CouponCopyResponse build() {
      return new CouponCopyResponse(this);
    }
  }

  /** Get the coupon from the response. */
  public Coupon getCoupon() {
    return coupon;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
