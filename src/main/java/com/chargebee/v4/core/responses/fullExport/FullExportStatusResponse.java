package com.chargebee.v4.core.responses.fullExport;

import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for FullExportStatus operation. Contains the response data from a
 * single resource get operation.
 */
public final class FullExportStatusResponse {

  private final Response httpResponse;

  private FullExportStatusResponse(Response httpResponse) {

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into FullExportStatusResponse object. */
  public static FullExportStatusResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into FullExportStatusResponse object with HTTP response. */
  public static FullExportStatusResponse fromJson(String json, Response httpResponse) {
    try {

      return new FullExportStatusResponse(httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse FullExportStatusResponse from JSON", e);
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
