package com.chargebee.v4.core.responses.couponSet;

import com.chargebee.v4.core.models.couponSet.CouponSet;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CouponSetDelete operation. Contains the response data from the API.
 */
public final class CouponSetDeleteResponse {

  private final CouponSet couponSet;

  private final Response httpResponse;

  private CouponSetDeleteResponse(Builder builder) {

    this.couponSet = builder.couponSet;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CouponSetDeleteResponse object. */
  public static CouponSetDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CouponSetDeleteResponse object with HTTP response. */
  public static CouponSetDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __couponSetJson = JsonUtil.getObject(json, "coupon_set");
      if (__couponSetJson != null) {
        builder.couponSet(CouponSet.fromJson(__couponSetJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CouponSetDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for CouponSetDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CouponSetDeleteResponse. */
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

    public CouponSetDeleteResponse build() {
      return new CouponSetDeleteResponse(this);
    }
  }

  /** Get the couponSet from the response. */
  public CouponSet getCouponSet() {
    return couponSet;
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
