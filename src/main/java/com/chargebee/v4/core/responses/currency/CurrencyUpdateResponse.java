package com.chargebee.v4.core.responses.currency;

import com.chargebee.v4.core.models.currency.Currency;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CurrencyUpdate operation. Contains the response data from the API.
 */
public final class CurrencyUpdateResponse {

  private final Currency currency;

  private final Response httpResponse;

  private CurrencyUpdateResponse(Builder builder) {

    this.currency = builder.currency;

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CurrencyUpdateResponse object. */
  public static CurrencyUpdateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CurrencyUpdateResponse object with HTTP response. */
  public static CurrencyUpdateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __currencyJson = JsonUtil.getObject(json, "currency");
      if (__currencyJson != null) {
        builder.currency(Currency.fromJson(__currencyJson));
      }

      builder.httpResponse(httpResponse);
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

    private Response httpResponse;

    private Builder() {}

    public Builder currency(Currency currency) {
      this.currency = currency;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
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
