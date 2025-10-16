package com.chargebee.v4.core.responses.priceVariant;

import com.chargebee.v4.core.models.priceVariant.PriceVariant;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PriceVariantUpdate operation. Contains the response data from the
 * API.
 */
public final class PriceVariantUpdateResponse extends BaseResponse {
  private final PriceVariant priceVariant;

  private PriceVariantUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.priceVariant = builder.priceVariant;
  }

  /** Parse JSON response into PriceVariantUpdateResponse object. */
  public static PriceVariantUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PriceVariantUpdateResponse object with HTTP response. */
  public static PriceVariantUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __priceVariantJson = JsonUtil.getObject(json, "price_variant");
      if (__priceVariantJson != null) {
        builder.priceVariant(PriceVariant.fromJson(__priceVariantJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceVariantUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for PriceVariantUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PriceVariantUpdateResponse. */
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

    public PriceVariantUpdateResponse build() {
      return new PriceVariantUpdateResponse(this);
    }
  }

  /** Get the priceVariant from the response. */
  public PriceVariant getPriceVariant() {
    return priceVariant;
  }
}
