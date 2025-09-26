package com.chargebee.core.responses.currency;

import com.chargebee.core.models.currency.Currency;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CurrencyCreate operation. Contains the response data from the API.
 */
public final class CurrencyCreateResponse {

  private final Currency currency;

  private CurrencyCreateResponse(Builder builder) {

    this.currency = builder.currency;
  }

  /** Parse JSON response into CurrencyCreateResponse object. */
  public static CurrencyCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __currencyJson = JsonUtil.getObject(json, "currency");
      if (__currencyJson != null) {
        builder.currency(Currency.fromJson(__currencyJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CurrencyCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CurrencyCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CurrencyCreateResponse. */
  public static class Builder {

    private Currency currency;

    private Builder() {}

    public Builder currency(Currency currency) {
      this.currency = currency;
      return this;
    }

    public CurrencyCreateResponse build() {
      return new CurrencyCreateResponse(this);
    }
  }

  /** Get the currency from the response. */
  public Currency getCurrency() {
    return currency;
  }
}
