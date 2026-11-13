package com.chargebee.v4.models.priceRamp.responses;

import com.chargebee.v4.models.priceRamp.PriceRamp;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PriceRampDelete operation. Contains the response data from the API.
 */
public final class PriceRampDeleteResponse extends BaseResponse {
  private final PriceRamp priceRamp;

  private PriceRampDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.priceRamp = builder.priceRamp;
  }

  /** Parse JSON response into PriceRampDeleteResponse object. */
  public static PriceRampDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PriceRampDeleteResponse object with HTTP response. */
  public static PriceRampDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __priceRampObj = JsonUtil.getJsonObject(jsonObj, "price_ramp");
      if (__priceRampObj != null) {
        builder.priceRamp(PriceRamp.fromJson(__priceRampObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceRampDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for PriceRampDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PriceRampDeleteResponse. */
  public static class Builder {

    private PriceRamp priceRamp;

    private Response httpResponse;

    private Builder() {}

    public Builder priceRamp(PriceRamp priceRamp) {
      this.priceRamp = priceRamp;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public PriceRampDeleteResponse build() {
      return new PriceRampDeleteResponse(this);
    }
  }

  /** Get the priceRamp from the response. */
  public PriceRamp getPriceRamp() {
    return priceRamp;
  }

  @Override
  public String toString() {
    return "PriceRampDeleteResponse{" + "priceRamp=" + priceRamp + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PriceRampDeleteResponse that = (PriceRampDeleteResponse) o;
    return java.util.Objects.equals(priceRamp, that.priceRamp);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(priceRamp);
  }
}
