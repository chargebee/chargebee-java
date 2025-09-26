package com.chargebee.core.responses.creditNote;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CreditNoteResendEinvoice operation. Contains the response data from
 * the API.
 */
public final class CreditNoteResendEinvoiceResponse {

  private final CreditNote creditNote;

  private CreditNoteResendEinvoiceResponse(Builder builder) {

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into CreditNoteResendEinvoiceResponse object. */
  public static CreditNoteResendEinvoiceResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteResendEinvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteResendEinvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteResendEinvoiceResponse. */
  public static class Builder {

    private CreditNote creditNote;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public CreditNoteResendEinvoiceResponse build() {
      return new CreditNoteResendEinvoiceResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
