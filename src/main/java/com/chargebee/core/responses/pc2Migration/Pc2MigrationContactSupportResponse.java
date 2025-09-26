package com.chargebee.core.responses.pc2Migration;

/**
 * Immutable response object for Pc2MigrationContactSupport operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationContactSupportResponse {

  private Pc2MigrationContactSupportResponse(Builder builder) {}

  /** Parse JSON response into Pc2MigrationContactSupportResponse object. */
  public static Pc2MigrationContactSupportResponse fromJson(String json) {
    try {
      Builder builder = builder();

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationContactSupportResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationContactSupportResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationContactSupportResponse. */
  public static class Builder {

    private Builder() {}

    public Pc2MigrationContactSupportResponse build() {
      return new Pc2MigrationContactSupportResponse(this);
    }
  }
}
