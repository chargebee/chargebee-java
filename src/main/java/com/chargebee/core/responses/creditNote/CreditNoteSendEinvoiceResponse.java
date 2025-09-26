package com.chargebee.core.responses.creditNote;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CreditNoteSendEinvoice operation. Contains the response data from
 * the API.
 */
public final class CreditNoteSendEinvoiceResponse {

  private final CreditNote creditNote;

  private CreditNoteSendEinvoiceResponse(Builder builder) {

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into CreditNoteSendEinvoiceResponse object. */
  public static CreditNoteSendEinvoiceResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteSendEinvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteSendEinvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteSendEinvoiceResponse. */
  public static class Builder {

    private CreditNote creditNote;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public CreditNoteSendEinvoiceResponse build() {
      return new CreditNoteSendEinvoiceResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
