package com.chargebee.v4.core.responses.creditNote;

import com.chargebee.v4.core.models.transaction.Transaction;

import com.chargebee.v4.core.models.creditNote.CreditNote;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for CreditNoteRefund operation. Contains the response data from the
 * API.
 */
public final class CreditNoteRefundResponse {

  private final CreditNote creditNote;

  private final Transaction transaction;

  private CreditNoteRefundResponse(Builder builder) {

    this.creditNote = builder.creditNote;

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into CreditNoteRefundResponse object. */
  public static CreditNoteRefundResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __creditNoteJson = JsonUtil.getObject(json, "credit_note");
      if (__creditNoteJson != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteJson));
      }

      String __transactionJson = JsonUtil.getObject(json, "transaction");
      if (__transactionJson != null) {
        builder.transaction(Transaction.fromJson(__transactionJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CreditNoteRefundResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteRefundResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteRefundResponse. */
  public static class Builder {

    private CreditNote creditNote;

    private Transaction transaction;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public CreditNoteRefundResponse build() {
      return new CreditNoteRefundResponse(this);
    }
  }

  /** Get the creditNote from the response. */
  public CreditNote getCreditNote() {
    return creditNote;
  }

  /** Get the transaction from the response. */
  public Transaction getTransaction() {
    return transaction;
  }
}
