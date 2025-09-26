package com.chargebee.core.responses.transaction;

import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.chargebee.core.models.transaction.Transaction;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.TransactionService;
import com.chargebee.core.models.transaction.params.TransactionListParams;

/**
 * Immutable response object for TransactionList operation. Contains paginated list data with
 * auto-pagination support.
 */
public final class TransactionListResponse
    implements Iterable<TransactionListResponse.TransactionListItem> {

  private final List<TransactionListItem> list;

  private final String nextOffset;

  private final TransactionService service;
  private final TransactionListParams originalParams;
  private final boolean isAutoPaginate;

  private TransactionListResponse(
      List<TransactionListItem> list,
      String nextOffset,
      TransactionService service,
      TransactionListParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = false;
  }

  private TransactionListResponse(
      List<TransactionListItem> list,
      String nextOffset,
      TransactionService service,
      TransactionListParams originalParams,
      boolean isAutoPaginate) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.isAutoPaginate = isAutoPaginate;
  }

  /**
   * Parse JSON response into TransactionListResponse object (no service context). Use this when you
   * only need to read a single page (no nextPage()).
   */
  public static TransactionListResponse fromJson(String json) {
    try {

      List<TransactionListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionListResponse(list, nextOffset, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into TransactionListResponse object with service context for pagination
   * (enables nextPage(), autoPaginate()).
   */
  public static TransactionListResponse fromJson(
      String json, TransactionService service, TransactionListParams originalParams) {
    try {

      List<TransactionListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionListResponse(list, nextOffset, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionListResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<TransactionListItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<TransactionListItem> items() {
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
  public TransactionListResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    TransactionListParams nextParams = originalParams.toBuilder().offset(nextOffset).build();

    return service.list(nextParams);
  }

  /**
   * Enable auto-pagination for this response. Returns a new response that will automatically
   * iterate through all pages.
   */
  public TransactionListResponse autoPaginate() {
    return new TransactionListResponse(list, nextOffset, service, originalParams, true);
  }

  /** Iterator implementation for auto-pagination support. */
  @Override
  public Iterator<TransactionListItem> iterator() {
    if (isAutoPaginate) {
      return new AutoPaginateIterator();
    } else {
      return list.iterator();
    }
  }

  /** Internal iterator class for auto-pagination. */
  private class AutoPaginateIterator implements Iterator<TransactionListItem> {
    private TransactionListResponse currentPage = TransactionListResponse.this;
    private Iterator<TransactionListItem> currentIterator = currentPage.list.iterator();

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
    public TransactionListItem next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return currentIterator.next();
    }
  }

  public static class TransactionListItem {

    private Transaction transaction;

    public Transaction getTransaction() {
      return transaction;
    }

    public static TransactionListItem fromJson(String json) {
      TransactionListItem item = new TransactionListItem();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        item.transaction = Transaction.fromJson(__transactionJson);
      }

      return item;
    }
  }
}
