package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceResumeDunning operation. Contains the response data from the
 * API.
 */
public final class InvoiceResumeDunningResponse extends BaseResponse {
  private final Invoice invoice;

  private InvoiceResumeDunningResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceResumeDunningResponse object. */
  public static InvoiceResumeDunningResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceResumeDunningResponse object with HTTP response. */
  public static InvoiceResumeDunningResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceResumeDunningResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceResumeDunningResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceResumeDunningResponse. */
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

    public InvoiceResumeDunningResponse build() {
      return new InvoiceResumeDunningResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
