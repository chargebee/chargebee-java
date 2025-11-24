package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.quote.Quote;

import com.chargebee.v4.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.models.quotedCharge.QuotedCharge;

import com.chargebee.v4.models.quotedRamp.QuotedRamp;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for QuoteRetrieve operation. Contains the response data from a single
 * resource get operation.
 */
public final class QuoteRetrieveResponse extends BaseResponse {
  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private final QuotedCharge quotedCharge;

  private final QuotedRamp quotedRamp;

  private QuoteRetrieveResponse(
      Quote quote,
      QuotedSubscription quotedSubscription,
      QuotedCharge quotedCharge,
      QuotedRamp quotedRamp,
      Response httpResponse) {
    super(httpResponse);

    this.quote = quote;

    this.quotedSubscription = quotedSubscription;

    this.quotedCharge = quotedCharge;

    this.quotedRamp = quotedRamp;
  }

  /** Parse JSON response into QuoteRetrieveResponse object. */
  public static QuoteRetrieveResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into QuoteRetrieveResponse object with HTTP response. */
  public static QuoteRetrieveResponse fromJson(String json, Response httpResponse) {
    try {

      Quote quote = Quote.fromJson(JsonUtil.getObject(json, "quote"));

      QuotedSubscription quotedSubscription =
          QuotedSubscription.fromJson(JsonUtil.getObject(json, "quoted_subscription"));

      QuotedCharge quotedCharge = QuotedCharge.fromJson(JsonUtil.getObject(json, "quoted_charge"));

      QuotedRamp quotedRamp = QuotedRamp.fromJson(JsonUtil.getObject(json, "quoted_ramp"));

      return new QuoteRetrieveResponse(
          quote, quotedSubscription, quotedCharge, quotedRamp, httpResponse);
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
