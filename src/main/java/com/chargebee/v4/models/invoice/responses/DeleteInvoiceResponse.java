package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for DeleteInvoice operation. Contains the response data from the API.
 */
public final class DeleteInvoiceResponse extends BaseResponse {
  private final Invoice invoice;

  private DeleteInvoiceResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into DeleteInvoiceResponse object. */
  public static DeleteInvoiceResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into DeleteInvoiceResponse object with HTTP response. */
  public static DeleteInvoiceResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse DeleteInvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for DeleteInvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for DeleteInvoiceResponse. */
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

    public DeleteInvoiceResponse build() {
      return new DeleteInvoiceResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
