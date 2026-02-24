package com.chargebee.v4.models.creditNote.responses;

import com.chargebee.v4.models.transaction.Transaction;

import com.chargebee.v4.models.creditNote.CreditNote;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreditNoteRefund operation. Contains the response data from the
 * API.
 */
public final class CreditNoteRefundResponse extends BaseResponse {
  private final CreditNote creditNote;

  private final Transaction transaction;

  private CreditNoteRefundResponse(Builder builder) {
    super(builder.httpResponse);

    this.creditNote = builder.creditNote;

    this.transaction = builder.transaction;
  }

  /** Parse JSON response into CreditNoteRefundResponse object. */
  public static CreditNoteRefundResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CreditNoteRefundResponse object with HTTP response. */
  public static CreditNoteRefundResponse fromJson(String json, Response httpResponse) {
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

  @Override
  public String toString() {
    return "CreditNoteRefundResponse{"
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

    CreditNoteRefundResponse that = (CreditNoteRefundResponse) o;
    return java.util.Objects.equals(creditNote, that.creditNote)
        && java.util.Objects.equals(transaction, that.transaction);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(creditNote, transaction);
  }
}
