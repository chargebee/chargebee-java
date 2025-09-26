package com.chargebee.core.responses.currency;

import com.chargebee.core.models.currency.Currency;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CurrencyUpdate operation. Contains the response data from the API.
 */
public final class CurrencyUpdateResponse {

  private final Currency currency;

  private CurrencyUpdateResponse(Builder builder) {

    this.currency = builder.currency;
  }

  /** Parse JSON response into CurrencyUpdateResponse object. */
  public static CurrencyUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __currencyJson = JsonUtil.getObject(json, "currency");
      if (__currencyJson != null) {
        builder.currency(Currency.fromJson(__currencyJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CurrencyUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for CurrencyUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CurrencyUpdateResponse. */
  public static class Builder {

    private Currency currency;

    private Builder() {}

    public Builder currency(Currency currency) {
      this.currency = currency;
      return this;
    }

    public CurrencyUpdateResponse build() {
      return new CurrencyUpdateResponse(this);
    }
  }

  /** Get the currency from the response. */
  public Currency getCurrency() {
    return currency;
  }
}
