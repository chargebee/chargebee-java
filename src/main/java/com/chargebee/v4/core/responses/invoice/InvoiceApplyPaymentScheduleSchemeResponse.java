package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for InvoiceApplyPaymentScheduleScheme operation. Contains the response
 * data from the API.
 */
public final class InvoiceApplyPaymentScheduleSchemeResponse {

  private final Invoice invoice;

  private InvoiceApplyPaymentScheduleSchemeResponse(Builder builder) {

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceApplyPaymentScheduleSchemeResponse object. */
  public static InvoiceApplyPaymentScheduleSchemeResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse InvoiceApplyPaymentScheduleSchemeResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceApplyPaymentScheduleSchemeResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceApplyPaymentScheduleSchemeResponse. */
  public static class Builder {

    private Invoice invoice;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public InvoiceApplyPaymentScheduleSchemeResponse build() {
      return new InvoiceApplyPaymentScheduleSchemeResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }
}
