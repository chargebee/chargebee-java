package com.chargebee.core.responses.quote;

import com.chargebee.core.models.quote.Quote;

import com.chargebee.core.models.quotedSubscription.QuotedSubscription;

import com.chargebee.internal.JsonUtil;

/**
 * Immutable response object for QuoteEditUpdateSubscriptionQuote operation. Contains the response
 * data from the API.
 */
public final class QuoteEditUpdateSubscriptionQuoteResponse {

  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private QuoteEditUpdateSubscriptionQuoteResponse(Builder builder) {

    this.quote = builder.quote;

    this.quotedSubscription = builder.quotedSubscription;
  }

  /** Parse JSON response into QuoteEditUpdateSubscriptionQuoteResponse object. */
  public static QuoteEditUpdateSubscriptionQuoteResponse fromJson(String json) {
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

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse QuoteEditUpdateSubscriptionQuoteResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteEditUpdateSubscriptionQuoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteEditUpdateSubscriptionQuoteResponse. */
  public static class Builder {

    private Quote quote;

    private QuotedSubscription quotedSubscription;

    private Builder() {}

    public Builder quote(Quote quote) {
      this.quote = quote;
      return this;
    }

    public Builder quotedSubscription(QuotedSubscription quotedSubscription) {
      this.quotedSubscription = quotedSubscription;
      return this;
    }

    public QuoteEditUpdateSubscriptionQuoteResponse build() {
      return new QuoteEditUpdateSubscriptionQuoteResponse(this);
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
}
