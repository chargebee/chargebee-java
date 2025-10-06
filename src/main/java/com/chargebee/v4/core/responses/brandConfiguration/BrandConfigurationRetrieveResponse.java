package com.chargebee.v4.core.responses.brandConfiguration;

/**
 * Immutable response object for BrandConfigurationRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class BrandConfigurationRetrieveResponse {

  private BrandConfigurationRetrieveResponse() {}

  /** Parse JSON response into BrandConfigurationRetrieveResponse object. */
  public static BrandConfigurationRetrieveResponse fromJson(String json) {
    try {

      return new BrandConfigurationRetrieveResponse();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse BrandConfigurationRetrieveResponse from JSON", e);
    }
  }
}
