package com.chargebee.v4.core.responses.pc2MigrationItem;

/**
 * Immutable response object for Pc2MigrationItemUpdate operation. Contains the response data from
 * the API.
 */
public final class Pc2MigrationItemUpdateResponse {

  private Pc2MigrationItemUpdateResponse(Builder builder) {}

  /** Parse JSON response into Pc2MigrationItemUpdateResponse object. */
  public static Pc2MigrationItemUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse Pc2MigrationItemUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemUpdateResponse. */
  public static class Builder {

    private Builder() {}

    public Pc2MigrationItemUpdateResponse build() {
      return new Pc2MigrationItemUpdateResponse(this);
    }
  }
}
