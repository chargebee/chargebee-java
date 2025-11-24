package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class InvoiceRetrieveResponse extends BaseResponse {
  private final Invoice invoice;

  private InvoiceRetrieveResponse(Invoice invoice, Response httpResponse) {
    super(httpResponse);

    this.invoice = invoice;
  }

  /** Parse JSON response into InvoiceRetrieveResponse object. */
  public static InvoiceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceRetrieveResponse object with HTTP response. */
  public static InvoiceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Invoice invoice = Invoice.fromJson(JsonUtil.getObject(json, "invoice"));

      return new InvoiceRetrieveResponse(invoice, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceRetrieveResponse from JSON", e);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
