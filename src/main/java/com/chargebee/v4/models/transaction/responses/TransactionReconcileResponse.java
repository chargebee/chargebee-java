package com.chargebee.v4.models.transaction.responses;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TransactionReconcile operation. Contains the response data from the
 * API.
 */
public final class TransactionReconcileResponse extends BaseResponse {
  private final Transaction transaction;

  private TransactionReconcileResponse(Builder builder) {
    super(builder.httpResponse);

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into TransactionReconcileResponse object. */
  public static TransactionReconcileResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into TransactionReconcileResponse object with HTTP response. */
  public static TransactionReconcileResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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
