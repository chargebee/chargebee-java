package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for RecordPaymentForInvoice operation. Contains the response data from
 * the API.
 */
public final class RecordPaymentForInvoiceResponse extends BaseResponse {
  private final Invoice invoice;

  private final Transaction transaction;

  private RecordPaymentForInvoiceResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into RecordPaymentForInvoiceResponse object. */
  public static RecordPaymentForInvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RecordPaymentForInvoiceResponse object with HTTP response. */
  public static RecordPaymentForInvoiceResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse RecordPaymentForInvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for RecordPaymentForInvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RecordPaymentForInvoiceResponse. */
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

    public RecordPaymentForInvoiceResponse build() {
      return new RecordPaymentForInvoiceResponse(this);
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
