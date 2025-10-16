package com.chargebee.v4.core.responses.invoice;

import java.util.List;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.core.models.creditNote.CreditNote;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceDeleteImported operation. Contains the response data from
 * the API.
 */
public final class InvoiceDeleteImportedResponse extends BaseResponse {
  private final Invoice invoice;

  private final List<CreditNote> creditNotes;

  private InvoiceDeleteImportedResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;

    this.creditNotes = builder.creditNotes;
  }

  /** Parse JSON response into InvoiceDeleteImportedResponse object. */
  public static InvoiceDeleteImportedResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceDeleteImportedResponse object with HTTP response. */
  public static InvoiceDeleteImportedResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      builder.creditNotes(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "credit_notes")).stream()
              .map(CreditNote::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceDeleteImportedResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceDeleteImportedResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceDeleteImportedResponse. */
  public static class Builder {

    private Invoice invoice;

    private List<CreditNote> creditNotes;

    private Response httpResponse;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public Builder creditNotes(List<CreditNote> creditNotes) {
      this.creditNotes = creditNotes;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public InvoiceDeleteImportedResponse build() {
      return new InvoiceDeleteImportedResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }

  /** Get the creditNotes from the response. */
  public List<CreditNote> getCreditNotes() {
    return creditNotes;
  }
}
