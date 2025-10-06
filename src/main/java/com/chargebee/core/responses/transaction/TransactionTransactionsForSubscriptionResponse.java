package com.chargebee.core.responses.transaction;

import java.util.List;

import com.chargebee.core.models.transaction.Transaction;

import com.chargebee.internal.JsonUtil;
import com.chargebee.core.services.TransactionService;
import com.chargebee.core.models.transaction.params.TransactionTransactionsForSubscriptionParams;

/**
 * Immutable response object for TransactionTransactionsForSubscription operation. Contains
 * paginated list data.
 */
public final class TransactionTransactionsForSubscriptionResponse {

  private final List<TransactionTransactionsForSubscriptionItem> list;

  private final String nextOffset;

  private final String subscriptionId;

  private final TransactionService service;
  private final TransactionTransactionsForSubscriptionParams originalParams;

  private TransactionTransactionsForSubscriptionResponse(
      List<TransactionTransactionsForSubscriptionItem> list,
      String nextOffset,
      String subscriptionId,
      TransactionService service,
      TransactionTransactionsForSubscriptionParams originalParams) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.subscriptionId = subscriptionId;

    this.service = service;
    this.originalParams = originalParams;
  }

  /**
   * Parse JSON response into TransactionTransactionsForSubscriptionResponse object (no service
   * context). Use this when you only need to read a single page (no nextPage()).
   */
  public static TransactionTransactionsForSubscriptionResponse fromJson(String json) {
    try {

      List<TransactionTransactionsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionTransactionsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionTransactionsForSubscriptionResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TransactionTransactionsForSubscriptionResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into TransactionTransactionsForSubscriptionResponse object with service
   * context for pagination (enables nextPage()).
   */
  public static TransactionTransactionsForSubscriptionResponse fromJson(
      String json,
      TransactionService service,
      TransactionTransactionsForSubscriptionParams originalParams,
      String subscriptionId) {
    try {

      List<TransactionTransactionsForSubscriptionItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionTransactionsForSubscriptionItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionTransactionsForSubscriptionResponse(
          list, nextOffset, subscriptionId, service, originalParams);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TransactionTransactionsForSubscriptionResponse from JSON", e);
    }
  }

  /** Get the list from the response. */
  public List<TransactionTransactionsForSubscriptionItem> getList() {
    return list;
  }

  /** Get the nextOffset from the response. */
  public String getNextOffset() {
    return nextOffset;
  }

  /** Get the list of items in this page (alias). */
  public List<TransactionTransactionsForSubscriptionItem> items() {
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
  public TransactionTransactionsForSubscriptionResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null || originalParams == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams).");
    }

    // Create new params with the next offset
    TransactionTransactionsForSubscriptionParams nextParams =
        originalParams.toBuilder().offset(nextOffset).build();

    return service.transactionsForSubscription(subscriptionId, nextParams);
  }

  public static class TransactionTransactionsForSubscriptionItem {

    private Transaction transaction;

    public Transaction getTransaction() {
      return transaction;
    }

    public static TransactionTransactionsForSubscriptionItem fromJson(String json) {
      TransactionTransactionsForSubscriptionItem item =
          new TransactionTransactionsForSubscriptionItem();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        item.transaction = Transaction.fromJson(__transactionJson);
      }

      return item;
    }
  }
}
