package com.chargebee.v4.core.responses.csvTaxRule;

/**
 * Immutable response object for CsvTaxRuleCreate operation. Contains the response data from the
 * API.
 */
public final class CsvTaxRuleCreateResponse {

  private CsvTaxRuleCreateResponse(Builder builder) {}

  /** Parse JSON response into CsvTaxRuleCreateResponse object. */
  public static CsvTaxRuleCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

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

    private Builder() {}

    public CsvTaxRuleCreateResponse build() {
      return new CsvTaxRuleCreateResponse(this);
    }
  }
}
