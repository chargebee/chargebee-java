package com.chargebee.v4.models.invoice.responses;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceRemovePaymentScheduleScheme operation. Contains the response
 * data from the API.
 */
public final class InvoiceRemovePaymentScheduleSchemeResponse extends BaseResponse {
  private final Invoice invoice;

  private InvoiceRemovePaymentScheduleSchemeResponse(Builder builder) {
    super(builder.httpResponse);

    this.invoice = builder.invoice;
  }

  /** Parse JSON response into InvoiceRemovePaymentScheduleSchemeResponse object. */
  public static InvoiceRemovePaymentScheduleSchemeResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into InvoiceRemovePaymentScheduleSchemeResponse object with HTTP response.
   */
  public static InvoiceRemovePaymentScheduleSchemeResponse fromJson(
      String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __invoiceObj = JsonUtil.getJsonObject(jsonObj, "invoice");
      if (__invoiceObj != null) {
        builder.invoice(Invoice.fromJson(__invoiceObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse InvoiceRemovePaymentScheduleSchemeResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceRemovePaymentScheduleSchemeResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceRemovePaymentScheduleSchemeResponse. */
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

    public InvoiceRemovePaymentScheduleSchemeResponse build() {
      return new InvoiceRemovePaymentScheduleSchemeResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }

  @Override
  public String toString() {
    return "InvoiceRemovePaymentScheduleSchemeResponse{" + "invoice=" + invoice + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InvoiceRemovePaymentScheduleSchemeResponse that =
        (InvoiceRemovePaymentScheduleSchemeResponse) o;
    return java.util.Objects.equals(invoice, that.invoice);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(invoice);
  }
}
