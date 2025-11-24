package com.chargebee.v4.models.transaction.responses;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for ReconcileForTransaction operation. Contains the response data from
 * the API.
 */
public final class ReconcileForTransactionResponse extends BaseResponse {
  private final Transaction transaction;

  private ReconcileForTransactionResponse(Builder builder) {
    super(builder.httpResponse);

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into ReconcileForTransactionResponse object. */
  public static ReconcileForTransactionResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into ReconcileForTransactionResponse object with HTTP response. */
  public static ReconcileForTransactionResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ReconcileForTransactionResponse from JSON", e);
    }
  }

  /** Create a new builder for ReconcileForTransactionResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for ReconcileForTransactionResponse. */
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

    public ReconcileForTransactionResponse build() {
      return new ReconcileForTransactionResponse(this);
    }
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }
}
