package com.chargebee.v4.models.ledgerAccountBalance.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for LedgerAccountBalanceUpdateOverdraftLimit operation. Contains the
 * response data from the API.
 */
public final class LedgerAccountBalanceUpdateOverdraftLimitResponse extends BaseResponse {
  private final String subscriptionId;

  private final String unitId;

  private final String changeReason;

  private final java.util.Map<String, Object> overdraftBalance;

  private LedgerAccountBalanceUpdateOverdraftLimitResponse(Builder builder) {
    super(builder.httpResponse);

    this.subscriptionId = builder.subscriptionId;

    this.unitId = builder.unitId;

    this.changeReason = builder.changeReason;

    this.overdraftBalance = builder.overdraftBalance;
  }

  /** Parse JSON response into LedgerAccountBalanceUpdateOverdraftLimitResponse object. */
  public static LedgerAccountBalanceUpdateOverdraftLimitResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into LedgerAccountBalanceUpdateOverdraftLimitResponse object with HTTP
   * response.
   */
  public static LedgerAccountBalanceUpdateOverdraftLimitResponse fromJson(
      String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      builder.subscriptionId(JsonUtil.getString(jsonObj, "subscription_id"));

      builder.unitId(JsonUtil.getString(jsonObj, "unit_id"));

      builder.changeReason(JsonUtil.getString(jsonObj, "change_reason"));

      JsonObject __overdraftBalanceObj = JsonUtil.getJsonObject(jsonObj, "overdraft_balance");
      if (__overdraftBalanceObj != null) {
        builder.overdraftBalance(JsonUtil.parseJsonObjectToMap(__overdraftBalanceObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse LedgerAccountBalanceUpdateOverdraftLimitResponse from JSON", e);
    }
  }

  /** Create a new builder for LedgerAccountBalanceUpdateOverdraftLimitResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for LedgerAccountBalanceUpdateOverdraftLimitResponse. */
  public static class Builder {

    private String subscriptionId;

    private String unitId;

    private String changeReason;

    private java.util.Map<String, Object> overdraftBalance;

    private Response httpResponse;

    private Builder() {}

    public Builder subscriptionId(String subscriptionId) {
      this.subscriptionId = subscriptionId;
      return this;
    }

    public Builder unitId(String unitId) {
      this.unitId = unitId;
      return this;
    }

    public Builder changeReason(String changeReason) {
      this.changeReason = changeReason;
      return this;
    }

    public Builder overdraftBalance(java.util.Map<String, Object> overdraftBalance) {
      this.overdraftBalance = overdraftBalance;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public LedgerAccountBalanceUpdateOverdraftLimitResponse build() {
      return new LedgerAccountBalanceUpdateOverdraftLimitResponse(this);
    }
  }

  /** Get the subscriptionId from the response. */
  public String getSubscriptionId() {
    return subscriptionId;
  }

  /** Get the unitId from the response. */
  public String getUnitId() {
    return unitId;
  }

  /** Get the changeReason from the response. */
  public String getChangeReason() {
    return changeReason;
  }

  /** Get the overdraftBalance from the response. */
  public java.util.Map<String, Object> getOverdraftBalance() {
    return overdraftBalance;
  }

  @Override
  public String toString() {
    return "LedgerAccountBalanceUpdateOverdraftLimitResponse{"
        + "subscriptionId="
        + subscriptionId
        + ", unitId="
        + unitId
        + ", changeReason="
        + changeReason
        + ", overdraftBalance="
        + overdraftBalance
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LedgerAccountBalanceUpdateOverdraftLimitResponse that =
        (LedgerAccountBalanceUpdateOverdraftLimitResponse) o;
    return java.util.Objects.equals(subscriptionId, that.subscriptionId)
        && java.util.Objects.equals(unitId, that.unitId)
        && java.util.Objects.equals(changeReason, that.changeReason)
        && java.util.Objects.equals(overdraftBalance, that.overdraftBalance);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(subscriptionId, unitId, changeReason, overdraftBalance);
  }
}
