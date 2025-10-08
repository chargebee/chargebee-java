package com.chargebee.v4.core.responses.pc2MigrationItem;

import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for Pc2MigrationItemRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class Pc2MigrationItemRetrieveResponse {

  private final Response httpResponse;

  private Pc2MigrationItemRetrieveResponse(Response httpResponse) {

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into Pc2MigrationItemRetrieveResponse object. */
  public static Pc2MigrationItemRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into Pc2MigrationItemRetrieveResponse object with HTTP response. */
  public static Pc2MigrationItemRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      return new Pc2MigrationItemRetrieveResponse(httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemRetrieveResponse from JSON", e);
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
