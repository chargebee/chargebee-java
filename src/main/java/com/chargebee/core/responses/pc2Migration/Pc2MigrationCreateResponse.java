package com.chargebee.core.responses.pc2Migration;

/**
 * Immutable response object for Pc2MigrationCreate operation. Contains the response data from the
 * API.
 */
public final class Pc2MigrationCreateResponse {

  private Pc2MigrationCreateResponse(Builder builder) {}

  /** Parse JSON response into Pc2MigrationCreateResponse object. */
  public static Pc2MigrationCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationCreateResponse. */
  public static class Builder {

    private Builder() {}

    public Pc2MigrationCreateResponse build() {
      return new Pc2MigrationCreateResponse(this);
    }
  }
}
