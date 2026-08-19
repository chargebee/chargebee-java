package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceVoidBeforeCapture operation. Contains the response data from
 * the API.
 */
public final class InvoiceVoidBeforeCaptureResponse extends BaseResponse {
  private final Invoice invoice;

  private final CreditNote creditNote;

  private InvoiceVoidBeforeCaptureResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into InvoiceVoidBeforeCaptureResponse object. */
  public static InvoiceVoidBeforeCaptureResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceVoidBeforeCaptureResponse object with HTTP response. */
  public static InvoiceVoidBeforeCaptureResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __invoiceObj = JsonUtil.getJsonObject(jsonObj, "invoice");
      if (__invoiceObj != null) {
        builder.invoice(Invoice.fromJson(__invoiceObj));
      }

      JsonObject __creditNoteObj = JsonUtil.getJsonObject(jsonObj, "credit_note");
      if (__creditNoteObj != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceVoidBeforeCaptureResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceVoidBeforeCaptureResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceVoidBeforeCaptureResponse. */
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

    public InvoiceVoidBeforeCaptureResponse build() {
      return new InvoiceVoidBeforeCaptureResponse(this);
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

  @Override
  public String toString() {
    return "InvoiceVoidBeforeCaptureResponse{"
        + "invoice="
        + invoice
        + ", creditNote="
        + creditNote
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InvoiceVoidBeforeCaptureResponse that = (InvoiceVoidBeforeCaptureResponse) o;
    return java.util.Objects.equals(invoice, that.invoice)
        && java.util.Objects.equals(creditNote, that.creditNote);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(invoice, creditNote);
  }
}
