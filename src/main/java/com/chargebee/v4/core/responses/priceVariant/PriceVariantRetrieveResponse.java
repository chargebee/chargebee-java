package com.chargebee.v4.core.responses.priceVariant;

import com.chargebee.v4.core.models.priceVariant.PriceVariant;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for PriceVariantRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class PriceVariantRetrieveResponse {

  private final PriceVariant priceVariant;

  private final Response httpResponse;

  private PriceVariantRetrieveResponse(PriceVariant priceVariant, Response httpResponse) {

    this.priceVariant = priceVariant;

    this.httpResponse = httpResponse;
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
