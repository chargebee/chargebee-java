package com.chargebee.v4.core.responses.quote;

import com.chargebee.v4.core.models.quote.Quote;

import com.chargebee.v4.core.models.quotedRamp.QuotedRamp;

import com.chargebee.v4.core.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for QuoteEditUpdateSubscriptionQuoteForItems operation. Contains the
 * response data from the API.
 */
public final class QuoteEditUpdateSubscriptionQuoteForItemsResponse {

  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private final QuotedRamp quotedRamp;

  private QuoteEditUpdateSubscriptionQuoteForItemsResponse(Builder builder) {

    this.quote = builder.quote;

    this.quotedSubscription = builder.quotedSubscription;

    this.quotedRamp = builder.quotedRamp;
  }

  /** Parse JSON response into QuoteEditUpdateSubscriptionQuoteForItemsResponse object. */
  public static QuoteEditUpdateSubscriptionQuoteForItemsResponse fromJson(String json) {
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

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse QuoteEditUpdateSubscriptionQuoteForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteEditUpdateSubscriptionQuoteForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteEditUpdateSubscriptionQuoteForItemsResponse. */
  public static class Builder {

    private Quote quote;

    private QuotedSubscription quotedSubscription;

    private QuotedRamp quotedRamp;

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

    public QuoteEditUpdateSubscriptionQuoteForItemsResponse build() {
      return new QuoteEditUpdateSubscriptionQuoteForItemsResponse(this);
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
