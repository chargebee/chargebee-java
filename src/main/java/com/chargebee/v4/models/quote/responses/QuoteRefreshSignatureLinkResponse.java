package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.cpqQuoteSignature.CpqQuoteSignature;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for QuoteRefreshSignatureLink operation. Contains the response data
 * from the API.
 */
public final class QuoteRefreshSignatureLinkResponse extends BaseResponse {
  private final CpqQuoteSignature cpqQuoteSignature;

  private QuoteRefreshSignatureLinkResponse(Builder builder) {
    super(builder.httpResponse);

    this.cpqQuoteSignature = builder.cpqQuoteSignature;
  }

  /** Parse JSON response into QuoteRefreshSignatureLinkResponse object. */
  public static QuoteRefreshSignatureLinkResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into QuoteRefreshSignatureLinkResponse object with HTTP response. */
  public static QuoteRefreshSignatureLinkResponse fromJson(String json, Response httpResponse) {
    try {
      JsonObject jsonObj = JsonUtil.parse(json);
      Builder builder = builder();

      JsonObject __cpqQuoteSignatureObj = JsonUtil.getJsonObject(jsonObj, "cpq_quote_signature");
      if (__cpqQuoteSignatureObj != null) {
        builder.cpqQuoteSignature(CpqQuoteSignature.fromJson(__cpqQuoteSignatureObj));
      }

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse QuoteRefreshSignatureLinkResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteRefreshSignatureLinkResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteRefreshSignatureLinkResponse. */
  public static class Builder {

    private CpqQuoteSignature cpqQuoteSignature;

    private Response httpResponse;

    private Builder() {}

    public Builder cpqQuoteSignature(CpqQuoteSignature cpqQuoteSignature) {
      this.cpqQuoteSignature = cpqQuoteSignature;
      return this;
    }

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public QuoteRefreshSignatureLinkResponse build() {
      return new QuoteRefreshSignatureLinkResponse(this);
    }
  }

  /** Get the cpqQuoteSignature from the response. */
  public CpqQuoteSignature getCpqQuoteSignature() {
    return cpqQuoteSignature;
  }

  @Override
  public String toString() {
    return "QuoteRefreshSignatureLinkResponse{" + "cpqQuoteSignature=" + cpqQuoteSignature + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QuoteRefreshSignatureLinkResponse that = (QuoteRefreshSignatureLinkResponse) o;
    return java.util.Objects.equals(cpqQuoteSignature, that.cpqQuoteSignature);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(cpqQuoteSignature);
  }
}
