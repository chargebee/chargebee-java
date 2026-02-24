package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceRecordRefund operation. Contains the response data from the
 * API.
 */
public final class InvoiceRecordRefundResponse extends BaseResponse {
  private final Invoice invoice;

  private final Transaction transaction;

  private final CreditNote creditNote;

  private InvoiceRecordRefundResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;

    this.transaction = builder.transaction;

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into InvoiceRecordRefundResponse object. */
  public static InvoiceRecordRefundResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceRecordRefundResponse object with HTTP response. */
  public static InvoiceRecordRefundResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __invoiceObj = JsonUtil.getJsonObject(jsonObj, "invoice");
      if (__invoiceObj != null) {
        builder.invoice(Invoice.fromJson(__invoiceObj));
      }

      JsonObject __transactionObj = JsonUtil.getJsonObject(jsonObj, "transaction");
      if (__transactionObj != null) {
        builder.transaction(Transaction.fromJson(__transactionObj));
      }

      JsonObject __creditNoteObj = JsonUtil.getJsonObject(jsonObj, "credit_note");
      if (__creditNoteObj != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceRecordRefundResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceRecordRefundResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceRecordRefundResponse. */
  public static class Builder {

    private Invoice invoice;

    private Transaction transaction;

    private CreditNote creditNote;

    private Response httpResponse;

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

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public InvoiceRecordRefundResponse build() {
      return new InvoiceRecordRefundResponse(this);
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

  @Override
  public String toString() {
    return "InvoiceRecordRefundResponse{"
        + "invoice="
        + invoice
        + ", transaction="
        + transaction
        + ", creditNote="
        + creditNote
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InvoiceRecordRefundResponse that = (InvoiceRecordRefundResponse) o;
    return java.util.Objects.equals(invoice, that.invoice)
        && java.util.Objects.equals(transaction, that.transaction)
        && java.util.Objects.equals(creditNote, that.creditNote);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(invoice, transaction, creditNote);
  }
}
