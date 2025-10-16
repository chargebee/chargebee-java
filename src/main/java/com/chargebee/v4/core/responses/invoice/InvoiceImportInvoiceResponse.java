package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.core.models.creditNote.CreditNote;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceImportInvoice operation. Contains the response data from the
 * API.
 */
public final class InvoiceImportInvoiceResponse extends BaseResponse {
  private final Invoice invoice;

  private final CreditNote creditNote;

  private InvoiceImportInvoiceResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into InvoiceImportInvoiceResponse object. */
  public static InvoiceImportInvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceImportInvoiceResponse object with HTTP response. */
  public static InvoiceImportInvoiceResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceImportInvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceImportInvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceImportInvoiceResponse. */
  public static class Builder {

    private Invoice invoice;

    private CreditNote creditNote;

    private Response httpResponse;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public InvoiceImportInvoiceResponse build() {
      return new InvoiceImportInvoiceResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }
}
