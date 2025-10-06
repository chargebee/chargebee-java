package com.chargebee.v4.core.responses.currency;

import com.chargebee.v4.core.models.currency.Currency;

import com.chargebee.v4.internal.JsonUtil;
import java.sql.Timestamp;

/**
 * Immutable response object for CurrencyAddSchedule operation. Contains the response data from the
 * API.
 */
public final class CurrencyAddScheduleResponse {

  private final Timestamp scheduledAt;

  private final Currency currency;

  private CurrencyAddScheduleResponse(Builder builder) {

    this.scheduledAt = builder.scheduledAt;

    this.currency = builder.currency;
  }

  /** Parse JSON response into CurrencyAddScheduleResponse object. */
  public static CurrencyAddScheduleResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.scheduledAt(JsonUtil.getTimestamp(json, "scheduled_at"));

      String __currencyJson = JsonUtil.getObject(json, "currency");
      if (__currencyJson != null) {
        builder.currency(Currency.fromJson(__currencyJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CurrencyAddScheduleResponse from JSON", e);
    }
  }

  /** Create a new builder for CurrencyAddScheduleResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CurrencyAddScheduleResponse. */
  public static class Builder {

    private Timestamp scheduledAt;

    private Currency currency;

    private Builder() {}

    public Builder scheduledAt(Timestamp scheduledAt) {
      this.scheduledAt = scheduledAt;
      return this;
    }

    public Builder currency(Currency currency) {
      this.currency = currency;
      return this;
    }

    public CurrencyAddScheduleResponse build() {
      return new CurrencyAddScheduleResponse(this);
    }
  }

  /** Get the scheduledAt from the response. */
  public Timestamp getScheduledAt() {
    return scheduledAt;
  }

  /** Get the currency from the response. */
  public Currency getCurrency() {
    return currency;
  }
}
