package com.chargebee.core.responses.pc2Migration;

/**
 * Immutable response object for Pc2MigrationRetrieve operation. Contains the response data from a
 * single resource get operation.
 */
public final class Pc2MigrationRetrieveResponse {

  private Pc2MigrationRetrieveResponse() {}

  /** Parse JSON response into Pc2MigrationRetrieveResponse object. */
  public static Pc2MigrationRetrieveResponse fromJson(String json) {
    try {

      return new Pc2MigrationRetrieveResponse();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationRetrieveResponse from JSON", e);
    }
  }
}
