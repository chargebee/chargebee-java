package com.chargebee.v4.core.responses.quote;

import com.chargebee.v4.core.models.quote.Quote;

import com.chargebee.v4.core.models.quotedCharge.QuotedCharge;

import com.chargebee.v4.internal.JsonUtil;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for QuoteCreateForOnetimeCharges operation. Contains the response data
 * from the API.
 */
public final class QuoteCreateForOnetimeChargesResponse {

  private final Quote quote;

  private final QuotedCharge quotedCharge;

  private final Response httpResponse;

  private QuoteCreateForOnetimeChargesResponse(Builder builder) {

    this.quote = builder.quote;

    this.quotedCharge = builder.quotedCharge;

    this.httpResponse = builder.httpResponse;
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

  /** Get the raw response payload as JSON string. */
  public String responsePayload() {
    return httpResponse != null ? httpResponse.getBodyAsString() : null;
  }

  /** Get the HTTP status code. */
  public int httpStatus() {
    return httpResponse != null ? httpResponse.getStatusCode() : 0;
  }

  /** Get response headers. */
  public java.util.Map<String, java.util.List<String>> headers() {
    return httpResponse != null ? httpResponse.getHeaders() : java.util.Collections.emptyMap();
  }

  /** Get a specific header value. */
  public java.util.List<String> header(String name) {
    if (httpResponse == null) return null;
    return httpResponse.getHeaders().entrySet().stream()
        .filter(e -> e.getKey().equalsIgnoreCase(name))
        .map(java.util.Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}
