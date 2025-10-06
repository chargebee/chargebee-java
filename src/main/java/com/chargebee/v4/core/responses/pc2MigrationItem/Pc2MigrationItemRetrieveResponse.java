package com.chargebee.v4.core.responses.pc2MigrationItem;

/**
 * Immutable response object for Pc2MigrationItemRetrieve operation. Contains the response data from
 * a single resource get operation.
 */
public final class Pc2MigrationItemRetrieveResponse {

  private Pc2MigrationItemRetrieveResponse() {}

  /** Parse JSON response into Pc2MigrationItemRetrieveResponse object. */
  public static Pc2MigrationItemRetrieveResponse fromJson(String json) {
    try {

      return new Pc2MigrationItemRetrieveResponse();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemRetrieveResponse from JSON", e);
    }
  }
}
