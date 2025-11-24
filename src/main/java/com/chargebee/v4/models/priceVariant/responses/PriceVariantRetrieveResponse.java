package com.chargebee.v4.models.priceVariant.responses;

import com.chargebee.v4.models.priceVariant.PriceVariant;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PriceVariantRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PriceVariantRetrieveResponse extends BaseResponse {
  private final PriceVariant priceVariant;

  private PriceVariantRetrieveResponse(PriceVariant priceVariant, Response httpResponse) {
    super(httpResponse);

    this.priceVariant = priceVariant;
  }

  /** Parse JSON response into PriceVariantRetrieveResponse object. */
  public static PriceVariantRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PriceVariantRetrieveResponse object with HTTP response. */
  public static PriceVariantRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      PriceVariant priceVariant = PriceVariant.fromJson(JsonUtil.getObject(json, "price_variant"));

      return new PriceVariantRetrieveResponse(priceVariant, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceVariantRetrieveResponse from JSON", e);
    }
  }

  /** Get the priceVariant from the response. */
  public PriceVariant getPriceVariant() {
    return priceVariant;
  }
}
