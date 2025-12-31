package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceClose operation. Contains the response data from the API.
 */
public final class InvoiceCloseResponse extends BaseResponse {
  private final Invoice invoice;

  private InvoiceCloseResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceCloseResponse object. */
  public static InvoiceCloseResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceCloseResponse object with HTTP response. */
  public static InvoiceCloseResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceCloseResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceCloseResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceCloseResponse. */
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

    public InvoiceCloseResponse build() {
      return new InvoiceCloseResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }

  @Override
  public String toString() {
    return "InvoiceCloseResponse{" + "invoice=" + invoice + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InvoiceCloseResponse that = (InvoiceCloseResponse) o;
    return java.util.Objects.equals(invoice, that.invoice);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(invoice);
  }
}
