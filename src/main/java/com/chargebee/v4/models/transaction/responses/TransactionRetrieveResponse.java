package com.chargebee.v4.models.transaction.responses;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TransactionRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class TransactionRetrieveResponse extends BaseResponse {
  private final Transaction transaction;

  private TransactionRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into TransactionRetrieveResponse object. */
  public static TransactionRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into TransactionRetrieveResponse object with HTTP response. */
  public static TransactionRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __transactionObj = JsonUtil.getJsonObject(jsonObj, "transaction");
      if (__transactionObj != null) {
        builder.transaction(Transaction.fromJson(__transactionObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for TransactionRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TransactionRetrieveResponse. */
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

    public TransactionRetrieveResponse build() {
      return new TransactionRetrieveResponse(this);
    }
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }

  @Override
  public String toString() {
    return "TransactionRetrieveResponse{" + "transaction=" + transaction + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TransactionRetrieveResponse that = (TransactionRetrieveResponse) o;
    return java.util.Objects.equals(transaction, that.transaction);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(transaction);
  }
}
