package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for InvoiceStopDunning operation. Contains the response data from the
 * API.
 */
public final class InvoiceStopDunningResponse {

  private final Invoice invoice;

  private InvoiceStopDunningResponse(Builder builder) {

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceStopDunningResponse object. */
  public static InvoiceStopDunningResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceStopDunningResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceStopDunningResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceStopDunningResponse. */
  public static class Builder {

    private Invoice invoice;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public InvoiceStopDunningResponse build() {
      return new InvoiceStopDunningResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
