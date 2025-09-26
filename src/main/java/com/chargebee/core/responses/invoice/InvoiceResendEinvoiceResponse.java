package com.chargebee.core.responses.invoice;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for InvoiceResendEinvoice operation. Contains the response data from
 * the API.
 */
public final class InvoiceResendEinvoiceResponse {

  private final Invoice invoice;

  private InvoiceResendEinvoiceResponse(Builder builder) {

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceResendEinvoiceResponse object. */
  public static InvoiceResendEinvoiceResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceResendEinvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceResendEinvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceResendEinvoiceResponse. */
  public static class Builder {

    private Invoice invoice;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public InvoiceResendEinvoiceResponse build() {
      return new InvoiceResendEinvoiceResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
