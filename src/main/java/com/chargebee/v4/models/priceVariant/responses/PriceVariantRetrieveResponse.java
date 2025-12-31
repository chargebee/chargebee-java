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

  private PriceVariantRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.priceVariant = builder.priceVariant;
  }

  /** Parse JSON response into PriceVariantRetrieveResponse object. */
  public static PriceVariantRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into PriceVariantRetrieveResponse object with HTTP response. */
  public static PriceVariantRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __priceVariantJson = JsonUtil.getObject(json, "price_variant");
      if (__priceVariantJson != null) {
        builder.priceVariant(PriceVariant.fromJson(__priceVariantJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse PriceVariantRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for PriceVariantRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for PriceVariantRetrieveResponse. */
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

    public PriceVariantRetrieveResponse build() {
      return new PriceVariantRetrieveResponse(this);
    }
  }

  /** Get the priceVariant from the response. */
  public PriceVariant getPriceVariant() {
    return priceVariant;
  }

  @Override
  public String toString() {
    return "PriceVariantRetrieveResponse{" + "priceVariant=" + priceVariant + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PriceVariantRetrieveResponse that = (PriceVariantRetrieveResponse) o;
    return java.util.Objects.equals(priceVariant, that.priceVariant);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(priceVariant);
  }
}
