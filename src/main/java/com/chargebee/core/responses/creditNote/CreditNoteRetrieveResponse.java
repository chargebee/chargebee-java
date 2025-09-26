package com.chargebee.core.responses.creditNote;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CreditNoteRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CreditNoteRetrieveResponse {

  private final CreditNote creditNote;

  private CreditNoteRetrieveResponse(CreditNote creditNote) {

    this.creditNote = creditNote;
  }

  /** Parse JSON response into CreditNoteRetrieveResponse object. */
  public static CreditNoteRetrieveResponse fromJson(String json) {
    try {

      CreditNote creditNote = CreditNote.fromJson(JsonUtil.getObject(json, "credit_note"));

      return new CreditNoteRetrieveResponse(creditNote);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteRetrieveResponse from JSON", e);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
