package com.chargebee.core.responses.pc2MigrationItem;

/**
 * Immutable response object for Pc2MigrationItemCreate operation. Contains the response data from
 * the API.
 */
public final class Pc2MigrationItemCreateResponse {

  private Pc2MigrationItemCreateResponse(Builder builder) {}

  /** Parse JSON response into Pc2MigrationItemCreateResponse object. */
  public static Pc2MigrationItemCreateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemCreateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemCreateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemCreateResponse. */
  public static class Builder {

    private Builder() {}

    public Pc2MigrationItemCreateResponse build() {
      return new Pc2MigrationItemCreateResponse(this);
    }
  }
}
