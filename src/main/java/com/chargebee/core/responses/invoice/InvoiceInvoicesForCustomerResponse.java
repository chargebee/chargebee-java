package com.chargebee.core.responses.invoice;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.invoice.Invoice;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.InvoiceService;
import com.chargebee.core.models.invoice.params.InvoiceInvoicesForCustomerParams;

/**
 * Immutable response object for InvoiceInvoicesForCustomer operation. Contains paginated list data
 * with auto-pagination support.
 */
public final class InvoiceInvoicesForCustomerResponse
    implements Iterable<InvoiceInvoicesForCustomerResponse.InvoiceInvoicesForCustomerItem> {

  private final List<InvoiceInvoicesForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final InvoiceService service;
  private final InvoiceInvoicesForCustomerParams originalParams;
  private final boolean isAutoPaginate;

  private InvoiceInvoicesForCustomerResponse(
      List<InvoiceInvoicesForCustomerItem> list,
      String nextOffset,
      String customerId,
      InvoiceService service,
      InvoiceInvoicesForCustomerParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private InvoiceInvoicesForCustomerResponse(
      List<InvoiceInvoicesForCustomerItem> list,
      String nextOffset,
      String customerId,
      InvoiceService service,
      InvoiceInvoicesForCustomerParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into InvoiceInvoicesForCustomerResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static InvoiceInvoicesForCustomerResponse fromJson(String json) {
    try {

      List<InvoiceInvoicesForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceInvoicesForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoiceInvoicesForCustomerResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceInvoicesForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into InvoiceInvoicesForCustomerResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static InvoiceInvoicesForCustomerResponse fromJson(
      String json,
      InvoiceService service,
      InvoiceInvoicesForCustomerParams originalParams,
      String customerId) {
    try {

      List<InvoiceInvoicesForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(InvoiceInvoicesForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new InvoiceInvoicesForCustomerResponse(
          list, nextOffset, customerId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse InvoiceInvoicesForCustomerResponse from JSON", e);
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

  /** Get the list of items in this page (alias). */
  public List<InvoiceInvoicesForCustomerItem> items() {
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
  public InvoiceInvoicesForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    InvoiceInvoicesForCustomerParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.invoicesForCustomer(customerId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public InvoiceInvoicesForCustomerResponse autoPaginate() {
    return new InvoiceInvoicesForCustomerResponse(
        list, nextOffset, customerId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<InvoiceInvoicesForCustomerItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<InvoiceInvoicesForCustomerItem> {
    private InvoiceInvoicesForCustomerResponse currentPage =
        InvoiceInvoicesForCustomerResponse.this;
    private Iterator<InvoiceInvoicesForCustomerItem> currentIterator = currentPage.list.iterator();

    @Override
    public boolean hasNext() {
      if (currentIterator.hasNext()) {
        return true;
      }

      // Try to load next page if available
      if (currentPage.hasNextPage()) {
        try {
          currentPage = currentPage.nextPage();
          currentIterator = currentPage.list.iterator();
          return currentIterator.hasNext();
        } catch (Exception e) {
          throw new RuntimeException("Failed to fetch next page", e);
        }
      }

      return false;
    }

    @Override
    public InvoiceInvoicesForCustomerItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
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
