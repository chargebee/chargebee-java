package com.chargebee.v4.core.responses.export;

import com.chargebee.v4.core.models.export.Export;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ExportRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class ExportRetrieveResponse {

  private final Export export;

  private final Response httpResponse;

  private ExportRetrieveResponse(Export export, Response httpResponse) {

    this.export = export;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into ExportRetrieveResponse object. */
  public static ExportRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ExportRetrieveResponse object with HTTP response. */
  public static ExportRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Export export = Export.fromJson(JsonUtil.getObject(json, "export"));

      return new ExportRetrieveResponse(export, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ExportRetrieveResponse from JSON", e);
    }
  }

  /** Get the export from the response. */
  public Export getExport() {
    return export;
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
