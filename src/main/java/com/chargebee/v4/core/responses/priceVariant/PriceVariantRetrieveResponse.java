package com.chargebee.v4.core.responses.priceVariant;

import com.chargebee.v4.core.models.priceVariant.PriceVariant;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for PriceVariantRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PriceVariantRetrieveResponse {

  private final PriceVariant priceVariant;

  private PriceVariantRetrieveResponse(PriceVariant priceVariant) {

    this.priceVariant = priceVariant;
  }

  /** Parse JSON response into PriceVariantRetrieveResponse object. */
  public static PriceVariantRetrieveResponse fromJson(String json) {
    try {

      PriceVariant priceVariant = PriceVariant.fromJson(JsonUtil.getObject(json, "price_variant"));

      return new PriceVariantRetrieveResponse(priceVariant);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceVariantRetrieveResponse from JSON", e);
    }
  }

  /** Get the priceVariant from the response. */
  public PriceVariant getPriceVariant() {
    return priceVariant;
  }
}
