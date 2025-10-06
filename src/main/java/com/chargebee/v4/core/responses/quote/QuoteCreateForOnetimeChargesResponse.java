package com.chargebee.v4.core.responses.quote;

import com.chargebee.v4.core.models.quote.Quote;

import com.chargebee.v4.core.models.quotedCharge.QuotedCharge;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for QuoteCreateForOnetimeCharges operation. Contains the response data
 * from the API.
 */
public final class QuoteCreateForOnetimeChargesResponse {

  private final Quote quote;

  private final QuotedCharge quotedCharge;

  private QuoteCreateForOnetimeChargesResponse(Builder builder) {

    this.quote = builder.quote;

    this.quotedCharge = builder.quotedCharge;
  }

  /** Parse JSON response into QuoteCreateForOnetimeChargesResponse object. */
  public static QuoteCreateForOnetimeChargesResponse fromJson(String json) {
    try {
      Builder builder = builder();

      String __quoteJson = JsonUtil.getObject(json, "quote");
      if (__quoteJson != null) {
        builder.quote(Quote.fromJson(__quoteJson));
      }

      String __quotedChargeJson = JsonUtil.getObject(json, "quoted_charge");
      if (__quotedChargeJson != null) {
        builder.quotedCharge(QuotedCharge.fromJson(__quotedChargeJson));
      }

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse QuoteCreateForOnetimeChargesResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteCreateForOnetimeChargesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteCreateForOnetimeChargesResponse. */
  public static class Builder {

    private Quote quote;

    private QuotedCharge quotedCharge;

    private Builder() {}

    public Builder quote(Quote quote) {
      this.quote = quote;
      return this;
    }

    public Builder quotedCharge(QuotedCharge quotedCharge) {
      this.quotedCharge = quotedCharge;
      return this;
    }

    public QuoteCreateForOnetimeChargesResponse build() {
      return new QuoteCreateForOnetimeChargesResponse(this);
    }
  }

  /** Get the quote from the response. */
  public Quote getQuote() {
    return quote;
  }

  /** Get the quotedCharge from the response. */
  public QuotedCharge getQuotedCharge() {
    return quotedCharge;
  }
}
