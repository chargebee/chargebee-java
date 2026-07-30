package com.chargebee.v4.models.ledgerOperation.responses;

import java.util.List;

import com.chargebee.v4.models.ledgerOperation.LedgerOperation;

import com.chargebee.v4.models.ledgerEntry.LedgerEntry;

import com.chargebee.v4.models.ledgerAccountBalance.LedgerAccountBalance;

import com.chargebee.v4.models.grantBlock.GrantBlock;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for LedgerOperationCapture operation. Contains the response data from
 * the API.
 */
public final class LedgerOperationCaptureResponse extends BaseResponse {
  private final LedgerOperation ledgerOperation;

  private final LedgerAccountBalance ledgerAccountBalance;

  private final List<GrantBlock> grantBlocks;

  private final List<LedgerEntry> ledgerEntries;

  private LedgerOperationCaptureResponse(Builder builder) {
    super(builder.httpResponse);

    this.ledgerOperation = builder.ledgerOperation;

    this.ledgerAccountBalance = builder.ledgerAccountBalance;

    this.grantBlocks = builder.grantBlocks;

    this.ledgerEntries = builder.ledgerEntries;
  }

  /** Parse JSON response into LedgerOperationCaptureResponse object. */
  public static LedgerOperationCaptureResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into LedgerOperationCaptureResponse object with HTTP response. */
  public static LedgerOperationCaptureResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __ledgerOperationObj = JsonUtil.getJsonObject(jsonObj, "ledger_operation");
      if (__ledgerOperationObj != null) {
        builder.ledgerOperation(LedgerOperation.fromJson(__ledgerOperationObj));
      }

      JsonObject __ledgerAccountBalanceObj =
          JsonUtil.getJsonObject(jsonObj, "ledger_account_balance");
      if (__ledgerAccountBalanceObj != null) {
        builder.ledgerAccountBalance(LedgerAccountBalance.fromJson(__ledgerAccountBalanceObj));
      }

      builder.grantBlocks(
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "grant_blocks"), GrantBlock::fromJson));

      builder.ledgerEntries(
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "ledger_entries"), LedgerEntry::fromJson));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse LedgerOperationCaptureResponse from JSON", e);
    }
  }

  /** Create a new builder for LedgerOperationCaptureResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for LedgerOperationCaptureResponse. */
  public static class Builder {

    private LedgerOperation ledgerOperation;

    private LedgerAccountBalance ledgerAccountBalance;

    private List<GrantBlock> grantBlocks;

    private List<LedgerEntry> ledgerEntries;

    private Response httpResponse;

    private Builder() {}

    public Builder ledgerOperation(LedgerOperation ledgerOperation) {
      this.ledgerOperation = ledgerOperation;
      return this;
    }

    public Builder ledgerAccountBalance(LedgerAccountBalance ledgerAccountBalance) {
      this.ledgerAccountBalance = ledgerAccountBalance;
      return this;
    }

    public Builder grantBlocks(List<GrantBlock> grantBlocks) {
      this.grantBlocks = grantBlocks;
      return this;
    }

    public Builder ledgerEntries(List<LedgerEntry> ledgerEntries) {
      this.ledgerEntries = ledgerEntries;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public LedgerOperationCaptureResponse build() {
      return new LedgerOperationCaptureResponse(this);
    }
  }

  /** Get the ledgerOperation from the response. */
  public LedgerOperation getLedgerOperation() {
    return ledgerOperation;
  }

  /** Get the ledgerAccountBalance from the response. */
  public LedgerAccountBalance getLedgerAccountBalance() {
    return ledgerAccountBalance;
  }

  /** Get the grantBlocks from the response. */
  public List<GrantBlock> getGrantBlocks() {
    return grantBlocks;
  }

  /** Get the ledgerEntries from the response. */
  public List<LedgerEntry> getLedgerEntries() {
    return ledgerEntries;
  }

  @Override
  public String toString() {
    return "LedgerOperationCaptureResponse{"
        + "ledgerOperation="
        + ledgerOperation
        + ", ledgerAccountBalance="
        + ledgerAccountBalance
        + ", grantBlocks="
        + grantBlocks
        + ", ledgerEntries="
        + ledgerEntries
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LedgerOperationCaptureResponse that = (LedgerOperationCaptureResponse) o;
    return java.util.Objects.equals(ledgerOperation, that.ledgerOperation)
        && java.util.Objects.equals(ledgerAccountBalance, that.ledgerAccountBalance)
        && java.util.Objects.equals(grantBlocks, that.grantBlocks)
        && java.util.Objects.equals(ledgerEntries, that.ledgerEntries);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(
        ledgerOperation, ledgerAccountBalance, grantBlocks, ledgerEntries);
  }
}
