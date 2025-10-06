package com.chargebee.core.responses.invoice;

import java.util.List;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.InvoiceService;
import com.chargebee.core.models.invoice.params.InvoiceInvoicesForSubscriptionParams;

/**
 * Immutable response object for InvoiceInvoicesForSubscription operation. Contains paginated list
 * data.
 */
public final class InvoiceInvoicesForSubscriptionResponse {

  private final List<InvoiceInvoicesForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final InvoiceService service;
  private final InvoiceInvoicesForSubscriptionParams originalParams;

  private InvoiceInvoicesForSubscriptionResponse(
      List<InvoiceInvoicesForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      InvoiceService service,
      InvoiceInvoicesForSubscriptionParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into InvoiceInvoicesForSubscriptionResponse object (no service context).
   * Use this when you only need to read a single page (no nextPage()).
   */
  public static InvoiceInvoicesForSubscriptionResponse fromJson(String json) {
    try {

      List<InvoiceInvoicesForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceInvoicesForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoiceInvoicesForSubscriptionResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse InvoiceInvoicesForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into InvoiceInvoicesForSubscriptionResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static InvoiceInvoicesForSubscriptionResponse fromJson(
      String json,
      InvoiceService service,
      InvoiceInvoicesForSubscriptionParams originalParams,
      String subscriptionId) {
    try {

      List<InvoiceInvoicesForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceInvoicesForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoiceInvoicesForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse InvoiceInvoicesForSubscriptionResponse from JSON", e);
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

  /** Get the list of items in this page (alias). */
  public List<InvoiceInvoicesForSubscriptionItem> items() {
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
  public InvoiceInvoicesForSubscriptionResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    InvoiceInvoicesForSubscriptionParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.invoicesForSubscription(subscriptionId, nextParams);
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
  }
}
