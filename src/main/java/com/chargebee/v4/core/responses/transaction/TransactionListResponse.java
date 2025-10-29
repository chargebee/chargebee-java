package com.chargebee.v4.core.responses.transaction;

import java.util.List;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.core.services.TransactionService;
import com.chargebee.v4.core.models.transaction.params.TransactionListParams;

/** Immutable response object for TransactionList operation. Contains paginated list data. */
public final class TransactionListResponse {

  private final List<TransactionListItem> list;

  private final String nextOffset;

  private final TransactionService service;
  private final TransactionListParams originalParams;
  private final Response httpResponse;

  private TransactionListResponse(
      List<TransactionListItem> list,
      String nextOffset,
      TransactionService service,
      TransactionListParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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

      return new TransactionListResponse(list, nextOffset, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionListResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into TransactionListResponse object with service context for pagination
   * (enables nextPage()).
   */
  public static TransactionListResponse fromJson(
      String json,
      TransactionService service,
      TransactionListParams originalParams,
      Response httpResponse) {
    try {

      List<TransactionListItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionListItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionListResponse(list, nextOffset, service, originalParams, httpResponse);
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
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    TransactionListParams nextParams =
        (originalParams != null ? originalParams.toBuilder() : TransactionListParams.builder())
            .offset(nextOffset)
            .build();

    return service.list(nextParams);
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
