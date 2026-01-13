package com.chargebee.v4.models.invoice.responses;

import java.util.List;

import com.chargebee.v4.models.invoice.Invoice;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.InvoiceService;
import com.chargebee.v4.models.invoice.params.InvoicesForSubscriptionParams;

/**
 * Immutable response object for InvoicesForSubscription operation. Contains paginated list data.
 */
public final class InvoicesForSubscriptionResponse {

  private final List<InvoiceInvoicesForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final InvoiceService service;
  private final InvoicesForSubscriptionParams originalParams;
  private final Response httpResponse;

  private InvoicesForSubscriptionResponse(
      List<InvoiceInvoicesForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      InvoiceService service,
      InvoicesForSubscriptionParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into InvoicesForSubscriptionResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static InvoicesForSubscriptionResponse fromJson(String json) {
    try {

      List<InvoiceInvoicesForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceInvoicesForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoicesForSubscriptionResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoicesForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into InvoicesForSubscriptionResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static InvoicesForSubscriptionResponse fromJson(
      String json,
      InvoiceService service,
      InvoicesForSubscriptionParams originalParams,
      String subscriptionId,
      Response httpResponse) {
    try {

      List<InvoiceInvoicesForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceInvoicesForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoicesForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoicesForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<InvoiceInvoicesForSubscriptionItem> getList() {
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
   * @throws ChargebeeException if unable to fetch next page
   */
  public InvoicesForSubscriptionResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    InvoicesForSubscriptionParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : InvoicesForSubscriptionParams.builder())
            .offset(nextOffset)
            .build();

    return service.invoicesForSubscription(subscriptionId, nextParams);
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

  @Override
  public String toString() {
    return "InvoicesForSubscriptionResponse{" + "list=" + list + ", nextOffset=" + nextOffset + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InvoicesForSubscriptionResponse that = (InvoicesForSubscriptionResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(list, nextOffset);
  }

  public static class InvoiceInvoicesForSubscriptionItem {

    private Invoice invoice;

    public Invoice getInvoice() {
      return invoice;
    }

    public static InvoiceInvoicesForSubscriptionItem fromJson(String json) {
      InvoiceInvoicesForSubscriptionItem item = new InvoiceInvoicesForSubscriptionItem();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        item.invoice = Invoice.fromJson(__invoiceJson);
      }

      return item;
    }

    @Override
    public String toString() {
      return "InvoiceInvoicesForSubscriptionItem{" + "invoice=" + invoice + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      InvoiceInvoicesForSubscriptionItem that = (InvoiceInvoicesForSubscriptionItem) o;
      return java.util.Objects.equals(invoice, that.invoice);
    }

    @Override
    public int hashCode() {

      return java.util.Objects.hash(invoice);
    }
  }
}
