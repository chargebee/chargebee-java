package com.chargebee.core.responses.invoice;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.core.models.transaction.Transaction;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for InvoiceRefund operation. Contains the response data from the API.
 */
public final class InvoiceRefundResponse {

  private final Invoice invoice;

  private final Transaction transaction;

  private final CreditNote creditNote;

  private InvoiceRefundResponse(Builder builder) {

    this.invoice = builder.invoice;

    this.transaction = builder.transaction;

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into InvoiceRefundResponse object. */
  public static InvoiceRefundResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceRefundResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceRefundResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceRefundResponse. */
  public static class Builder {

    private Invoice invoice;

    private Transaction transaction;

    private CreditNote creditNote;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public InvoiceRefundResponse build() {
      return new InvoiceRefundResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
