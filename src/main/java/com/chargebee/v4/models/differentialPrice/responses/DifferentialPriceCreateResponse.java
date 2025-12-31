package com.chargebee.v4.models.differentialPrice.responses;

import com.chargebee.v4.models.differentialPrice.DifferentialPrice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DifferentialPriceCreate operation. Contains the response data from
 * the API.
 */
public final class DifferentialPriceCreateResponse extends BaseResponse {
  private final DifferentialPrice differentialPrice;

  private DifferentialPriceCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.differentialPrice = builder.differentialPrice;
  }

  /** Parse JSON response into DifferentialPriceCreateResponse object. */
  public static DifferentialPriceCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DifferentialPriceCreateResponse object with HTTP response. */
  public static DifferentialPriceCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __differentialPriceJson = JsonUtil.getObject(json, "differential_price");
      if (__differentialPriceJson != null) {
        builder.differentialPrice(DifferentialPrice.fromJson(__differentialPriceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DifferentialPriceCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for DifferentialPriceCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DifferentialPriceCreateResponse. */
  public static class Builder {

    private DifferentialPrice differentialPrice;

    private Response httpResponse;

    private Builder() {}

    public Builder differentialPrice(DifferentialPrice differentialPrice) {
      this.differentialPrice = differentialPrice;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DifferentialPriceCreateResponse build() {
      return new DifferentialPriceCreateResponse(this);
    }
  }

  /** Get the differentialPrice from the response. */
  public DifferentialPrice getDifferentialPrice() {
    return differentialPrice;
  }

  @Override
  public String toString() {
    return "DifferentialPriceCreateResponse{" + "differentialPrice=" + differentialPrice + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DifferentialPriceCreateResponse that = (DifferentialPriceCreateResponse) o;
    return java.util.Objects.equals(differentialPrice, that.differentialPrice);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(differentialPrice);
  }
}
