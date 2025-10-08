package com.chargebee.v4.core.responses.creditNote;

import com.chargebee.v4.core.models.creditNote.CreditNote;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreditNoteVoidCreditNote operation. Contains the response data from
 * the API.
 */
public final class CreditNoteVoidCreditNoteResponse {

  private final CreditNote creditNote;

  private final Response httpResponse;

  private CreditNoteVoidCreditNoteResponse(Builder builder) {

    this.creditNote = builder.creditNote;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CreditNoteVoidCreditNoteResponse object. */
  public static CreditNoteVoidCreditNoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteVoidCreditNoteResponse object with HTTP response. */
  public static CreditNoteVoidCreditNoteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteVoidCreditNoteResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteVoidCreditNoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteVoidCreditNoteResponse. */
  public static class Builder {

    private CreditNote creditNote;

    private Response httpResponse;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CreditNoteVoidCreditNoteResponse build() {
      return new CreditNoteVoidCreditNoteResponse(this);
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
