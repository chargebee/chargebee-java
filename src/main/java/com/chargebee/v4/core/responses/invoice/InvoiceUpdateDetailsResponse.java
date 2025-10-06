package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for InvoiceUpdateDetails operation. Contains the response data from the
 * API.
 */
public final class InvoiceUpdateDetailsResponse {

  private final Invoice invoice;

  private InvoiceUpdateDetailsResponse(Builder builder) {

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceUpdateDetailsResponse object. */
  public static InvoiceUpdateDetailsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceUpdateDetailsResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceUpdateDetailsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceUpdateDetailsResponse. */
  public static class Builder {

    private Invoice invoice;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public InvoiceUpdateDetailsResponse build() {
      return new InvoiceUpdateDetailsResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
