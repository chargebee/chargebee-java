package com.chargebee.v4.models.transaction.responses;

import java.util.List;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.exceptions.ChargebeeException;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import com.chargebee.v4.services.TransactionService;
import com.chargebee.v4.models.transaction.params.TransactionPaymentsForInvoiceParams;

/**
 * Immutable response object for TransactionPaymentsForInvoice operation. Contains paginated list
 * data.
 */
public final class TransactionPaymentsForInvoiceResponse {

  private final List<TransactionPaymentsForInvoiceItem> list;

  private final String nextOffset;

  private final String invoiceId;

  private final TransactionService service;
  private final TransactionPaymentsForInvoiceParams originalParams;
  private final Response httpResponse;

  private TransactionPaymentsForInvoiceResponse(
      List<TransactionPaymentsForInvoiceItem> list,
      String nextOffset,
      String invoiceId,
      TransactionService service,
      TransactionPaymentsForInvoiceParams originalParams,
      Response httpResponse) {

    this.list = list;

    this.nextOffset = nextOffset;

    this.invoiceId = invoiceId;

    this.service = service;
    this.originalParams = originalParams;
    this.httpResponse = httpResponse;
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

      return new TransactionPaymentsForInvoiceResponse(list, nextOffset, null, null, null, null);
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TransactionPaymentsForInvoiceResponse from JSON", e);
    }
  }

  /**
   * Parse JSON response into TransactionPaymentsForInvoiceResponse object with service context for
   * pagination (enables nextPage()).
   */
  public static TransactionPaymentsForInvoiceResponse fromJson(
      String json,
      TransactionService service,
      TransactionPaymentsForInvoiceParams originalParams,
      String invoiceId,
      Response httpResponse) {
    try {

      List<TransactionPaymentsForInvoiceItem> list =
          JsonUtil.parseObjectArray(JsonUtil.getArray(json, "list")).stream()
              .map(TransactionPaymentsForInvoiceItem::fromJson)
              .collect(java.util.stream.Collectors.toList());

      String nextOffset = JsonUtil.getString(json, "next_offset");

      return new TransactionPaymentsForInvoiceResponse(
          list, nextOffset, invoiceId, service, originalParams, httpResponse);
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

  /** Check if there are more pages available. */
  public boolean hasNextPage() {
    return nextOffset != null && !nextOffset.isEmpty();
  }

  /**
   * Get the next page of results.
   *
   * @throws ChargebeeException if unable to fetch next page
   */
  public TransactionPaymentsForInvoiceResponse nextPage() throws ChargebeeException {
    if (!hasNextPage()) {
      throw new IllegalStateException("No more pages available");
    }
    if (service == null) {
      throw new UnsupportedOperationException(
          "nextPage() requires service context. Use fromJson(json, service, originalParams, httpResponse).");
    }

    TransactionPaymentsForInvoiceParams nextParams =
        (originalParams != null
                ? originalParams.toBuilder()
                : TransactionPaymentsForInvoiceParams.builder())
            .offset(nextOffset)
            .build();

    return service.paymentsForInvoice(invoiceId, nextParams);
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

  @Override
  public String toString() {
    return "TransactionPaymentsForInvoiceResponse{"
        + "list="
        + list
        + ", nextOffset="
        + nextOffset
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TransactionPaymentsForInvoiceResponse that = (TransactionPaymentsForInvoiceResponse) o;
    return java.util.Objects.equals(list, that.list)
        && java.util.Objects.equals(nextOffset, that.nextOffset);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(list, nextOffset);
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

    @Override
    public String toString() {
      return "TransactionPaymentsForInvoiceItem{" + "transaction=" + transaction + "}";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      TransactionPaymentsForInvoiceItem that = (TransactionPaymentsForInvoiceItem) o;
      return java.util.Objects.equals(transaction, that.transaction);
    }

    @Override
    public int hashCode() {
      return java.util.Objects.hash(transaction);
    }
  }
}
