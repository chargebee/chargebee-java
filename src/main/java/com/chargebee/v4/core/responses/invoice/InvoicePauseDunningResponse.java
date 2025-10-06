package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for InvoicePauseDunning operation. Contains the response data from the
 * API.
 */
public final class InvoicePauseDunningResponse {

  private final Invoice invoice;

  private InvoicePauseDunningResponse(Builder builder) {

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoicePauseDunningResponse object. */
  public static InvoicePauseDunningResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoicePauseDunningResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoicePauseDunningResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoicePauseDunningResponse. */
  public static class Builder {

    private Invoice invoice;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public InvoicePauseDunningResponse build() {
      return new InvoicePauseDunningResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
