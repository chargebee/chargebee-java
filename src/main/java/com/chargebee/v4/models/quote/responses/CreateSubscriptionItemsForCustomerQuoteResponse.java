package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.quote.Quote;

import com.chargebee.v4.models.quotedRamp.QuotedRamp;

import com.chargebee.v4.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CreateSubscriptionItemsForCustomerQuote operation. Contains the
 * response data from the API.
 */
public final class CreateSubscriptionItemsForCustomerQuoteResponse extends BaseResponse {
  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private final QuotedRamp quotedRamp;

  private CreateSubscriptionItemsForCustomerQuoteResponse(Builder builder) {
    super(builder.httpResponse);

    this.quote = builder.quote;

    this.quotedSubscription = builder.quotedSubscription;

    this.quotedRamp = builder.quotedRamp;
  }

  /** Parse JSON response into CreateSubscriptionItemsForCustomerQuoteResponse object. */
  public static CreateSubscriptionItemsForCustomerQuoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into CreateSubscriptionItemsForCustomerQuoteResponse object with HTTP
   * response.
   */
  public static CreateSubscriptionItemsForCustomerQuoteResponse fromJson(
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
          "Failed to parse CreateSubscriptionItemsForCustomerQuoteResponse from JSON", e);
    }
  }

  /** Create a new builder for CreateSubscriptionItemsForCustomerQuoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CreateSubscriptionItemsForCustomerQuoteResponse. */
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

    public CreateSubscriptionItemsForCustomerQuoteResponse build() {
      return new CreateSubscriptionItemsForCustomerQuoteResponse(this);
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

  @Override
  public String toString() {
    return "CreateSubscriptionItemsForCustomerQuoteResponse{"
        + "quote="
        + quote
        + ", quotedSubscription="
        + quotedSubscription
        + ", quotedRamp="
        + quotedRamp
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CreateSubscriptionItemsForCustomerQuoteResponse that =
        (CreateSubscriptionItemsForCustomerQuoteResponse) o;
    return java.util.Objects.equals(quote, that.quote)
        && java.util.Objects.equals(quotedSubscription, that.quotedSubscription)
        && java.util.Objects.equals(quotedRamp, that.quotedRamp);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(quote, quotedSubscription, quotedRamp);
  }
}
