package com.chargebee.v4.models.priceVariant.responses;

import com.chargebee.v4.models.priceVariant.PriceVariant;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeletePriceVariant operation. Contains the response data from the
 * API.
 */
public final class DeletePriceVariantResponse extends BaseResponse {
  private final PriceVariant priceVariant;

  private DeletePriceVariantResponse(Builder builder) {
    super(builder.httpResponse);

    this.priceVariant = builder.priceVariant;
  }

  /** Parse JSON response into DeletePriceVariantResponse object. */
  public static DeletePriceVariantResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeletePriceVariantResponse object with HTTP response. */
  public static DeletePriceVariantResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __priceVariantJson = JsonUtil.getObject(json, "price_variant");
      if (__priceVariantJson != null) {
        builder.priceVariant(PriceVariant.fromJson(__priceVariantJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeletePriceVariantResponse from JSON", e);
    }
  }

  /** Create a new builder for DeletePriceVariantResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeletePriceVariantResponse. */
  public static class Builder {

    private PriceVariant priceVariant;

    private Response httpResponse;

    private Builder() {}

    public Builder priceVariant(PriceVariant priceVariant) {
      this.priceVariant = priceVariant;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public DeletePriceVariantResponse build() {
      return new DeletePriceVariantResponse(this);
    }
  }

  /** Get the priceVariant from the response. */
  public PriceVariant getPriceVariant() {
    return priceVariant;
  }
}
