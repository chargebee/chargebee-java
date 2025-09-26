package com.chargebee.core.responses.invoice;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for InvoiceClose operation. Contains the response data from the API.
 */
public final class InvoiceCloseResponse {

  private final Invoice invoice;

  private InvoiceCloseResponse(Builder builder) {

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceCloseResponse object. */
  public static InvoiceCloseResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceCloseResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceCloseResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceCloseResponse. */
  public static class Builder {

    private Invoice invoice;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public InvoiceCloseResponse build() {
      return new InvoiceCloseResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
