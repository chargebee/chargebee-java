package com.chargebee.v4.core.responses.priceVariant;

import com.chargebee.v4.core.models.priceVariant.PriceVariant;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PriceVariantCreate operation. Contains the response data from the
 * API.
 */
public final class PriceVariantCreateResponse extends BaseResponse {
  private final PriceVariant priceVariant;

  private PriceVariantCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.priceVariant = builder.priceVariant;
  }

  /** Parse JSON response into PriceVariantCreateResponse object. */
  public static PriceVariantCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PriceVariantCreateResponse object with HTTP response. */
  public static PriceVariantCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __priceVariantJson = JsonUtil.getObject(json, "price_variant");
      if (__priceVariantJson != null) {
        builder.priceVariant(PriceVariant.fromJson(__priceVariantJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceVariantCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for PriceVariantCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PriceVariantCreateResponse. */
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

    public PriceVariantCreateResponse build() {
      return new PriceVariantCreateResponse(this);
    }
  }

  /** Get the priceVariant from the response. */
  public PriceVariant getPriceVariant() {
    return priceVariant;
  }
}
