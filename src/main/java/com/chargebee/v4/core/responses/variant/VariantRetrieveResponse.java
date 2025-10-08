package com.chargebee.v4.core.responses.variant;

import com.chargebee.v4.core.models.variant.Variant;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for VariantRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class VariantRetrieveResponse {

  private final Variant variant;

  private final Response httpResponse;

  private VariantRetrieveResponse(Variant variant, Response httpResponse) {

    this.variant = variant;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into VariantRetrieveResponse object. */
  public static VariantRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VariantRetrieveResponse object with HTTP response. */
  public static VariantRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Variant variant = Variant.fromJson(JsonUtil.getObject(json, "variant"));

      return new VariantRetrieveResponse(variant, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VariantRetrieveResponse from JSON", e);
    }
  }

  /** Get the variant from the response. */
  public Variant getVariant() {
    return variant;
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
