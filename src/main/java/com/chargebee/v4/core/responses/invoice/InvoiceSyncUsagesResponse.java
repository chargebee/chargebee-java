package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for InvoiceSyncUsages operation. Contains the response data from the
 * API.
 */
public final class InvoiceSyncUsagesResponse {

  private final Invoice invoice;

  private InvoiceSyncUsagesResponse(Builder builder) {

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceSyncUsagesResponse object. */
  public static InvoiceSyncUsagesResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceSyncUsagesResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceSyncUsagesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceSyncUsagesResponse. */
  public static class Builder {

    private Invoice invoice;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public InvoiceSyncUsagesResponse build() {
      return new InvoiceSyncUsagesResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
