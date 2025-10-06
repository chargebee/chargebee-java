package com.chargebee.v4.core.responses.transaction;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for TransactionVoidTransaction operation. Contains the response data
 * from the API.
 */
public final class TransactionVoidTransactionResponse {

  private final Transaction transaction;

  private TransactionVoidTransactionResponse(Builder builder) {

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into TransactionVoidTransactionResponse object. */
  public static TransactionVoidTransactionResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionVoidTransactionResponse from JSON", e);
    }
  }

  /** Create a new builder for TransactionVoidTransactionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TransactionVoidTransactionResponse. */
  public static class Builder {

    private Transaction transaction;

    private Builder() {}

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public TransactionVoidTransactionResponse build() {
      return new TransactionVoidTransactionResponse(this);
    }
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }
}
