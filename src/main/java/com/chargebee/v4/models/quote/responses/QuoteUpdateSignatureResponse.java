package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.cpqQuoteSignature.CpqQuoteSignature;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for QuoteUpdateSignature operation. Contains the response data from the
 * API.
 */
public final class QuoteUpdateSignatureResponse extends BaseResponse {
  private final CpqQuoteSignature cpqQuoteSignature;

  private QuoteUpdateSignatureResponse(Builder builder) {
    super(builder.httpResponse);

    this.cpqQuoteSignature = builder.cpqQuoteSignature;
  }

  /** Parse JSON response into QuoteUpdateSignatureResponse object. */
  public static QuoteUpdateSignatureResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into QuoteUpdateSignatureResponse object with HTTP response. */
  public static QuoteUpdateSignatureResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse QuoteUpdateSignatureResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteUpdateSignatureResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteUpdateSignatureResponse. */
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

    public QuoteUpdateSignatureResponse build() {
      return new QuoteUpdateSignatureResponse(this);
    }
  }

  /** Get the cpqQuoteSignature from the response. */
  public CpqQuoteSignature getCpqQuoteSignature() {
    return cpqQuoteSignature;
  }

  @Override
  public String toString() {
    return "QuoteUpdateSignatureResponse{" + "cpqQuoteSignature=" + cpqQuoteSignature + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QuoteUpdateSignatureResponse that = (QuoteUpdateSignatureResponse) o;
    return java.util.Objects.equals(cpqQuoteSignature, that.cpqQuoteSignature);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(cpqQuoteSignature);
  }
}
