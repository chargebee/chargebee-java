package com.chargebee.v4.models.priceRamp.responses;

import com.chargebee.v4.models.priceRamp.PriceRamp;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PriceRampRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PriceRampRetrieveResponse extends BaseResponse {
  private final PriceRamp priceRamp;

  private PriceRampRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.priceRamp = builder.priceRamp;
  }

  /** Parse JSON response into PriceRampRetrieveResponse object. */
  public static PriceRampRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PriceRampRetrieveResponse object with HTTP response. */
  public static PriceRampRetrieveResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse PriceRampRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for PriceRampRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PriceRampRetrieveResponse. */
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

    public PriceRampRetrieveResponse build() {
      return new PriceRampRetrieveResponse(this);
    }
  }

  /** Get the priceRamp from the response. */
  public PriceRamp getPriceRamp() {
    return priceRamp;
  }

  @Override
  public String toString() {
    return "PriceRampRetrieveResponse{" + "priceRamp=" + priceRamp + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PriceRampRetrieveResponse that = (PriceRampRetrieveResponse) o;
    return java.util.Objects.equals(priceRamp, that.priceRamp);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(priceRamp);
  }
}
