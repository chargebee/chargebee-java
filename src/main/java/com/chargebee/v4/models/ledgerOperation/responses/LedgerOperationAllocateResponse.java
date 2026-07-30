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
 * Immutable response object for LedgerOperationAllocate operation. Contains the response data from
 * the API.
 */
public final class LedgerOperationAllocateResponse extends BaseResponse {
  private final LedgerAccountBalance ledgerAccountBalance;

  private final List<LedgerOperation> ledgerOperations;

  private final List<GrantBlock> grantBlocks;

  private final List<LedgerEntry> ledgerEntries;

  private LedgerOperationAllocateResponse(Builder builder) {
    super(builder.httpResponse);

    this.ledgerAccountBalance = builder.ledgerAccountBalance;

    this.ledgerOperations = builder.ledgerOperations;

    this.grantBlocks = builder.grantBlocks;

    this.ledgerEntries = builder.ledgerEntries;
  }

  /** Parse JSON response into LedgerOperationAllocateResponse object. */
  public static LedgerOperationAllocateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into LedgerOperationAllocateResponse object with HTTP response. */
  public static LedgerOperationAllocateResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __ledgerAccountBalanceObj =
          JsonUtil.getJsonObject(jsonObj, "ledger_account_balance");
      if (__ledgerAccountBalanceObj != null) {
        builder.ledgerAccountBalance(LedgerAccountBalance.fromJson(__ledgerAccountBalanceObj));
      }

      builder.ledgerOperations(
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "ledger_operations"), LedgerOperation::fromJson));

      builder.grantBlocks(
          JsonUtil.mapArray(JsonUtil.getJsonArray(jsonObj, "grant_blocks"), GrantBlock::fromJson));

      builder.ledgerEntries(
          JsonUtil.mapArray(
              JsonUtil.getJsonArray(jsonObj, "ledger_entries"), LedgerEntry::fromJson));

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse LedgerOperationAllocateResponse from JSON", e);
    }
  }

  /** Create a new builder for LedgerOperationAllocateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for LedgerOperationAllocateResponse. */
  public static class Builder {

    private LedgerAccountBalance ledgerAccountBalance;

    private List<LedgerOperation> ledgerOperations;

    private List<GrantBlock> grantBlocks;

    private List<LedgerEntry> ledgerEntries;

    private Response httpResponse;

    private Builder() {}

    public Builder ledgerAccountBalance(LedgerAccountBalance ledgerAccountBalance) {
      this.ledgerAccountBalance = ledgerAccountBalance;
      return this;
    }

    public Builder ledgerOperations(List<LedgerOperation> ledgerOperations) {
      this.ledgerOperations = ledgerOperations;
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

    public LedgerOperationAllocateResponse build() {
      return new LedgerOperationAllocateResponse(this);
    }
  }

  /** Get the ledgerAccountBalance from the response. */
  public LedgerAccountBalance getLedgerAccountBalance() {
    return ledgerAccountBalance;
  }

  /** Get the ledgerOperations from the response. */
  public List<LedgerOperation> getLedgerOperations() {
    return ledgerOperations;
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
    return "LedgerOperationAllocateResponse{"
        + "ledgerAccountBalance="
        + ledgerAccountBalance
        + ", ledgerOperations="
        + ledgerOperations
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

    LedgerOperationAllocateResponse that = (LedgerOperationAllocateResponse) o;
    return java.util.Objects.equals(ledgerAccountBalance, that.ledgerAccountBalance)
        && java.util.Objects.equals(ledgerOperations, that.ledgerOperations)
        && java.util.Objects.equals(grantBlocks, that.grantBlocks)
        && java.util.Objects.equals(ledgerEntries, that.ledgerEntries);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(
        ledgerAccountBalance, ledgerOperations, grantBlocks, ledgerEntries);
  }
}
