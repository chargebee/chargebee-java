package com.chargebee.v4.core.responses.pc2MigrationItemPrice;

/**
 * Immutable response object for Pc2MigrationItemPriceRetrieve operation. Contains the response data
 * from a single resource get operation.
 */
public final class Pc2MigrationItemPriceRetrieveResponse {

  private Pc2MigrationItemPriceRetrieveResponse() {}

  /** Parse JSON response into Pc2MigrationItemPriceRetrieveResponse object. */
  public static Pc2MigrationItemPriceRetrieveResponse fromJson(String json) {
    try {

      return new Pc2MigrationItemPriceRetrieveResponse();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemPriceRetrieveResponse from JSON", e);
    }
  }
}
