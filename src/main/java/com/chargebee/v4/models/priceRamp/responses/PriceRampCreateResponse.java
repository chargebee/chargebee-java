package com.chargebee.v4.models.priceRamp.responses;

import com.chargebee.v4.models.priceRamp.PriceRamp;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PriceRampCreate operation. Contains the response data from the API.
 */
public final class PriceRampCreateResponse extends BaseResponse {
  private final PriceRamp priceRamp;

  private PriceRampCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.priceRamp = builder.priceRamp;
  }

  /** Parse JSON response into PriceRampCreateResponse object. */
  public static PriceRampCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PriceRampCreateResponse object with HTTP response. */
  public static PriceRampCreateResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse PriceRampCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for PriceRampCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PriceRampCreateResponse. */
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

    public PriceRampCreateResponse build() {
      return new PriceRampCreateResponse(this);
    }
  }

  /** Get the priceRamp from the response. */
  public PriceRamp getPriceRamp() {
    return priceRamp;
  }

  @Override
  public String toString() {
    return "PriceRampCreateResponse{" + "priceRamp=" + priceRamp + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PriceRampCreateResponse that = (PriceRampCreateResponse) o;
    return java.util.Objects.equals(priceRamp, that.priceRamp);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(priceRamp);
  }
}
