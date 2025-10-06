package com.chargebee.v4.core.responses.quote;

import com.chargebee.v4.core.models.quote.Quote;

import com.chargebee.v4.core.models.quotedCharge.QuotedCharge;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for QuoteEditOneTimeQuote operation. Contains the response data from
 * the API.
 */
public final class QuoteEditOneTimeQuoteResponse {

  private final Quote quote;

  private final QuotedCharge quotedCharge;

  private QuoteEditOneTimeQuoteResponse(Builder builder) {

    this.quote = builder.quote;

    this.quotedCharge = builder.quotedCharge;
  }

  /** Parse JSON response into QuoteEditOneTimeQuoteResponse object. */
  public static QuoteEditOneTimeQuoteResponse fromJson(String json) {
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
      throw new RuntimeException("Failed to parse QuoteEditOneTimeQuoteResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteEditOneTimeQuoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteEditOneTimeQuoteResponse. */
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

    public QuoteEditOneTimeQuoteResponse build() {
      return new QuoteEditOneTimeQuoteResponse(this);
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
