package com.chargebee.core.responses.pc2MigrationItemFamily;

/**
 * Immutable response object for Pc2MigrationItemFamilyCreate operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationItemFamilyCreateResponse {

  private Pc2MigrationItemFamilyCreateResponse(Builder builder) {}

  /** Parse JSON response into Pc2MigrationItemFamilyCreateResponse object. */
  public static Pc2MigrationItemFamilyCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemFamilyCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemFamilyCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemFamilyCreateResponse. */
  public static class Builder {

    private Builder() {}

    public Pc2MigrationItemFamilyCreateResponse build() {
      return new Pc2MigrationItemFamilyCreateResponse(this);
    }
  }
}
