package com.chargebee.v4.core.responses.transaction;

import java.util.List;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.TransactionService;
import com.chargebee.v4.core.models.transaction.params.TransactionTransactionsForCustomerParams;

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
  private final Response httpResponse;

  private TransactionTransactionsForCustomerResponse(
      List<TransactionTransactionsForCustomerItem> list,
      String nextOffset,
      String customerId,
      TransactionService service,
      TransactionTransactionsForCustomerParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.customerId = customerId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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

      return new TransactionTransactionsForCustomerResponse(
          list, nextOffset, null, null, null, null);
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
      String customerId,
      Response httpResponse) {
    try {

      List<TransactionTransactionsForCustomerItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionTransactionsForCustomerItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionTransactionsForCustomerResponse(
          list, nextOffset, customerId, service, originalParams, httpResponse);
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
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    TransactionTransactionsForCustomerParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : TransactionTransactionsForCustomerParams.builder())
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
