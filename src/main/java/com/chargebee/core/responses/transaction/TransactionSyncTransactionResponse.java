package com.chargebee.core.responses.transaction;

import com.chargebee.core.models.transaction.Transaction;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for TransactionSyncTransaction operation. Contains the response data
 * from the API.
 */
public final class TransactionSyncTransactionResponse {

  private final Transaction transaction;

  private TransactionSyncTransactionResponse(Builder builder) {

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into TransactionSyncTransactionResponse object. */
  public static TransactionSyncTransactionResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionSyncTransactionResponse from JSON", e);
    }
  }

  /** Create a new builder for TransactionSyncTransactionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TransactionSyncTransactionResponse. */
  public static class Builder {

    private Transaction transaction;

    private Builder() {}

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public TransactionSyncTransactionResponse build() {
      return new TransactionSyncTransactionResponse(this);
    }
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }
}
