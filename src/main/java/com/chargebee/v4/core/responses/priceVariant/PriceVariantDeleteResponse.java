package com.chargebee.v4.core.responses.priceVariant;

import com.chargebee.v4.core.models.priceVariant.PriceVariant;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PriceVariantDelete operation. Contains the response data from the
 * API.
 */
public final class PriceVariantDeleteResponse extends BaseResponse {
  private final PriceVariant priceVariant;

  private PriceVariantDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.priceVariant = builder.priceVariant;
  }

  /** Parse JSON response into PriceVariantDeleteResponse object. */
  public static PriceVariantDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PriceVariantDeleteResponse object with HTTP response. */
  public static PriceVariantDeleteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __priceVariantJson = JsonUtil.getObject(json, "price_variant");
      if (__priceVariantJson != null) {
        builder.priceVariant(PriceVariant.fromJson(__priceVariantJson));
      }

      builder.httpResponse(httpResponse);
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

    public PriceVariantDeleteResponse build() {
      return new PriceVariantDeleteResponse(this);
    }
  }

  /** Get the priceVariant from the response. */
  public PriceVariant getPriceVariant() {
    return priceVariant;
  }
}
