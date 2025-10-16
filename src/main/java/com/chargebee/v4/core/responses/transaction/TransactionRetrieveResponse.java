package com.chargebee.v4.core.responses.transaction;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for TransactionRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class TransactionRetrieveResponse extends BaseResponse {
  private final Transaction transaction;

  private TransactionRetrieveResponse(Transaction transaction, Response httpResponse) {
    super(httpResponse);

    this.transaction = transaction;
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
}
