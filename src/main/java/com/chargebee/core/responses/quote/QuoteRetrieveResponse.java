package com.chargebee.core.responses.quote;

import com.chargebee.core.models.quote.Quote;

import com.chargebee.core.models.quotedSubscription.QuotedSubscription;

import com.chargebee.core.models.quotedCharge.QuotedCharge;

import com.chargebee.core.models.quotedRamp.QuotedRamp;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for QuoteRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class QuoteRetrieveResponse {

  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private final QuotedCharge quotedCharge;

  private final QuotedRamp quotedRamp;

  private QuoteRetrieveResponse(
      Quote quote,
      QuotedSubscription quotedSubscription,
      QuotedCharge quotedCharge,
      QuotedRamp quotedRamp) {

    this.quote = quote;

    this.quotedSubscription = quotedSubscription;

    this.quotedCharge = quotedCharge;

    this.quotedRamp = quotedRamp;
  }

  /** Parse JSON response into QuoteRetrieveResponse object. */
  public static QuoteRetrieveResponse fromJson(String json) {
    try {

      Quote quote = Quote.fromJson(JsonUtil.getObject(json, "quote"));

      QuotedSubscription quotedSubscription =
          QuotedSubscription.fromJson(JsonUtil.getObject(json, "quoted_subscription"));

      QuotedCharge quotedCharge = QuotedCharge.fromJson(JsonUtil.getObject(json, "quoted_charge"));

      QuotedRamp quotedRamp = QuotedRamp.fromJson(JsonUtil.getObject(json, "quoted_ramp"));

      return new QuoteRetrieveResponse(quote, quotedSubscription, quotedCharge, quotedRamp);
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse QuoteRetrieveResponse from JSON", e);
    }
  }

  /** Get the quote from the response. */
  public Quote getQuote() {
    return quote;
  }

  /** Get the quotedSubscription from the response. */
  public QuotedSubscription getQuotedSubscription() {
    return quotedSubscription;
  }

  /** Get the quotedCharge from the response. */
  public QuotedCharge getQuotedCharge() {
    return quotedCharge;
  }

  /** Get the quotedRamp from the response. */
  public QuotedRamp getQuotedRamp() {
    return quotedRamp;
  }
}
