package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for InvoiceCollectPayment operation. Contains the response data from
 * the API.
 */
public final class InvoiceCollectPaymentResponse {

  private final Invoice invoice;

  private final Transaction transaction;

  private InvoiceCollectPaymentResponse(Builder builder) {

    this.invoice = builder.invoice;

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into InvoiceCollectPaymentResponse object. */
  public static InvoiceCollectPaymentResponse fromJson(String json) {
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

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceCollectPaymentResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceCollectPaymentResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceCollectPaymentResponse. */
  public static class Builder {

    private Invoice invoice;

    private Transaction transaction;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public InvoiceCollectPaymentResponse build() {
      return new InvoiceCollectPaymentResponse(this);
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
}
