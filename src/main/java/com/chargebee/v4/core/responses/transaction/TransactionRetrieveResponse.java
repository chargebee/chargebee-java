package com.chargebee.v4.core.responses.transaction;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TransactionRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class TransactionRetrieveResponse {

  private final Transaction transaction;

  private final Response httpResponse;

  private TransactionRetrieveResponse(Transaction transaction, Response httpResponse) {

    this.transaction = transaction;

    this.httpResponse = httpResponse;
  }

  /** Parse JSON response into TransactionRetrieveResponse object. */
  public static TransactionRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into TransactionRetrieveResponse object with HTTP response. */
  public static TransactionRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Transaction transaction = Transaction.fromJson(JsonUtil.getObject(json, "transaction"));

      return new TransactionRetrieveResponse(transaction, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionRetrieveResponse from JSON", e);
    }
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
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
}
