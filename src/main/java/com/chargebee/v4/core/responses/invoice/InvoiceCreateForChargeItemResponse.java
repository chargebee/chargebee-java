package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceCreateForChargeItem operation. Contains the response data
 * from the API.
 */
public final class InvoiceCreateForChargeItemResponse {

  private final Invoice invoice;

  private final Response httpResponse;

  private InvoiceCreateForChargeItemResponse(Builder builder) {

    this.invoice = builder.invoice;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into InvoiceCreateForChargeItemResponse object. */
  public static InvoiceCreateForChargeItemResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceCreateForChargeItemResponse object with HTTP response. */
  public static InvoiceCreateForChargeItemResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        builder.invoice(Invoice.fromJson(__invoiceJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceCreateForChargeItemResponse from JSON", e);
    }
  }

  /** Create a new builder for InvoiceCreateForChargeItemResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for InvoiceCreateForChargeItemResponse. */
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

    public InvoiceCreateForChargeItemResponse build() {
      return new InvoiceCreateForChargeItemResponse(this);
    }
  }

  /** Get the invoice from the response. */
  public Invoice getInvoice() {
    return invoice;
  }

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
