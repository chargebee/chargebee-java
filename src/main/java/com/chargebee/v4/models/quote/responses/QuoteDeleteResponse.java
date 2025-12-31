package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.quote.Quote;

import com.chargebee.v4.models.quotedRamp.QuotedRamp;

import com.chargebee.v4.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.models.quotedCharge.QuotedCharge;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/** Immutable response object for QuoteDelete operation. Contains the response data from the API. */
public final class QuoteDeleteResponse extends BaseResponse {
  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private final QuotedCharge quotedCharge;

  private final QuotedRamp quotedRamp;

  private QuoteDeleteResponse(Builder builder) {
    super(builder.httpResponse);

    this.quote = builder.quote;

    this.quotedSubscription = builder.quotedSubscription;

    this.quotedCharge = builder.quotedCharge;

    this.quotedRamp = builder.quotedRamp;
  }

  /** Parse JSON response into QuoteDeleteResponse object. */
  public static QuoteDeleteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into QuoteDeleteResponse object with HTTP response. */
  public static QuoteDeleteResponse fromJson(String json, Response httpResponse) {
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

      String __quotedChargeJson = JsonUtil.getObject(json, "quoted_charge");
      if (__quotedChargeJson != null) {
        builder.quotedCharge(QuotedCharge.fromJson(__quotedChargeJson));
      }

      String __quotedRampJson = JsonUtil.getObject(json, "quoted_ramp");
      if (__quotedRampJson != null) {
        builder.quotedRamp(QuotedRamp.fromJson(__quotedRampJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse QuoteDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteDeleteResponse. */
  public static class Builder {

    private Quote quote;

    private QuotedSubscription quotedSubscription;

    private QuotedCharge quotedCharge;

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

    public Builder quotedCharge(QuotedCharge quotedCharge) {
      this.quotedCharge = quotedCharge;
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

    public QuoteDeleteResponse build() {
      return new QuoteDeleteResponse(this);
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

  @Override
  public String toString() {
    return "QuoteDeleteResponse{"
        + "quote="
        + quote
        + ", quotedSubscription="
        + quotedSubscription
        + ", quotedCharge="
        + quotedCharge
        + ", quotedRamp="
        + quotedRamp
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuoteDeleteResponse that = (QuoteDeleteResponse) o;
    return java.util.Objects.equals(quote, that.quote)
        && java.util.Objects.equals(quotedSubscription, that.quotedSubscription)
        && java.util.Objects.equals(quotedCharge, that.quotedCharge)
        && java.util.Objects.equals(quotedRamp, that.quotedRamp);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(quote, quotedSubscription, quotedCharge, quotedRamp);
  }
}
