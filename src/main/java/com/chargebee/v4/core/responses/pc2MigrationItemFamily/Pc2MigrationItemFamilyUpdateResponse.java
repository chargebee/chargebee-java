package com.chargebee.v4.core.responses.pc2MigrationItemFamily;

/**
 * Immutable response object for Pc2MigrationItemFamilyUpdate operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationItemFamilyUpdateResponse {

  private Pc2MigrationItemFamilyUpdateResponse(Builder builder) {}

  /** Parse JSON response into Pc2MigrationItemFamilyUpdateResponse object. */
  public static Pc2MigrationItemFamilyUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemFamilyUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemFamilyUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemFamilyUpdateResponse. */
  public static class Builder {

    private Builder() {}

    public Pc2MigrationItemFamilyUpdateResponse build() {
      return new Pc2MigrationItemFamilyUpdateResponse(this);
    }
  }
}
