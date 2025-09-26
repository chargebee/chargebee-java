package com.chargebee.core.responses.currency;

import com.chargebee.core.models.currency.Currency;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for CurrencyRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CurrencyRetrieveResponse {

  private final Currency currency;

  private CurrencyRetrieveResponse(Currency currency) {

    this.currency = currency;
  }

  /** Parse JSON response into CurrencyRetrieveResponse object. */
  public static CurrencyRetrieveResponse fromJson(String json) {
    try {

      Currency currency = Currency.fromJson(JsonUtil.getObject(json, "currency"));

      return new CurrencyRetrieveResponse(currency);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CurrencyRetrieveResponse from JSON", e);
    }
  }

  /** Get the currency from the response. */
  public Currency getCurrency() {
    return currency;
  }
}
