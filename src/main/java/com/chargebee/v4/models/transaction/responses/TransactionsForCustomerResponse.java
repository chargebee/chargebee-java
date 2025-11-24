package com.chargebee.v4.models.transaction.responses;

import java.util.List;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.TransactionService;
import com.chargebee.v4.models.transaction.params.TransactionsForCustomerParams;

/**
 * Immutable response object for TransactionsForCustomer operation. Contains paginated list data.
 */
public final class TransactionsForCustomerResponse {

  private final List<TransactionTransactionsForCustomerItem> list;

  private final String nextOffset;

  private final String customerId;

  private final TransactionService service;
  private final TransactionsForCustomerParams originalParams;
  private final Response httpResponse;

  private TransactionsForCustomerResponse(
      List<TransactionTransactionsForCustomerItem> list,
      String nextOffset,
      String customerId,
      TransactionService service,
      TransactionsForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
  }

  /**
   * Parse JSON response into TransactionsForCustomerResponse object (no service context). Use this
   * when you only need to read a single page (no nextPage()).
   */
  public static TransactionsForCustomerResponse fromJson(String json) {
    try {

      List<TransactionTransactionsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionTransactionsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionsForCustomerResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionsForCustomerResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into TransactionsForCustomerResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static TransactionsForCustomerResponse fromJson(
      String json,
      TransactionService service,
      TransactionsForCustomerParams originalParams,
      String customerId,
      Response httpResponse) {
    try {

      List<TransactionTransactionsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionTransactionsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionsForCustomerResponse from JSON", e);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws Exception if unable to fetch next page
   */
  public TransactionsForCustomerResponse nextPage() throws Exception {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    TransactionsForCustomerParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : TransactionsForCustomerParams.builder())
            .offset(nextOffset)
            .build();

    return service.transactionsForCustomer(customerId, nextParams);
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
