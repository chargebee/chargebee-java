package com.chargebee.core.responses.virtualBankAccount;

import com.chargebee.core.models.transaction.Transaction;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for VirtualBankAccountSyncFund operation. Contains the response data
 * from the API.
 */
public final class VirtualBankAccountSyncFundResponse {

  private final Transaction transaction;

  private VirtualBankAccountSyncFundResponse(Builder builder) {

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into VirtualBankAccountSyncFundResponse object. */
  public static VirtualBankAccountSyncFundResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse VirtualBankAccountSyncFundResponse from JSON", e);
    }
  }

  /** Create a new builder for VirtualBankAccountSyncFundResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for VirtualBankAccountSyncFundResponse. */
  public static class Builder {

    private Transaction transaction;

    private Builder() {}

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public VirtualBankAccountSyncFundResponse build() {
      return new VirtualBankAccountSyncFundResponse(this);
    }
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }
}
