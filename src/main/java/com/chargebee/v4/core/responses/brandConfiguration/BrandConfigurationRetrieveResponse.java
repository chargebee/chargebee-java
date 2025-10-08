package com.chargebee.v4.core.responses.brandConfiguration;

import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for BrandConfigurationRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class BrandConfigurationRetrieveResponse {

  private final Response httpResponse;

  private BrandConfigurationRetrieveResponse(Response httpResponse) {

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into BrandConfigurationRetrieveResponse object. */
  public static BrandConfigurationRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into BrandConfigurationRetrieveResponse object with HTTP response. */
  public static BrandConfigurationRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      return new BrandConfigurationRetrieveResponse(httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BrandConfigurationRetrieveResponse from JSON", e);
    }
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
