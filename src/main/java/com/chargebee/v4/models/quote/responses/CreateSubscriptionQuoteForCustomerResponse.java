package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.quote.Quote;

import com.chargebee.v4.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreateSubscriptionQuoteForCustomer operation. Contains the response
 * data from the API.
 */
public final class CreateSubscriptionQuoteForCustomerResponse extends BaseResponse {
  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private CreateSubscriptionQuoteForCustomerResponse(Builder builder) {
    super(builder.httpResponse);

    this.quote = builder.quote;

    this.quotedSubscription = builder.quotedSubscription;
  }

  /** Parse JSON response into CreateSubscriptionQuoteForCustomerResponse object. */
  public static CreateSubscriptionQuoteForCustomerResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into CreateSubscriptionQuoteForCustomerResponse object with HTTP response.
   */
  public static CreateSubscriptionQuoteForCustomerResponse fromJson(
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
          "Failed to parse CreateSubscriptionQuoteForCustomerResponse from JSON", e);
    }
  }

  /** Create a new builder for CreateSubscriptionQuoteForCustomerResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreateSubscriptionQuoteForCustomerResponse. */
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

    public CreateSubscriptionQuoteForCustomerResponse build() {
      return new CreateSubscriptionQuoteForCustomerResponse(this);
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
