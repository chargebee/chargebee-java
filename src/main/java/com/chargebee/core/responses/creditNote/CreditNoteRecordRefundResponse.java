package com.chargebee.core.responses.creditNote;

import com.chargebee.core.models.transaction.Transaction;

import com.chargebee.core.models.creditNote.CreditNote;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CreditNoteRecordRefund operation. Contains the response data from
 * the API.
 */
public final class CreditNoteRecordRefundResponse {

  private final CreditNote creditNote;

  private final Transaction transaction;

  private CreditNoteRecordRefundResponse(Builder builder) {

    this.creditNote = builder.creditNote;

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into CreditNoteRecordRefundResponse object. */
  public static CreditNoteRecordRefundResponse fromJson(String json) {
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
      throw new RuntimeException("Failed to parse CreditNoteRecordRefundResponse from JSON", e);
    }
  }

  /** Create a new builder for CreditNoteRecordRefundResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreditNoteRecordRefundResponse. */
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

    public CreditNoteRecordRefundResponse build() {
      return new CreditNoteRecordRefundResponse(this);
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
