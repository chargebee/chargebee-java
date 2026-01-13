package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.quote.Quote;

import com.chargebee.v4.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreateSubscriptionForCustomerQuote operation. Contains the response
 * data from the API.
 */
public final class CreateSubscriptionForCustomerQuoteResponse extends BaseResponse {
  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private CreateSubscriptionForCustomerQuoteResponse(Builder builder) {
    super(builder.httpResponse);

    this.quote = builder.quote;

    this.quotedSubscription = builder.quotedSubscription;
  }

  /** Parse JSON response into CreateSubscriptionForCustomerQuoteResponse object. */
  public static CreateSubscriptionForCustomerQuoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into CreateSubscriptionForCustomerQuoteResponse object with HTTP response.
   */
  public static CreateSubscriptionForCustomerQuoteResponse fromJson(
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

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse CreateSubscriptionForCustomerQuoteResponse from JSON", e);
    }
  }

  /** Create a new builder for CreateSubscriptionForCustomerQuoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreateSubscriptionForCustomerQuoteResponse. */
  public static class Builder {

    private Quote quote;

    private QuotedSubscription quotedSubscription;

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

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CreateSubscriptionForCustomerQuoteResponse build() {
      return new CreateSubscriptionForCustomerQuoteResponse(this);
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

  @Override
  public String toString() {
    return "CreateSubscriptionForCustomerQuoteResponse{"
        + "quote="
        + quote
        + ", quotedSubscription="
        + quotedSubscription
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CreateSubscriptionForCustomerQuoteResponse that =
        (CreateSubscriptionForCustomerQuoteResponse) o;
    return java.util.Objects.equals(quote, that.quote)
        && java.util.Objects.equals(quotedSubscription, that.quotedSubscription);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(quote, quotedSubscription);
  }
}
