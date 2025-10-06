package com.chargebee.v4.core.responses.transaction;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for TransactionRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class TransactionRetrieveResponse {

  private final Transaction transaction;

  private TransactionRetrieveResponse(Transaction transaction) {

    this.transaction = transaction;
  }

  /** Parse JSON response into TransactionRetrieveResponse object. */
  public static TransactionRetrieveResponse fromJson(String json) {
    try {

      Transaction transaction = Transaction.fromJson(JsonUtil.getObject(json, "transaction"));

      return new TransactionRetrieveResponse(transaction);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionRetrieveResponse from JSON", e);
    }
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }
}
