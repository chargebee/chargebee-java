package com.chargebee.core.responses.creditNote;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CreditNoteDelete operation. Contains the response data from the
 * API.
 */
public final class CreditNoteDeleteResponse {

  private final CreditNote creditNote;

  private CreditNoteDeleteResponse(Builder builder) {

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into CreditNoteDeleteResponse object. */
  public static CreditNoteDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteDeleteResponse. */
  public static class Builder {

    private CreditNote creditNote;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public CreditNoteDeleteResponse build() {
      return new CreditNoteDeleteResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
