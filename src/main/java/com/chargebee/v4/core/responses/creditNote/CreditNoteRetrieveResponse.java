package com.chargebee.v4.core.responses.creditNote;

import com.chargebee.v4.core.models.creditNote.CreditNote;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreditNoteRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CreditNoteRetrieveResponse {

  private final CreditNote creditNote;

  private final Response httpResponse;

  private CreditNoteRetrieveResponse(CreditNote creditNote, Response httpResponse) {

    this.creditNote = creditNote;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into CreditNoteRetrieveResponse object. */
  public static CreditNoteRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteRetrieveResponse object with HTTP response. */
  public static CreditNoteRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      CreditNote creditNote = CreditNote.fromJson(JsonUtil.getObject(json, "credit_note"));

      return new CreditNoteRetrieveResponse(creditNote, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteRetrieveResponse from JSON", e);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
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
