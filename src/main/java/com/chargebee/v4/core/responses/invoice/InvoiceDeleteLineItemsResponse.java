package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceDeleteLineItems operation. Contains the response data from
 * the API.
 */
public final class InvoiceDeleteLineItemsResponse extends BaseResponse {
  private final Invoice invoice;

  private InvoiceDeleteLineItemsResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceDeleteLineItemsResponse object. */
  public static InvoiceDeleteLineItemsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceDeleteLineItemsResponse object with HTTP response. */
  public static InvoiceDeleteLineItemsResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceDeleteLineItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceDeleteLineItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceDeleteLineItemsResponse. */
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

    public InvoiceDeleteLineItemsResponse build() {
      return new InvoiceDeleteLineItemsResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
