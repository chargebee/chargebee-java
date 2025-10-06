package com.chargebee.v4.core.responses.pc2Migration;

/**
 * Immutable response object for Pc2MigrationInitiate operation. Contains the response data from the
 * API.
 */
public final class Pc2MigrationInitiateResponse {

  private Pc2MigrationInitiateResponse(Builder builder) {}

  /** Parse JSON response into Pc2MigrationInitiateResponse object. */
  public static Pc2MigrationInitiateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationInitiateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationInitiateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationInitiateResponse. */
  public static class Builder {

    private Builder() {}

    public Pc2MigrationInitiateResponse build() {
      return new Pc2MigrationInitiateResponse(this);
    }
  }
}
