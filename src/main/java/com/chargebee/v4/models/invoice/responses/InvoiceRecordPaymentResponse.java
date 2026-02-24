package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceRecordPayment operation. Contains the response data from the
 * API.
 */
public final class InvoiceRecordPaymentResponse extends BaseResponse {
  private final Invoice invoice;

  private final Transaction transaction;

  private InvoiceRecordPaymentResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into InvoiceRecordPaymentResponse object. */
  public static InvoiceRecordPaymentResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceRecordPaymentResponse object with HTTP response. */
  public static InvoiceRecordPaymentResponse fromJson(String json, Response httpResponse) {
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

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceRecordPaymentResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceRecordPaymentResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceRecordPaymentResponse. */
  public static class Builder {

    private Invoice invoice;

    private Transaction transaction;

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

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public InvoiceRecordPaymentResponse build() {
      return new InvoiceRecordPaymentResponse(this);
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

  @Override
  public String toString() {
    return "InvoiceRecordPaymentResponse{"
        + "invoice="
        + invoice
        + ", transaction="
        + transaction
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InvoiceRecordPaymentResponse that = (InvoiceRecordPaymentResponse) o;
    return java.util.Objects.equals(invoice, that.invoice)
        && java.util.Objects.equals(transaction, that.transaction);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(invoice, transaction);
  }
}
