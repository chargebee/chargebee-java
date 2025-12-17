package com.chargebee.v4.models.csvTaxRule.responses;

import com.chargebee.v4.models.BaseResponse;
import com.chargebee.v4.transport.Response;

/**
 * Immutable response object for CsvTaxRuleCreate operation. Contains the response data from the
 * API.
 */
public final class CsvTaxRuleCreateResponse extends BaseResponse {

  private CsvTaxRuleCreateResponse(Builder builder) {
    super(builder.httpResponse);
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
}
