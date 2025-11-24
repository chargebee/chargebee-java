package com.chargebee.v4.models.invoice.responses;

import java.util.List;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.InvoiceService;
import com.chargebee.v4.models.invoice.params.InvoicesForCustomerParams;

/** Immutable response object for InvoicesForCustomer operation. Contains paginated list data. */
public final class InvoicesForCustomerResponse {

  private final List<InvoiceInvoicesForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final InvoiceService service;
  private final InvoicesForCustomerParams originalParams;
  private final Response httpResponse;

  private InvoicesForCustomerResponse(
      List<InvoiceInvoicesForCustomerItem> list,
      String nextOffset,
      String customerId,
      InvoiceService service,
      InvoicesForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into InvoicesForCustomerResponse object (no service context). Use this when
   * you only need to read a single page (no nextPage()).
   */
  public static InvoicesForCustomerResponse fromJson(String json) {
    try {

      List<InvoiceInvoicesForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceInvoicesForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoicesForCustomerResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoicesForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into InvoicesForCustomerResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static InvoicesForCustomerResponse fromJson(
      String json,
      InvoiceService service,
      InvoicesForCustomerParams originalParams,
      String customerId,
      Response httpResponse) {
    try {

      List<InvoiceInvoicesForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceInvoicesForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoicesForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoicesForCustomerResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<InvoiceInvoicesForCustomerItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws Exception if unable to fetch next page
   */
  public InvoicesForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    InvoicesForCustomerParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : InvoicesForCustomerParams.builder())
            .offset(nextOffset)
            .build();

    return service.invoicesForCustomer(customerId, nextParams);
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

  public static class InvoiceInvoicesForCustomerItem {

    private Invoice invoice;

    public Invoice getInvoice() {
      return invoice;
    }

    public static InvoiceInvoicesForCustomerItem fromJson(String json) {
      InvoiceInvoicesForCustomerItem item = new InvoiceInvoicesForCustomerItem();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        item.invoice = Invoice.fromJson(__invoiceJson);
      }

      return item;
    }
  }
}
