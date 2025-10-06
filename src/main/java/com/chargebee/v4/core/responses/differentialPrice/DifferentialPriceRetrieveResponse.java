package com.chargebee.v4.core.responses.differentialPrice;

import com.chargebee.v4.core.models.differentialPrice.DifferentialPrice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for DifferentialPriceRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class DifferentialPriceRetrieveResponse {

  private final DifferentialPrice differentialPrice;

  private DifferentialPriceRetrieveResponse(DifferentialPrice differentialPrice) {

    this.differentialPrice = differentialPrice;
  }

  /** Parse JSON response into DifferentialPriceRetrieveResponse object. */
  public static DifferentialPriceRetrieveResponse fromJson(String json) {
    try {

      DifferentialPrice differentialPrice =
          DifferentialPrice.fromJson(JsonUtil.getObject(json, "differential_price"));

      return new DifferentialPriceRetrieveResponse(differentialPrice);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DifferentialPriceRetrieveResponse from JSON", e);
    }
  }

  /** Get the differentialPrice from the response. */
  public DifferentialPrice getDifferentialPrice() {
    return differentialPrice;
  }
}
