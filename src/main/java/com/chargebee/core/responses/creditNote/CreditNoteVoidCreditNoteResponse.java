package com.chargebee.core.responses.creditNote;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CreditNoteVoidCreditNote operation. Contains the response data from
 * the API.
 */
public final class CreditNoteVoidCreditNoteResponse {

  private final CreditNote creditNote;

  private CreditNoteVoidCreditNoteResponse(Builder builder) {

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into CreditNoteVoidCreditNoteResponse object. */
  public static CreditNoteVoidCreditNoteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

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

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
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
}
