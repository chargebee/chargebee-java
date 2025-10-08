package com.chargebee.v4.core.responses.currency;

import com.chargebee.v4.core.models.currency.Currency;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CurrencyRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CurrencyRetrieveResponse {

  private final Currency currency;

  private final Response httpResponse;

  private CurrencyRetrieveResponse(Currency currency, Response httpResponse) {

    this.currency = currency;

    this.httpResponse = httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
