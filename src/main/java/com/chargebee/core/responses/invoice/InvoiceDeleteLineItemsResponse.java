package com.chargebee.core.responses.invoice;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for InvoiceDeleteLineItems operation. Contains the response data from
 * the API.
 */
public final class InvoiceDeleteLineItemsResponse {

  private final Invoice invoice;

  private InvoiceDeleteLineItemsResponse(Builder builder) {

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceDeleteLineItemsResponse object. */
  public static InvoiceDeleteLineItemsResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceDeleteLineItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceDeleteLineItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceDeleteLineItemsResponse. */
  public static class Builder {

    private Invoice invoice;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public InvoiceDeleteLineItemsResponse build() {
      return new InvoiceDeleteLineItemsResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
