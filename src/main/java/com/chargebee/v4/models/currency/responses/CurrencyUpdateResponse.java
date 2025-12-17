package com.chargebee.v4.models.currency.responses;

import com.chargebee.v4.models.currency.Currency;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CurrencyUpdate operation. Contains the response data from the API.
 */
public final class CurrencyUpdateResponse extends BaseResponse {
  private final Currency currency;

  private CurrencyUpdateResponse(Builder builder) {
    super(builder.httpResponse);

    this.currency = builder.currency;
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
}
