package com.chargebee.core.responses.differentialPrice;

import com.chargebee.core.models.differentialPrice.DifferentialPrice;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for DifferentialPriceUpdate operation. Contains the response data from
 * the API.
 */
public final class DifferentialPriceUpdateResponse {

  private final DifferentialPrice differentialPrice;

  private DifferentialPriceUpdateResponse(Builder builder) {

    this.differentialPrice = builder.differentialPrice;
  }

  /** Parse JSON response into DifferentialPriceUpdateResponse object. */
  public static DifferentialPriceUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __differentialPriceJson = JsonUtil.getObject(json, "differential_price");
      if (__differentialPriceJson != null) {
        builder.differentialPrice(DifferentialPrice.fromJson(__differentialPriceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DifferentialPriceUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for DifferentialPriceUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DifferentialPriceUpdateResponse. */
  public static class Builder {

    private DifferentialPrice differentialPrice;

    private Builder() {}

    public Builder differentialPrice(DifferentialPrice differentialPrice) {
      this.differentialPrice = differentialPrice;
      return this;
    }

    public DifferentialPriceUpdateResponse build() {
      return new DifferentialPriceUpdateResponse(this);
    }
  }

  /** Get the differentialPrice from the response. */
  public DifferentialPrice getDifferentialPrice() {
    return differentialPrice;
  }
}
