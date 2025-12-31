package com.chargebee.v4.models.creditNote.responses;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ImportCreditNote operation. Contains the response data from the
 * API.
 */
public final class ImportCreditNoteResponse extends BaseResponse {
  private final CreditNote creditNote;

  private ImportCreditNoteResponse(Builder builder) {
    super(builder.httpResponse);

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into ImportCreditNoteResponse object. */
  public static ImportCreditNoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ImportCreditNoteResponse object with HTTP response. */
  public static ImportCreditNoteResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ImportCreditNoteResponse from JSON", e);
    }
  }

  /** Create a new builder for ImportCreditNoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ImportCreditNoteResponse. */
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

    public ImportCreditNoteResponse build() {
      return new ImportCreditNoteResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }

  @Override
  public String toString() {
    return "ImportCreditNoteResponse{" + "creditNote=" + creditNote + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ImportCreditNoteResponse that = (ImportCreditNoteResponse) o;
    return java.util.Objects.equals(creditNote, that.creditNote);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(creditNote);
  }
}
