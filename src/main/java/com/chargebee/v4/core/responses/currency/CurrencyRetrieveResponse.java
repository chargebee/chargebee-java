package com.chargebee.v4.core.responses.currency;

import com.chargebee.v4.core.models.currency.Currency;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CurrencyRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CurrencyRetrieveResponse extends BaseResponse {
  private final Currency currency;

  private CurrencyRetrieveResponse(Currency currency, Response httpResponse) {
    super(httpResponse);

    this.currency = currency;
  }

  /** Parse JSON response into CurrencyRetrieveResponse object. */
  public static CurrencyRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CurrencyRetrieveResponse object with HTTP response. */
  public static CurrencyRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Currency currency = Currency.fromJson(JsonUtil.getObject(json, "currency"));

      return new CurrencyRetrieveResponse(currency, httpResponse);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CurrencyRetrieveResponse from JSON", e);
    }
  }

  /** Get the currency from the response. */
  public Currency getCurrency() {
    return currency;
  }
}
