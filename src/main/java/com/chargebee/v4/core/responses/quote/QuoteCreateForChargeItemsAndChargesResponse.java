package com.chargebee.v4.core.responses.quote;

import com.chargebee.v4.core.models.quote.Quote;

import com.chargebee.v4.core.models.quotedCharge.QuotedCharge;

import com.chargebee.v4.core.responses.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for QuoteCreateForChargeItemsAndCharges operation. Contains the
 * response data from the API.
 */
public final class QuoteCreateForChargeItemsAndChargesResponse extends BaseResponse {
  private final Quote quote;

  private final QuotedCharge quotedCharge;

  private QuoteCreateForChargeItemsAndChargesResponse(Builder builder) {
    super(builder.httpResponse);

    this.quote = builder.quote;

    this.quotedCharge = builder.quotedCharge;
  }

  /** Parse JSON response into QuoteCreateForChargeItemsAndChargesResponse object. */
  public static QuoteCreateForChargeItemsAndChargesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /**
   * Parse JSON response into QuoteCreateForChargeItemsAndChargesResponse object with HTTP response.
   */
  public static QuoteCreateForChargeItemsAndChargesResponse fromJson(
      String json, Response httpResponse) {
    try {
      Builder builder = builder();

      String __quoteJson = JsonUtil.getObject(json, "quote");
      if (__quoteJson != null) {
        builder.quote(Quote.fromJson(__quoteJson));
      }

      String __quotedChargeJson = JsonUtil.getObject(json, "quoted_charge");
      if (__quotedChargeJson != null) {
        builder.quotedCharge(QuotedCharge.fromJson(__quotedChargeJson));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse QuoteCreateForChargeItemsAndChargesResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteCreateForChargeItemsAndChargesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteCreateForChargeItemsAndChargesResponse. */
  public static class Builder {

    private Quote quote;

    private QuotedCharge quotedCharge;

    private Response httpResponse;

    private Builder() {}

    public Builder quote(Quote quote) {
      this.quote = quote;
      return this;
    }

    public Builder quotedCharge(QuotedCharge quotedCharge) {
      this.quotedCharge = quotedCharge;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public QuoteCreateForChargeItemsAndChargesResponse build() {
      return new QuoteCreateForChargeItemsAndChargesResponse(this);
    }
  }

  /** Get the quote from the response. */
  public Quote getQuote() {
    return quote;
  }

  /** Get the quotedCharge from the response. */
  public QuotedCharge getQuotedCharge() {
    return quotedCharge;
  }
}
