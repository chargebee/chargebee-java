package com.chargebee.v4.core.responses.differentialPrice;

import com.chargebee.v4.core.models.differentialPrice.DifferentialPrice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for DifferentialPriceDelete operation. Contains the response data from
 * the API.
 */
public final class DifferentialPriceDeleteResponse {

  private final DifferentialPrice differentialPrice;

  private DifferentialPriceDeleteResponse(Builder builder) {

    this.differentialPrice = builder.differentialPrice;
  }

  /** Parse JSON response into DifferentialPriceDeleteResponse object. */
  public static DifferentialPriceDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __differentialPriceJson = JsonUtil.getObject(json, "differential_price");
      if (__differentialPriceJson != null) {
        builder.differentialPrice(DifferentialPrice.fromJson(__differentialPriceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DifferentialPriceDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for DifferentialPriceDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DifferentialPriceDeleteResponse. */
  public static class Builder {

    private DifferentialPrice differentialPrice;

    private Builder() {}

    public Builder differentialPrice(DifferentialPrice differentialPrice) {
      this.differentialPrice = differentialPrice;
      return this;
    }

    public DifferentialPriceDeleteResponse build() {
      return new DifferentialPriceDeleteResponse(this);
    }
  }

  /** Get the differentialPrice from the response. */
  public DifferentialPrice getDifferentialPrice() {
    return differentialPrice;
  }
}
