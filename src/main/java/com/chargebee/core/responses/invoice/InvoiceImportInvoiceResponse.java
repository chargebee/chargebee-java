package com.chargebee.core.responses.invoice;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for InvoiceImportInvoice operation. Contains the response data from the
 * API.
 */
public final class InvoiceImportInvoiceResponse {

  private final Invoice invoice;

  private final CreditNote creditNote;

  private InvoiceImportInvoiceResponse(Builder builder) {

    this.invoice = builder.invoice;

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into InvoiceImportInvoiceResponse object. */
  public static InvoiceImportInvoiceResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceImportInvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceImportInvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceImportInvoiceResponse. */
  public static class Builder {

    private Invoice invoice;

    private CreditNote creditNote;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public InvoiceImportInvoiceResponse build() {
      return new InvoiceImportInvoiceResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
