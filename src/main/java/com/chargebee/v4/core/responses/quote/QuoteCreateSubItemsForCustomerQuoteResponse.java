package com.chargebee.v4.core.responses.quote;

import com.chargebee.v4.core.models.quote.Quote;

import com.chargebee.v4.core.models.quotedRamp.QuotedRamp;

import com.chargebee.v4.core.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for QuoteCreateSubItemsForCustomerQuote operation. Contains the
 * response data from the API.
 */
public final class QuoteCreateSubItemsForCustomerQuoteResponse extends BaseResponse {
  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private final QuotedRamp quotedRamp;

  private QuoteCreateSubItemsForCustomerQuoteResponse(Builder builder) {
    super(builder.httpResponse);

    this.quote = builder.quote;

    this.quotedSubscription = builder.quotedSubscription;

    this.quotedRamp = builder.quotedRamp;
  }

  /** Parse JSON response into QuoteCreateSubItemsForCustomerQuoteResponse object. */
  public static QuoteCreateSubItemsForCustomerQuoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into QuoteCreateSubItemsForCustomerQuoteResponse object with HTTP response.
   */
  public static QuoteCreateSubItemsForCustomerQuoteResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __quoteJson = JsonUtil.getObject(json, "quote");
      if (__quoteJson != null) {
        builder.quote(Quote.fromJson(__quoteJson));
      }

      String __quotedSubscriptionJson = JsonUtil.getObject(json, "quoted_subscription");
      if (__quotedSubscriptionJson != null) {
        builder.quotedSubscription(QuotedSubscription.fromJson(__quotedSubscriptionJson));
      }

      String __quotedRampJson = JsonUtil.getObject(json, "quoted_ramp");
      if (__quotedRampJson != null) {
        builder.quotedRamp(QuotedRamp.fromJson(__quotedRampJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse QuoteCreateSubItemsForCustomerQuoteResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteCreateSubItemsForCustomerQuoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteCreateSubItemsForCustomerQuoteResponse. */
  public static class Builder {

    private Quote quote;

    private QuotedSubscription quotedSubscription;

    private QuotedRamp quotedRamp;

    private Response httpResponse;

    private Builder() {}

    public Builder quote(Quote quote) {
      this.quote = quote;
      return this;
    }

    public Builder quotedSubscription(QuotedSubscription quotedSubscription) {
      this.quotedSubscription = quotedSubscription;
      return this;
    }

    public Builder quotedRamp(QuotedRamp quotedRamp) {
      this.quotedRamp = quotedRamp;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public QuoteCreateSubItemsForCustomerQuoteResponse build() {
      return new QuoteCreateSubItemsForCustomerQuoteResponse(this);
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

  /** Get the quotedRamp from the response. */
  public QuotedRamp getQuotedRamp() {
    return quotedRamp;
  }
}
