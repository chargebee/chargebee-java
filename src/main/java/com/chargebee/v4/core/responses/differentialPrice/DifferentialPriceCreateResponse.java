package com.chargebee.v4.core.responses.differentialPrice;

import com.chargebee.v4.core.models.differentialPrice.DifferentialPrice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for DifferentialPriceCreate operation. Contains the response data from
 * the API.
 */
public final class DifferentialPriceCreateResponse {

  private final DifferentialPrice differentialPrice;

  private DifferentialPriceCreateResponse(Builder builder) {

    this.differentialPrice = builder.differentialPrice;
  }

  /** Parse JSON response into DifferentialPriceCreateResponse object. */
  public static DifferentialPriceCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __differentialPriceJson = JsonUtil.getObject(json, "differential_price");
      if (__differentialPriceJson != null) {
        builder.differentialPrice(DifferentialPrice.fromJson(__differentialPriceJson));
      }

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

    private Builder() {}

    public Builder differentialPrice(DifferentialPrice differentialPrice) {
      this.differentialPrice = differentialPrice;
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
}
