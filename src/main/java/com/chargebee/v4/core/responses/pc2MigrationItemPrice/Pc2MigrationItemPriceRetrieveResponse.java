package com.chargebee.v4.core.responses.pc2MigrationItemPrice;

import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemPriceRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class Pc2MigrationItemPriceRetrieveResponse {

  private final Response httpResponse;

  private Pc2MigrationItemPriceRetrieveResponse(Response httpResponse) {

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into Pc2MigrationItemPriceRetrieveResponse object. */
  public static Pc2MigrationItemPriceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemPriceRetrieveResponse object with HTTP response. */
  public static Pc2MigrationItemPriceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      return new Pc2MigrationItemPriceRetrieveResponse(httpResponse);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemPriceRetrieveResponse from JSON", e);
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
