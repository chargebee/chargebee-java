package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.quote.Quote;

import com.chargebee.v4.models.quotedCharge.QuotedCharge;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for EditOneTimeQuote operation. Contains the response data from the
 * API.
 */
public final class EditOneTimeQuoteResponse extends BaseResponse {
  private final Quote quote;

  private final QuotedCharge quotedCharge;

  private EditOneTimeQuoteResponse(Builder builder) {
    super(builder.httpResponse);

    this.quote = builder.quote;

    this.quotedCharge = builder.quotedCharge;
  }

  /** Parse JSON response into EditOneTimeQuoteResponse object. */
  public static EditOneTimeQuoteResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into EditOneTimeQuoteResponse object with HTTP response. */
  public static EditOneTimeQuoteResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __quoteObj = JsonUtil.getJsonObject(jsonObj, "quote");
      if (__quoteObj != null) {
        builder.quote(Quote.fromJson(__quoteObj));
      }

      JsonObject __quotedChargeObj = JsonUtil.getJsonObject(jsonObj, "quoted_charge");
      if (__quotedChargeObj != null) {
        builder.quotedCharge(QuotedCharge.fromJson(__quotedChargeObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse EditOneTimeQuoteResponse from JSON", e);
    }
  }

  /** Create a new builder for EditOneTimeQuoteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for EditOneTimeQuoteResponse. */
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

    public EditOneTimeQuoteResponse build() {
      return new EditOneTimeQuoteResponse(this);
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
    return "EditOneTimeQuoteResponse{" + "quote=" + quote + ", quotedCharge=" + quotedCharge + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    EditOneTimeQuoteResponse that = (EditOneTimeQuoteResponse) o;
    return java.util.Objects.equals(quote, that.quote)
        && java.util.Objects.equals(quotedCharge, that.quotedCharge);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(quote, quotedCharge);
  }
}
