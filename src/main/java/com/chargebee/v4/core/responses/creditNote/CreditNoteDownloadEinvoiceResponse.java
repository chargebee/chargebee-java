package com.chargebee.v4.core.responses.creditNote;

import com.chargebee.v4.core.models.download.Download;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.util.List;

/**
 * Immutable response object for CreditNoteDownloadEinvoice operation. Contains the response data
 * from a single resource get operation.
 */
public final class CreditNoteDownloadEinvoiceResponse {

  private final List<Download> downloads;

  private final Response httpResponse;

  private CreditNoteDownloadEinvoiceResponse(List<Download> downloads, Response httpResponse) {

    this.downloads = downloads;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into CreditNoteDownloadEinvoiceResponse object. */
  public static CreditNoteDownloadEinvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteDownloadEinvoiceResponse object with HTTP response. */
  public static CreditNoteDownloadEinvoiceResponse fromJson(String json, Response httpResponse) {
    try {

      List<Download> downloads =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "downloads")).stream()
              .map(Download::fromJson)
              .collect(java.util.stream.Collectors.toList());

      return new CreditNoteDownloadEinvoiceResponse(downloads, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteDownloadEinvoiceResponse from JSON", e);
    }
  }

  /** Get the downloads from the response. */
  public List<Download> getDownloads() {
    return downloads;
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
