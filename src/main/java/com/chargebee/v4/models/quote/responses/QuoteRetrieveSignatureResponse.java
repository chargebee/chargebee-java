package com.chargebee.v4.models.quote.responses;

import com.chargebee.v4.models.cpqQuoteSignature.CpqQuoteSignature;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.internal.JsonUtil;
import com.google.gson.JsonObject;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for QuoteRetrieveSignature operation. Contains the response data from a
 * single resource get operation.
 */
public final class QuoteRetrieveSignatureResponse extends BaseResponse {
  private final CpqQuoteSignature cpqQuoteSignature;

  private QuoteRetrieveSignatureResponse(Builder builder) {
    super(builder.httpResponse);

    this.cpqQuoteSignature = builder.cpqQuoteSignature;
  }

  /** Parse JSON response into QuoteRetrieveSignatureResponse object. */
  public static QuoteRetrieveSignatureResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into QuoteRetrieveSignatureResponse object with HTTP response. */
  public static QuoteRetrieveSignatureResponse fromJson(String json, Response httpResponse) {
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
      throw new RuntimeException("Failed to parse QuoteRetrieveSignatureResponse from JSON", e);
    }
  }

  /** Create a new builder for QuoteRetrieveSignatureResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for QuoteRetrieveSignatureResponse. */
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

    public QuoteRetrieveSignatureResponse build() {
      return new QuoteRetrieveSignatureResponse(this);
    }
  }

  /** Get the cpqQuoteSignature from the response. */
  public CpqQuoteSignature getCpqQuoteSignature() {
    return cpqQuoteSignature;
  }

  @Override
  public String toString() {
    return "QuoteRetrieveSignatureResponse{" + "cpqQuoteSignature=" + cpqQuoteSignature + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QuoteRetrieveSignatureResponse that = (QuoteRetrieveSignatureResponse) o;
    return java.util.Objects.equals(cpqQuoteSignature, that.cpqQuoteSignature);
  }

  @Override
  public int hashCode() {

    return java.util.Objects.hash(cpqQuoteSignature);
  }
}
