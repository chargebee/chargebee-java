package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
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
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
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
}
