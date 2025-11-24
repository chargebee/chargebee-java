package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RecordRefundForInvoice operation. Contains the response data from
 * the API.
 */
public final class RecordRefundForInvoiceResponse extends BaseResponse {
  private final Invoice invoice;

  private final Transaction transaction;

  private final CreditNote creditNote;

  private RecordRefundForInvoiceResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;

    this.transaction = builder.transaction;

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into RecordRefundForInvoiceResponse object. */
  public static RecordRefundForInvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RecordRefundForInvoiceResponse object with HTTP response. */
  public static RecordRefundForInvoiceResponse fromJson(String json, Response httpResponse) {
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

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RecordRefundForInvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for RecordRefundForInvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RecordRefundForInvoiceResponse. */
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

    public RecordRefundForInvoiceResponse build() {
      return new RecordRefundForInvoiceResponse(this);
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
