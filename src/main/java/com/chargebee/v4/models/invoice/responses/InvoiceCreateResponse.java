package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceCreate operation. Contains the response data from the API.
 */
public final class InvoiceCreateResponse extends BaseResponse {
  private final Invoice invoice;

  private InvoiceCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceCreateResponse object. */
  public static InvoiceCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceCreateResponse object with HTTP response. */
  public static InvoiceCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceCreateResponse. */
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

    public InvoiceCreateResponse build() {
      return new InvoiceCreateResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
