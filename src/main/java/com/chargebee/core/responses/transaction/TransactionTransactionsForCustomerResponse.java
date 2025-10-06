package com.chargebee.core.responses.transaction;

import java.util.List;

import com.chargebee.core.models.transaction.Transaction;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.TransactionService;
import com.chargebee.core.models.transaction.params.TransactionTransactionsForCustomerParams;

/**
 * Immutable response object for TransactionTransactionsForCustomer operation. Contains paginated
 * list data.
 */
public final class TransactionTransactionsForCustomerResponse {

  private final List<TransactionTransactionsForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final TransactionService service;
  private final TransactionTransactionsForCustomerParams originalParams;

  private TransactionTransactionsForCustomerResponse(
      List<TransactionTransactionsForCustomerItem> list,
      String nextOffset,
      String customerId,
      TransactionService service,
      TransactionTransactionsForCustomerParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into TransactionTransactionsForCustomerResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static TransactionTransactionsForCustomerResponse fromJson(String json) {
    try {

      List<TransactionTransactionsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionTransactionsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionTransactionsForCustomerResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TransactionTransactionsForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into TransactionTransactionsForCustomerResponse object with service context
   * for pagination (enables nextPage()).
   */
  public static TransactionTransactionsForCustomerResponse fromJson(
      String json,
      TransactionService service,
      TransactionTransactionsForCustomerParams originalParams,
      String customerId) {
    try {

      List<TransactionTransactionsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionTransactionsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionTransactionsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TransactionTransactionsForCustomerResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<TransactionTransactionsForCustomerItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<TransactionTransactionsForCustomerItem> items() {
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
  public TransactionTransactionsForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    TransactionTransactionsForCustomerParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.transactionsForCustomer(customerId, nextParams);
  }

  public static class TransactionTransactionsForCustomerItem {

    private Transaction transaction;

    public Transaction getTransaction() {
      return transaction;
    }

    public static TransactionTransactionsForCustomerItem fromJson(String json) {
      TransactionTransactionsForCustomerItem item = new TransactionTransactionsForCustomerItem();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        item.transaction = Transaction.fromJson(__transactionJson);
      }

      return item;
    }
  }
}
