package com.chargebee.v4.core.responses.invoice;

import java.util.List;

import com.chargebee.v4.core.models.invoice.Invoice;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.core.services.InvoiceService;
import com.chargebee.v4.core.models.invoice.params.InvoiceListParams;

/** Immutable response object for InvoiceList operation. Contains paginated list data. */
public final class InvoiceListResponse {

  private final List<InvoiceListItem> list;

  private final String nextOffset;

  private final InvoiceService service;
  private final InvoiceListParams originalParams;

  private InvoiceListResponse(
      List<InvoiceListItem> list,
      String nextOffset,
      InvoiceService service,
      InvoiceListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into InvoiceListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static InvoiceListResponse fromJson(String json) {
    try {

      List<InvoiceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoiceListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into InvoiceListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static InvoiceListResponse fromJson(
      String json, InvoiceService service, InvoiceListParams originalParams) {
    try {

      List<InvoiceListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoiceListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<InvoiceListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<InvoiceListItem> items() {
    return list;
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
  public InvoiceListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    InvoiceListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  public static class InvoiceListItem {

    private Invoice invoice;

    public Invoice getInvoice() {
      return invoice;
    }

    public static InvoiceListItem fromJson(String json) {
      InvoiceListItem item = new InvoiceListItem();

      String __invoiceJson = JsonUtil.getObject(json, "invoice");
      if (__invoiceJson != null) {
        item.invoice = Invoice.fromJson(__invoiceJson);
      }

      return item;
    }
  }
}
