package com.chargebee.v4.models.ledgerOperation.responses;

import com.chargebee.v4.models.ledgerOperation.LedgerOperation;

import com.chargebee.v4.models.ledgerAccountBalance.LedgerAccountBalance;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for LedgerOperationReleaseAuthorization operation. Contains the
 * response data from the API.
 */
public final class LedgerOperationReleaseAuthorizationResponse extends BaseResponse {
  private final LedgerOperation ledgerOperation;

  private final LedgerAccountBalance ledgerAccountBalance;

  private LedgerOperationReleaseAuthorizationResponse(Builder builder) {
    super(builder.httpResponse);

    this.ledgerOperation = builder.ledgerOperation;

    this.ledgerAccountBalance = builder.ledgerAccountBalance;
  }

  /** Parse JSON response into LedgerOperationReleaseAuthorizationResponse object. */
  public static LedgerOperationReleaseAuthorizationResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into LedgerOperationReleaseAuthorizationResponse object with HTTP response.
   */
  public static LedgerOperationReleaseAuthorizationResponse fromJson(
      String json, Response httpResponse) {
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

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse LedgerOperationReleaseAuthorizationResponse from JSON", e);
    }
  }

  /** Create a new builder for LedgerOperationReleaseAuthorizationResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for LedgerOperationReleaseAuthorizationResponse. */
  public static class Builder {

    private LedgerOperation ledgerOperation;

    private LedgerAccountBalance ledgerAccountBalance;

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

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public LedgerOperationReleaseAuthorizationResponse build() {
      return new LedgerOperationReleaseAuthorizationResponse(this);
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

  @Override
  public String toString() {
    return "LedgerOperationReleaseAuthorizationResponse{"
        + "ledgerOperation="
        + ledgerOperation
        + ", ledgerAccountBalance="
        + ledgerAccountBalance
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LedgerOperationReleaseAuthorizationResponse that =
        (LedgerOperationReleaseAuthorizationResponse) o;
    return java.util.Objects.equals(ledgerOperation, that.ledgerOperation)
        && java.util.Objects.equals(ledgerAccountBalance, that.ledgerAccountBalance);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(ledgerOperation, ledgerAccountBalance);
  }
}
