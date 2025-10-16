package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceSendEinvoice operation. Contains the response data from the
 * API.
 */
public final class InvoiceSendEinvoiceResponse extends BaseResponse {
  private final Invoice invoice;

  private InvoiceSendEinvoiceResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceSendEinvoiceResponse object. */
  public static InvoiceSendEinvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceSendEinvoiceResponse object with HTTP response. */
  public static InvoiceSendEinvoiceResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceSendEinvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceSendEinvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceSendEinvoiceResponse. */
  public static class Builder {

    private Invoice invoice;

    private Response httpResponse;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public InvoiceSendEinvoiceResponse build() {
      return new InvoiceSendEinvoiceResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
