package com.chargebee.v4.core.responses.csvTaxRule;

import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CsvTaxRuleCreate operation. Contains the response data from the
 * API.
 */
public final class CsvTaxRuleCreateResponse {

  private final Response httpResponse;

  private CsvTaxRuleCreateResponse(Builder builder) {

    this.httpResponse = builder.httpResponse;
  }

  /** Parse JSON response into CsvTaxRuleCreateResponse object. */
  public static CsvTaxRuleCreateResponse fromJson(String json) {
    return fromJson(json, null);
  }

  /** Parse JSON response into CsvTaxRuleCreateResponse object with HTTP response. */
  public static CsvTaxRuleCreateResponse fromJson(String json, Response httpResponse) {
    try {
      Builder builder = builder();

      builder.httpResponse(httpResponse);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse CsvTaxRuleCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for CsvTaxRuleCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for CsvTaxRuleCreateResponse. */
  public static class Builder {

    private Response httpResponse;

    private Builder() {}

    public Builder httpResponse(Response httpResponse) {
      this.httpResponse = httpResponse;
      return this;
    }

    public CsvTaxRuleCreateResponse build() {
      return new CsvTaxRuleCreateResponse(this);
    }
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
