package com.chargebee.v4.core.responses.invoice;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for InvoiceRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class InvoiceRetrieveResponse {

  private final Invoice invoice;

  private final Response httpResponse;

  private InvoiceRetrieveResponse(Invoice invoice, Response httpResponse) {

    this.invoice = invoice;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into InvoiceRetrieveResponse object. */
  public static InvoiceRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into InvoiceRetrieveResponse object with HTTP response. */
  public static InvoiceRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Invoice invoice = Invoice.fromJson(JsonUtil.getObject(json, "invoice"));

      return new InvoiceRetrieveResponse(invoice, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceRetrieveResponse from JSON", e);
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
