package com.chargebee.v4.core.responses.creditNote;

import com.chargebee.v4.core.models.creditNote.CreditNote;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CreditNoteRemoveTaxWithheldRefund operation. Contains the response
 * data from the API.
 */
public final class CreditNoteRemoveTaxWithheldRefundResponse {

  private final CreditNote creditNote;

  private CreditNoteRemoveTaxWithheldRefundResponse(Builder builder) {

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into CreditNoteRemoveTaxWithheldRefundResponse object. */
  public static CreditNoteRemoveTaxWithheldRefundResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CreditNoteRemoveTaxWithheldRefundResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteRemoveTaxWithheldRefundResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteRemoveTaxWithheldRefundResponse. */
  public static class Builder {

    private CreditNote creditNote;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public CreditNoteRemoveTaxWithheldRefundResponse build() {
      return new CreditNoteRemoveTaxWithheldRefundResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
