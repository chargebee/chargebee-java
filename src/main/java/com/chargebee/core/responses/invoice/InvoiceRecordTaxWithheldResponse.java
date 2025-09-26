package com.chargebee.core.responses.invoice;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for InvoiceRecordTaxWithheld operation. Contains the response data from
 * the API.
 */
public final class InvoiceRecordTaxWithheldResponse {

  private final Invoice invoice;

  private InvoiceRecordTaxWithheldResponse(Builder builder) {

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceRecordTaxWithheldResponse object. */
  public static InvoiceRecordTaxWithheldResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceRecordTaxWithheldResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceRecordTaxWithheldResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceRecordTaxWithheldResponse. */
  public static class Builder {

    private Invoice invoice;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public InvoiceRecordTaxWithheldResponse build() {
      return new InvoiceRecordTaxWithheldResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
