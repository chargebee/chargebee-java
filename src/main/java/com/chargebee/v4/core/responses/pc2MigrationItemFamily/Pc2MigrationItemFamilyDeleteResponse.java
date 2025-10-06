package com.chargebee.v4.core.responses.pc2MigrationItemFamily;

import com.chargebee.v4.internal.JsonUtil;

/**
 * Immutable response object for Pc2MigrationItemFamilyDelete operation. Contains the response data
 * from the API.
 */
public final class Pc2MigrationItemFamilyDeleteResponse {

  private final Boolean isDeleted;

  private Pc2MigrationItemFamilyDeleteResponse(Builder builder) {

    this.isDeleted = builder.isDeleted;
  }

  /** Parse JSON response into Pc2MigrationItemFamilyDeleteResponse object. */
  public static Pc2MigrationItemFamilyDeleteResponse fromJson(String json) {
    try {
      Builder builder = builder();

      builder.isDeleted(JsonUtil.getBoolean(json, "is_deleted"));

      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(
          "Failed to parse Pc2MigrationItemFamilyDeleteResponse from JSON", e);
    }
  }

  /** Create a new builder for Pc2MigrationItemFamilyDeleteResponse. */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder for Pc2MigrationItemFamilyDeleteResponse. */
  public static class Builder {

    private Boolean isDeleted;

    private Builder() {}

    public Builder isDeleted(Boolean isDeleted) {
      this.isDeleted = isDeleted;
      return this;
    }

    public Pc2MigrationItemFamilyDeleteResponse build() {
      return new Pc2MigrationItemFamilyDeleteResponse(this);
    }
  }

  /** Get the isDeleted from the response. */
  public Boolean getIsDeleted() {
    return isDeleted;
  }
}
