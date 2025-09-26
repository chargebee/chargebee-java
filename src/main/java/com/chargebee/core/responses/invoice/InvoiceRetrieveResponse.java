package com.chargebee.core.responses.invoice;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for InvoiceRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class InvoiceRetrieveResponse {

  private final Invoice invoice;

  private InvoiceRetrieveResponse(Invoice invoice) {

    this.invoice = invoice;
  }

  /** Parse JSON response into InvoiceRetrieveResponse object. */
  public static InvoiceRetrieveResponse fromJson(String json) {
    try {

      Invoice invoice = Invoice.fromJson(JsonUtil.getObject(json, "invoice"));

      return new InvoiceRetrieveResponse(invoice);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceRetrieveResponse from JSON", e);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
