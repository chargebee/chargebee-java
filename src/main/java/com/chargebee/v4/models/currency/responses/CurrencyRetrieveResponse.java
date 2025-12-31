package com.chargebee.v4.models.currency.responses;

import com.chargebee.v4.models.currency.Currency;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CurrencyRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class CurrencyRetrieveResponse extends BaseResponse {
  private final Currency currency;

  private CurrencyRetrieveResponse(Builder builder) {
    super(builder.httpResponse);

    this.currency = builder.currency;
  }

  /** Parse JSON response into CurrencyRetrieveResponse object. */
  public static CurrencyRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CurrencyRetrieveResponse object with HTTP response. */
  public static CurrencyRetrieveResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __currencyJson = JsonUtil.getObject(json, "currency");
      if (__currencyJson != null) {
        builder.currency(Currency.fromJson(__currencyJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CurrencyRetrieveResponse from JSON", e);
    }
  }

  /** Create a new builder for CurrencyRetrieveResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CurrencyRetrieveResponse. */
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

    public CurrencyRetrieveResponse build() {
      return new CurrencyRetrieveResponse(this);
    }
  }

  /** Get the currency from the response. */
  public Currency getCurrency() {
    return currency;
  }

  @Override
  public String toString() {
    return "CurrencyRetrieveResponse{" + "currency=" + currency + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CurrencyRetrieveResponse that = (CurrencyRetrieveResponse) o;
    return java.util.Objects.equals(currency, that.currency);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(currency);
  }
}
