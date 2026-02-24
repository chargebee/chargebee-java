package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.quote.Quote;

import com.chargebee.v4.models.quotedRamp.QuotedRamp;

import com.chargebee.v4.models.quotedSubscription.QuotedSubscription;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EditCreateSubscriptionCustomerQuoteForItems operation. Contains the
 * response data from the API.
 */
public final class EditCreateSubscriptionCustomerQuoteForItemsResponse extends BaseResponse {
  private final Quote quote;

  private final QuotedSubscription quotedSubscription;

  private final QuotedRamp quotedRamp;

  private EditCreateSubscriptionCustomerQuoteForItemsResponse(Builder builder) {
    super(builder.httpResponse);

    this.quote = builder.quote;

    this.quotedSubscription = builder.quotedSubscription;

    this.quotedRamp = builder.quotedRamp;
  }

  /** Parse JSON response into EditCreateSubscriptionCustomerQuoteForItemsResponse object. */
  public static EditCreateSubscriptionCustomerQuoteForItemsResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into EditCreateSubscriptionCustomerQuoteForItemsResponse object with HTTP
   * response.
   */
  public static EditCreateSubscriptionCustomerQuoteForItemsResponse fromJson(
      String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __quoteObj = JsonUtil.getJsonObject(jsonObj, "quote");
      if (__quoteObj != null) {
        builder.quote(Quote.fromJson(__quoteObj));
      }

      JsonObject __quotedSubscriptionObj = JsonUtil.getJsonObject(jsonObj, "quoted_subscription");
      if (__quotedSubscriptionObj != null) {
        builder.quotedSubscription(QuotedSubscription.fromJson(__quotedSubscriptionObj));
      }

      JsonObject __quotedRampObj = JsonUtil.getJsonObject(jsonObj, "quoted_ramp");
      if (__quotedRampObj != null) {
        builder.quotedRamp(QuotedRamp.fromJson(__quotedRampObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse EditCreateSubscriptionCustomerQuoteForItemsResponse from JSON", e);
    }
  }

  /** Create a new builder for EditCreateSubscriptionCustomerQuoteForItemsResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EditCreateSubscriptionCustomerQuoteForItemsResponse. */
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

    public EditCreateSubscriptionCustomerQuoteForItemsResponse build() {
      return new EditCreateSubscriptionCustomerQuoteForItemsResponse(this);
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
    return "EditCreateSubscriptionCustomerQuoteForItemsResponse{"
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

    EditCreateSubscriptionCustomerQuoteForItemsResponse that =
        (EditCreateSubscriptionCustomerQuoteForItemsResponse) o;
    return java.util.Objects.equals(quote, that.quote)
        && java.util.Objects.equals(quotedSubscription, that.quotedSubscription)
        && java.util.Objects.equals(quotedRamp, that.quotedRamp);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(quote, quotedSubscription, quotedRamp);
  }
}
