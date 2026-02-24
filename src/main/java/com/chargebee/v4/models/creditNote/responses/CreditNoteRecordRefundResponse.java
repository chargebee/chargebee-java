package com.chargebee.v4.models.creditNote.responses;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreditNoteRecordRefund operation. Contains the response data from
 * the API.
 */
public final class CreditNoteRecordRefundResponse extends BaseResponse {
  private final CreditNote creditNote;

  private final Transaction transaction;

  private CreditNoteRecordRefundResponse(Builder builder) {
    super(builder.httpResponse);

    this.creditNote = builder.creditNote;

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into CreditNoteRecordRefundResponse object. */
  public static CreditNoteRecordRefundResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteRecordRefundResponse object with HTTP response. */
  public static CreditNoteRecordRefundResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __creditNoteObj = JsonUtil.getJsonObject(jsonObj, "credit_note");
      if (__creditNoteObj != null) {
        builder.creditNote(CreditNote.fromJson(__creditNoteObj));
      }

      JsonObject __transactionObj = JsonUtil.getJsonObject(jsonObj, "transaction");
      if (__transactionObj != null) {
        builder.transaction(Transaction.fromJson(__transactionObj));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder creditNote(CreditNote creditNote) {
      this.creditNote = creditNote;
      return this;
    }

    public Builder transaction(Transaction transaction) {
      this.transaction = transaction;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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

  @Override
  public String toString() {
    return "CreditNoteRecordRefundResponse{"
        + "creditNote="
        + creditNote
        + ", transaction="
        + transaction
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CreditNoteRecordRefundResponse that = (CreditNoteRecordRefundResponse) o;
    return java.util.Objects.equals(creditNote, that.creditNote)
        && java.util.Objects.equals(transaction, that.transaction);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(creditNote, transaction);
  }
}
