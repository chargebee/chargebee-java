package com.chargebee.core.responses.quote;

import com.chargebee.core.models.quote.Quote;

import com.chargebee.core.models.quotedCharge.QuotedCharge;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for QuoteEditForChargeItemsAndCharges operation. Contains the response
 * data from the API.
 */
public final class QuoteEditForChargeItemsAndChargesResponse {

  private final Quote quote;

  private final QuotedCharge quotedCharge;

  private QuoteEditForChargeItemsAndChargesResponse(Builder builder) {

    this.quote = builder.quote;

    this.quotedCharge = builder.quotedCharge;
  }

  /** Parse JSON response into QuoteEditForChargeItemsAndChargesResponse object. */
  public static QuoteEditForChargeItemsAndChargesResponse fromJson(String json) {
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
          "Failed to parse QuoteEditForChargeItemsAndChargesResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteEditForChargeItemsAndChargesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteEditForChargeItemsAndChargesResponse. */
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

    public QuoteEditForChargeItemsAndChargesResponse build() {
      return new QuoteEditForChargeItemsAndChargesResponse(this);
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
