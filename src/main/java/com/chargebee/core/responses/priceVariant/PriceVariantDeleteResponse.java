package com.chargebee.core.responses.priceVariant;

import com.chargebee.core.models.priceVariant.PriceVariant;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for PriceVariantDelete operation. Contains the response data from the
 * API.
 */
public final class PriceVariantDeleteResponse {

  private final PriceVariant priceVariant;

  private PriceVariantDeleteResponse(Builder builder) {

    this.priceVariant = builder.priceVariant;
  }

  /** Parse JSON response into PriceVariantDeleteResponse object. */
  public static PriceVariantDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __priceVariantJson = JsonUtil.getObject(json, "price_variant");
      if (__priceVariantJson != null) {
        builder.priceVariant(PriceVariant.fromJson(__priceVariantJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceVariantDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for PriceVariantDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PriceVariantDeleteResponse. */
  public static class Builder {

    private PriceVariant priceVariant;

    private Builder() {}

    public Builder priceVariant(PriceVariant priceVariant) {
      this.priceVariant = priceVariant;
      return this;
    }

    public PriceVariantDeleteResponse build() {
      return new PriceVariantDeleteResponse(this);
    }
  }

  /** Get the priceVariant from the response. */
  public PriceVariant getPriceVariant() {
    return priceVariant;
  }
}
