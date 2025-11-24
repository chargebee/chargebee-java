package com.chargebee.v4.models.differentialPrice.responses;

import com.chargebee.v4.models.differentialPrice.DifferentialPrice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DifferentialPriceRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class DifferentialPriceRetrieveResponse extends BaseResponse {
  private final DifferentialPrice differentialPrice;

  private DifferentialPriceRetrieveResponse(
      DifferentialPrice differentialPrice, Response httpResponse) {
    super(httpResponse);

    this.differentialPrice = differentialPrice;
  }

  /** Parse JSON response into DifferentialPriceRetrieveResponse object. */
  public static DifferentialPriceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DifferentialPriceRetrieveResponse object with HTTP response. */
  public static DifferentialPriceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      DifferentialPrice differentialPrice =
          DifferentialPrice.fromJson(JsonUtil.getObject(json, "differential_price"));

      return new DifferentialPriceRetrieveResponse(differentialPrice, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DifferentialPriceRetrieveResponse from JSON", e);
    }
  }

  /** Get the differentialPrice from the response. */
  public DifferentialPrice getDifferentialPrice() {
    return differentialPrice;
  }
}
