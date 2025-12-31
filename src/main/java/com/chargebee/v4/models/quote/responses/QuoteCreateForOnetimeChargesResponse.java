package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.quote.Quote;

import com.chargebee.v4.models.quotedCharge.QuotedCharge;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for QuoteCreateForOnetimeCharges operation. Contains the response data
 * from the API.
 */
public final class QuoteCreateForOnetimeChargesResponse extends BaseResponse {
  private final Quote quote;

  private final QuotedCharge quotedCharge;

  private QuoteCreateForOnetimeChargesResponse(Builder builder) {
    super(builder.httpResponse);

    this.quote = builder.quote;

    this.quotedCharge = builder.quotedCharge;
  }

  /** Parse JSON response into QuoteCreateForOnetimeChargesResponse object. */
  public static QuoteCreateForOnetimeChargesResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into QuoteCreateForOnetimeChargesResponse object with HTTP response. */
  public static QuoteCreateForOnetimeChargesResponse fromJson(String json, Response httpResponse) {
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
          "Failed to parse QuoteCreateForOnetimeChargesResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteCreateForOnetimeChargesResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteCreateForOnetimeChargesResponse. */
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

    public QuoteCreateForOnetimeChargesResponse build() {
      return new QuoteCreateForOnetimeChargesResponse(this);
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

  @Override
  public String toString() {
    return "QuoteCreateForOnetimeChargesResponse{"
        + "quote="
        + quote
        + ", quotedCharge="
        + quotedCharge
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuoteCreateForOnetimeChargesResponse that = (QuoteCreateForOnetimeChargesResponse) o;
    return java.util.Objects.equals(quote, that.quote)
        && java.util.Objects.equals(quotedCharge, that.quotedCharge);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(quote, quotedCharge);
  }
}
