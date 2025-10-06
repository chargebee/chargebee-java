package com.chargebee.v4.core.responses.pc2MigrationItemPrice;

/**
 * Immutable response object for Pc2MigrationItemPriceUpdate operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationItemPriceUpdateResponse {

  private Pc2MigrationItemPriceUpdateResponse(Builder builder) {}

  /** Parse JSON response into Pc2MigrationItemPriceUpdateResponse object. */
  public static Pc2MigrationItemPriceUpdateResponse fromJson(String json) {
    try {
      Builder builder = builder();

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemPriceUpdateResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemPriceUpdateResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemPriceUpdateResponse. */
  public static class Builder {

    private Builder() {}

    public Pc2MigrationItemPriceUpdateResponse build() {
      return new Pc2MigrationItemPriceUpdateResponse(this);
    }
  }
}
