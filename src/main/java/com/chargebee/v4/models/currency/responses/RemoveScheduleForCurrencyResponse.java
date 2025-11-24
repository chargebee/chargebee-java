package com.chargebee.v4.models.currency.responses;

import com.chargebee.v4.models.currency.Currency;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;
import java.sql.Timestamp;

/**
 * Immutable response object for RemoveScheduleForCurrency operation. Contains the response data
 * from the API.
 */
public final class RemoveScheduleForCurrencyResponse extends BaseResponse {
  private final Timestamp scheduledAt;

  private final Currency currency;

  private RemoveScheduleForCurrencyResponse(Builder builder) {
    super(builder.httpResponse);

    this.scheduledAt = builder.scheduledAt;

    this.currency = builder.currency;
  }

  /** Parse JSON response into RemoveScheduleForCurrencyResponse object. */
  public static RemoveScheduleForCurrencyResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into RemoveScheduleForCurrencyResponse object with HTTP response. */
  public static RemoveScheduleForCurrencyResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.scheduledAt(JsonUtil.getTimestamp(json, "scheduled_at"));

      String __currencyJson = JsonUtil.getObject(json, "currency");
      if (__currencyJson != null) {
        builder.currency(Currency.fromJson(__currencyJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse RemoveScheduleForCurrencyResponse from JSON", e);
    }
  }

  /** Create a new builder for RemoveScheduleForCurrencyResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for RemoveScheduleForCurrencyResponse. */
  public static class Builder {

    private Timestamp scheduledAt;

    private Currency currency;

    private Response httpResponse;

    private Builder() {}

    public Builder scheduledAt(Timestamp scheduledAt) {
      this.scheduledAt = scheduledAt;
      return this;
    }

    public Builder currency(Currency currency) {
      this.currency = currency;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public RemoveScheduleForCurrencyResponse build() {
      return new RemoveScheduleForCurrencyResponse(this);
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
