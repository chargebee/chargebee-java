package com.chargebee.v4.models.creditNote.responses;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreditNoteRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CreditNoteRetrieveResponse extends BaseResponse {
  private final CreditNote creditNote;

  private CreditNoteRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into CreditNoteRetrieveResponse object. */
  public static CreditNoteRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteRetrieveResponse object with HTTP response. */
  public static CreditNoteRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteRetrieveResponse. */
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

    public CreditNoteRetrieveResponse build() {
      return new CreditNoteRetrieveResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }

  @Override
  public String toString() {
    return "CreditNoteRetrieveResponse{" + "creditNote=" + creditNote + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CreditNoteRetrieveResponse that = (CreditNoteRetrieveResponse) o;
    return java.util.Objects.equals(creditNote, that.creditNote);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(creditNote);
  }
}
