package com.chargebee.v4.models.creditNote.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreditNoteCreate operation. Contains the response data from the
 * API.
 */
public final class CreditNoteCreateResponse extends BaseResponse {
  private final CreditNote creditNote;

  private final Invoice invoice;

  private CreditNoteCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.creditNote = builder.creditNote;

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into CreditNoteCreateResponse object. */
  public static CreditNoteCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteCreateResponse object with HTTP response. */
  public static CreditNoteCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteCreateResponse. */
  public static class Builder {

    private CreditNote creditNote;

    private Invoice invoice;

    private Response httpResponse;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CreditNoteCreateResponse build() {
      return new CreditNoteCreateResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }

  @Override
  public String toString() {
    return "CreditNoteCreateResponse{" + "creditNote=" + creditNote + ", invoice=" + invoice + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CreditNoteCreateResponse that = (CreditNoteCreateResponse) o;
    return java.util.Objects.equals(creditNote, that.creditNote)
        && java.util.Objects.equals(invoice, that.invoice);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(creditNote, invoice);
  }
}
