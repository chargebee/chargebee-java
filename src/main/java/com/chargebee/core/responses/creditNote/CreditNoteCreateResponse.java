package com.chargebee.core.responses.creditNote;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CreditNoteCreate operation. Contains the response data from the
 * API.
 */
public final class CreditNoteCreateResponse {

  private final CreditNote creditNote;

  private final Invoice invoice;

  private CreditNoteCreateResponse(Builder builder) {

    this.creditNote = builder.creditNote;

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into CreditNoteCreateResponse object. */
  public static CreditNoteCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteCreateResponse. */
  public static class Builder {

    private CreditNote creditNote;

    private Invoice invoice;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public CreditNoteCreateResponse build() {
      return new CreditNoteCreateResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
