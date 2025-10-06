package com.chargebee.v4.core.responses.transaction;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for TransactionCreateAuthorization operation. Contains the response
 * data from the API.
 */
public final class TransactionCreateAuthorizationResponse {

  private final Transaction transaction;

  private TransactionCreateAuthorizationResponse(Builder builder) {

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into TransactionCreateAuthorizationResponse object. */
  public static TransactionCreateAuthorizationResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse TransactionCreateAuthorizationResponse from JSON", e);
    }
  }

  /** Create a new builder for TransactionCreateAuthorizationResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TransactionCreateAuthorizationResponse. */
  public static class Builder {

    private Transaction transaction;

    private Builder() {}

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public TransactionCreateAuthorizationResponse build() {
      return new TransactionCreateAuthorizationResponse(this);
    }
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }
}
