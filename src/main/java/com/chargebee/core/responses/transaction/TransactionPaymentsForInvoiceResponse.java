package com.chargebee.core.responses.transaction;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.transaction.Transaction;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.TransactionService;
import com.chargebee.core.models.transaction.params.TransactionPaymentsForInvoiceParams;

/**
 * Immutable response object for TransactionPaymentsForInvoice operation. Contains paginated list
 * data with auto-pagination support.
 */
public final class TransactionPaymentsForInvoiceResponse
    implements Iterable<TransactionPaymentsForInvoiceResponse.TransactionPaymentsForInvoiceItem> {

  private final List<TransactionPaymentsForInvoiceItem> list;

  private final String nextOffset;

  private final String invoiceId;

  private final TransactionService service;
  private final TransactionPaymentsForInvoiceParams originalParams;
  private final boolean isAutoPaginate;

  private TransactionPaymentsForInvoiceResponse(
      List<TransactionPaymentsForInvoiceItem> list,
      String nextOffset,
      String invoiceId,
      TransactionService service,
      TransactionPaymentsForInvoiceParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.invoiceId = invoiceId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private TransactionPaymentsForInvoiceResponse(
      List<TransactionPaymentsForInvoiceItem> list,
      String nextOffset,
      String invoiceId,
      TransactionService service,
      TransactionPaymentsForInvoiceParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.invoiceId = invoiceId;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into TransactionPaymentsForInvoiceResponse object (no service context). Use
   * this when you only need to read a single page (no nextPage()).
   */
  public static TransactionPaymentsForInvoiceResponse fromJson(String json) {
    try {

      List<TransactionPaymentsForInvoiceItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionPaymentsForInvoiceItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionPaymentsForInvoiceResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TransactionPaymentsForInvoiceResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into TransactionPaymentsForInvoiceResponse object with service context for
   * pagination (enables nextPage(), autoPaginate()).
   */
  public static TransactionPaymentsForInvoiceResponse fromJson(
      String json,
      TransactionService service,
      TransactionPaymentsForInvoiceParams originalParams,
      String invoiceId) {
    try {

      List<TransactionPaymentsForInvoiceItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionPaymentsForInvoiceItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionPaymentsForInvoiceResponse(
          list, nextOffset, invoiceId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TransactionPaymentsForInvoiceResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<TransactionPaymentsForInvoiceItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<TransactionPaymentsForInvoiceItem> items() {
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
  public TransactionPaymentsForInvoiceResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    TransactionPaymentsForInvoiceParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.paymentsForInvoice(invoiceId, nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public TransactionPaymentsForInvoiceResponse autoPaginate() {
    return new TransactionPaymentsForInvoiceResponse(
        list, nextOffset, invoiceId, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<TransactionPaymentsForInvoiceItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<TransactionPaymentsForInvoiceItem> {
    private TransactionPaymentsForInvoiceResponse currentPage =
        TransactionPaymentsForInvoiceResponse.this;
    private Iterator<TransactionPaymentsForInvoiceItem> currentIterator =
        currentPage.list.iterator();

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
    public TransactionPaymentsForInvoiceItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class TransactionPaymentsForInvoiceItem {

    private Transaction transaction;

    public Transaction getTransaction() {
      return transaction;
    }

    public static TransactionPaymentsForInvoiceItem fromJson(String json) {
      TransactionPaymentsForInvoiceItem item = new TransactionPaymentsForInvoiceItem();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        item.transaction = Transaction.fromJson(__transactionJson);
      }

      return item;
    }
  }
}
