package com.chargebee.v4.core.responses.priceVariant;

import com.chargebee.v4.core.models.priceVariant.PriceVariant;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PriceVariantUpdate operation. Contains the response data from the
 * API.
 */
public final class PriceVariantUpdateResponse {

  private final PriceVariant priceVariant;

  private final Response httpResponse;

  private PriceVariantUpdateResponse(Builder builder) {

    this.priceVariant = builder.priceVariant;

    this.httpResponse = builder.httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
