package com.chargebee.core.responses.transaction;

import com.chargebee.core.models.transaction.Transaction;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for TransactionReconcile operation. Contains the response data from the
 * API.
 */
public final class TransactionReconcileResponse {

  private final Transaction transaction;

  private TransactionReconcileResponse(Builder builder) {

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into TransactionReconcileResponse object. */
  public static TransactionReconcileResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionReconcileResponse from JSON", e);
    }
  }

  /** Create a new builder for TransactionReconcileResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TransactionReconcileResponse. */
  public static class Builder {

    private Transaction transaction;

    private Builder() {}

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public TransactionReconcileResponse build() {
      return new TransactionReconcileResponse(this);
    }
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }
}
