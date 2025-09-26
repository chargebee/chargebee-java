package com.chargebee.core.responses.pc2MigrationItemFamily;

/**
 * Immutable response object for Pc2MigrationItemFamilyRetrieve operation. Contains the response
 * data from a single resource get operation.
 */
public final class Pc2MigrationItemFamilyRetrieveResponse {

  private Pc2MigrationItemFamilyRetrieveResponse() {}

  /** Parse JSON response into Pc2MigrationItemFamilyRetrieveResponse object. */
  public static Pc2MigrationItemFamilyRetrieveResponse fromJson(String json) {
    try {

      return new Pc2MigrationItemFamilyRetrieveResponse();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemFamilyRetrieveResponse from JSON", e);
    }
  }
}
