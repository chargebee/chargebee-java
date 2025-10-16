package com.chargebee.v4.core.responses.transaction;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TransactionRecordRefund operation. Contains the response data from
 * the API.
 */
public final class TransactionRecordRefundResponse extends BaseResponse {
  private final Transaction transaction;

  private TransactionRecordRefundResponse(Builder builder) {
    super(builder.httpResponse);

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into TransactionRecordRefundResponse object. */
  public static TransactionRecordRefundResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into TransactionRecordRefundResponse object with HTTP response. */
  public static TransactionRecordRefundResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse TransactionRecordRefundResponse from JSON", e);
    }
  }

  /** Create a new builder for TransactionRecordRefundResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for TransactionRecordRefundResponse. */
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

    public TransactionRecordRefundResponse build() {
      return new TransactionRecordRefundResponse(this);
    }
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }
}
