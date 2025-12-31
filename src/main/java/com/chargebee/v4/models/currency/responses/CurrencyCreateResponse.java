package com.chargebee.v4.models.currency.responses;

import com.chargebee.v4.models.currency.Currency;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CurrencyCreate operation. Contains the response data from the API.
 */
public final class CurrencyCreateResponse extends BaseResponse {
  private final Currency currency;

  private CurrencyCreateResponse(Builder builder) {
    super(builder.httpResponse);

    this.currency = builder.currency;
  }

  /** Parse JSON response into CurrencyCreateResponse object. */
  public static CurrencyCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CurrencyCreateResponse object with HTTP response. */
  public static CurrencyCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __currencyJson = JsonUtil.getObject(json, "currency");
      if (__currencyJson != null) {
        builder.currency(Currency.fromJson(__currencyJson));
      }

      builder.httpResponse(httpResponse);
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

    public CurrencyCreateResponse build() {
      return new CurrencyCreateResponse(this);
    }
  }

  /** Get the currency from the response. */
  public Currency getCurrency() {
    return currency;
  }

  @Override
  public String toString() {
    return "CurrencyCreateResponse{" + "currency=" + currency + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CurrencyCreateResponse that = (CurrencyCreateResponse) o;
    return java.util.Objects.equals(currency, that.currency);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(currency);
  }
}
