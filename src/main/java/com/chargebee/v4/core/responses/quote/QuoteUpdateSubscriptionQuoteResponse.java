package com.chargebee.v4.core.responses.quote;

import com.chargebee.v4.core.models.quote.Quote;

import com.chargebee.v4.core.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for QuoteUpdateSubscriptionQuote operation. Contains the response data
 * from the API.
 */
public final class QuoteUpdateSubscriptionQuoteResponse {

  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private QuoteUpdateSubscriptionQuoteResponse(Builder builder) {

    this.quote = builder.quote;

    this.quotedSubscription = builder.quotedSubscription;
  }

  /** Parse JSON response into QuoteUpdateSubscriptionQuoteResponse object. */
  public static QuoteUpdateSubscriptionQuoteResponse fromJson(String json) {
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
          "Failed to parse QuoteUpdateSubscriptionQuoteResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteUpdateSubscriptionQuoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteUpdateSubscriptionQuoteResponse. */
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

    public QuoteUpdateSubscriptionQuoteResponse build() {
      return new QuoteUpdateSubscriptionQuoteResponse(this);
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
