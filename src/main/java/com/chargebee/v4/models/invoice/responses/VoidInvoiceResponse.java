package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for VoidInvoice operation. Contains the response data from the API. */
public final class VoidInvoiceResponse extends BaseResponse {
  private final Invoice invoice;

  private final CreditNote creditNote;

  private VoidInvoiceResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;

    this.creditNote = builder.creditNote;
  }

  /** Parse JSON response into VoidInvoiceResponse object. */
  public static VoidInvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into VoidInvoiceResponse object with HTTP response. */
  public static VoidInvoiceResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse VoidInvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for VoidInvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VoidInvoiceResponse. */
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

    public VoidInvoiceResponse build() {
      return new VoidInvoiceResponse(this);
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
    return "VoidInvoiceResponse{" + "invoice=" + invoice + ", creditNote=" + creditNote + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    VoidInvoiceResponse that = (VoidInvoiceResponse) o;
    return java.util.Objects.equals(invoice, that.invoice)
        && java.util.Objects.equals(creditNote, that.creditNote);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(invoice, creditNote);
  }
}
