package com.chargebee.v4.core.responses.subscription;

import java.util.List;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.core.models.unbilledCharge.UnbilledCharge;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for SubscriptionRegenerateInvoice operation. Contains the response data
 * from the API.
 */
public final class SubscriptionRegenerateInvoiceResponse {

  private final Invoice invoice;

  private final List<UnbilledCharge> unbilledCharges;

  private SubscriptionRegenerateInvoiceResponse(Builder builder) {

    this.invoice = builder.invoice;

    this.unbilledCharges = builder.unbilledCharges;
  }

  /** Parse JSON response into SubscriptionRegenerateInvoiceResponse object. */
  public static SubscriptionRegenerateInvoiceResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      builder.unbilledCharges(
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "unbilled_charges")).stream()
              .map(UnbilledCharge::fromJson)
              .collect(java.util.stream.Collectors.toList()));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse SubscriptionRegenerateInvoiceResponse from JSON", e);
    }
  }

  /** Create a new builder for SubscriptionRegenerateInvoiceResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for SubscriptionRegenerateInvoiceResponse. */
  public static class Builder {

    private Invoice invoice;

    private List<UnbilledCharge> unbilledCharges;

    private Builder() {}

    public Builder invoice(Invoice invoice) {
      this.invoice = invoice;
      return this;
    }

    public Builder unbilledCharges(List<UnbilledCharge> unbilledCharges) {
      this.unbilledCharges = unbilledCharges;
      return this;
    }

    public SubscriptionRegenerateInvoiceResponse build() {
      return new SubscriptionRegenerateInvoiceResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }

  /** Get the unbilledCharges from the response. */
  public List<UnbilledCharge> getUnbilledCharges() {
    return unbilledCharges;
  }
}
